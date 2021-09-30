package com.renogy.mvpmode.http.observe;


import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.google.gson.JsonParseException;
import com.renogy.mvpmode.base.contract.BaseImpl;
import com.renogy.mvpmode.base.response.BaseResponse;
import com.renogy.mvpmode.common.ExceptionEnum;
import com.renogy.mvpmode.data.exception.RxException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.text.ParseException;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.ResourceObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

import static com.renogy.mvpmode.common.ExceptionEnum.CONNECT_TIMEOUT;
import static com.renogy.mvpmode.common.ExceptionEnum.FORMAT_ERROR;
import static com.renogy.mvpmode.common.ExceptionEnum.RESPONSE_ERROR;
import static com.renogy.mvpmode.common.ExceptionEnum.RESPONSE_NULL;
import static com.renogy.mvpmode.common.ExceptionEnum.SERVER_ERROR;
import static com.renogy.mvpmode.common.ExceptionEnum.UNKNOWN_ERROR;
import static com.renogy.mvpmode.common.ExceptionEnum.UNKNOWN_SERVER_ERROR;
import static com.renogy.mvpmode.common.HttpErrorCode.*;

/**
 * @author Create by 17474 on 2021/4/22.
 * Email： lishuwentimor1994@163.com
 * Describe：封装观察者
 */
public abstract class RxObserver<T> extends ResourceObserver<T> {

    private final BaseImpl baseView;
    private final boolean show;


    public RxObserver(BaseImpl baseView) {
        this(baseView, true);
    }

    public RxObserver(BaseImpl baseView, boolean show) {
        this.baseView = baseView;
        this.show = show;
    }

    @Override
    protected void onStart() {
        super.onStart();
//        if (show) {
//
//        }
    }

    @Override
    public void onNext(@NonNull T t) {
        onRxSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        //自动取消订阅事件
        if (e instanceof HttpException) { // HTTP错误
            HttpException httpException = (HttpException) e;
            Response response = httpException.response();
            if (response == null) {
                ExceptionError(RESPONSE_NULL, e.getMessage());
                return;
            }
            ResponseBody errorBody = response.errorBody();
            if (errorBody == null) {
                ExceptionError(RESPONSE_NULL, e.getMessage());
                return;
            }
            int code = httpException.code();
            if (200 == code) {
                try {
                    BaseResponse baseResponse = GsonUtils.fromJson(errorBody.toString(), BaseResponse.class);
                    businessError(baseResponse);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    ExceptionError(RESPONSE_ERROR, e.getMessage());
                }
            } else {
                httpError(code, e.getMessage());
            }
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {   //   连接错误
            ExceptionError(SERVER_ERROR, e.getMessage());
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            ExceptionError(CONNECT_TIMEOUT, e.getMessage());
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {   //  解析错误
            ExceptionError(FORMAT_ERROR, e.getMessage());
        } else if (e instanceof UnknownServiceException) {
            ExceptionError(UNKNOWN_SERVER_ERROR, e.getMessage());
        } else if (e instanceof RxException) {
            businessError(((RxException) e).getResponse());
        } else {
            ExceptionError(UNKNOWN_ERROR, e.getMessage());
        }
        onRxError();
    }

    @Override
    public void onComplete() {

    }

    /**
     * 自己的业务逻辑的错误码
     *
     * @param response 服务器响应数据
     */
    private void businessError(BaseResponse response) {
        if (response != null && show) {
            String errorMsg = "errorCode：" + response.getCode() + " errorMsg: " + response.getMsg();
            baseView.showToast(errorMsg);
        }
    }


    /**
     * 处理http请求错误
     *
     * @param httpErrorCode 错误码
     */
    private void httpError(int httpErrorCode, String s) {
        ExceptionEnum exceptionEnum = null;
        switch (httpErrorCode) {
            case HTTP_400_ERROR:
                exceptionEnum = ExceptionEnum.HTTP_400_ERROR;
                break;
            case HTTP_401_ERROR:
                exceptionEnum = ExceptionEnum.HTTP_401_ERROR;
                break;
            case HTTP_403_ERROR:
                exceptionEnum = ExceptionEnum.HTTP_403_ERROR;
                break;
            case HTTP_404_ERROR:
                exceptionEnum = ExceptionEnum.HTTP_404_ERROR;
                break;
            case HTTP_500_ERROR:
                exceptionEnum = ExceptionEnum.HTTP_500_ERROR;
                break;
            case HTTP_502_ERROR:
                exceptionEnum = ExceptionEnum.HTTP_502_ERROR;
                break;
            case HTTP_503_ERROR:
                exceptionEnum = ExceptionEnum.HTTP_503_ERROR;
                break;
            case HTTP_504_ERROR:
                exceptionEnum = ExceptionEnum.HTTP_504_ERROR;
                break;
            case HTTP_505_ERROR:
                exceptionEnum = ExceptionEnum.HTTP_505_ERROR;
                break;
            default:
                break;
        }
        ExceptionError(exceptionEnum, s);
    }


    //是否显示错误信息
    private void ExceptionError(ExceptionEnum exceptionEnum, String s) {
        if (show && exceptionEnum != null) {
            String errorMsg = exceptionEnum.getAllMsg();
            if (AppUtils.isAppDebug()) {
                errorMsg = errorMsg + "\n" + s;
            }
            baseView.showToast(errorMsg);
        }
    }

    public abstract void onRxSuccess(T response);

    public abstract void onRxError();
}
