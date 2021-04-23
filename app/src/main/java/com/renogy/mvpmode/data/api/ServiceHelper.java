package com.renogy.mvpmode.data.api;

import android.text.TextUtils;
import com.blankj.utilcode.util.AppUtils;
import com.renogy.mvpmode.MVPApp;
import com.renogy.mvpmode.common.AppConstants;
import com.renogy.mvpmode.data.interceptor.NetWorkInterceptor;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Create by 17474 on 2021/4/20.
 * Email： lishuwentimor1994@163.com
 * Describe：初始化一个api服务
 */
public class ServiceHelper {

    private final static int CONNECT_TIME = 20;
    private final static int READ_TIME = 20;
    private final static int WRITE_TIME = 20;
    private final static String KEY_FILE = "";
    private static MVPApi service;
    private static Retrofit retrofit;
    private static String httpUrl;

    private final static class LazyHolder {
        private final static ServiceHelper INSTANCE = new ServiceHelper();
    }

    public static ServiceHelper getInstance() {
        return LazyHolder.INSTANCE;
    }

    //初始化一次
    public void init() {
        if (service != null) return;
        boolean debug = AppUtils.isAppDebug();
        if (debug) {
            httpUrl = AppConstants.APP_BASE_DEV_URL;
        } else {
            httpUrl = AppConstants.APP_BASE_RELEASE_URL;
        }
        service = getRetrofit().create(MVPApi.class);
    }

    public MVPApi getMVPApiService() {
        return service;
    }


    /**
     * 获取debug
     *
     * @return 你的网络请求信息
     */
    private static synchronized Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(getDefaultClient())
                    .baseUrl(httpUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }




    /**
     * 获取带有ssl认证的okHttp
     *
     * @return 默认的客户端
     */
    private static OkHttpClient getDefaultClient() {
        try {
            return getBaseHttpClient(KEY_FILE, CONNECT_TIME, READ_TIME, WRITE_TIME, new NetWorkInterceptor(), AppUtils.isAppDebug());
        } catch (IOException e) {
            e.printStackTrace();
            return new OkHttpClient();
        }
    }


    /**
     * 获取带有ssl认证的okHttp
     *
     * @param connectTimeOut     连接超时时间
     * @param keyFileName        密钥的名称
     * @param readTimeOut        读取超时时间
     * @param writeTimeOut       写入超时时间
     * @param debug              是否是开发版本或者正式版本
     * @param networkInterceptor 拦截器
     * @return OkHttpClient
     */
    public static OkHttpClient getBaseHttpClient(String keyFileName, int connectTimeOut, int readTimeOut, int writeTimeOut, Interceptor networkInterceptor, boolean debug) throws IOException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        SSLSocketFactory sslSocketFactory;
        if (!TextUtils.isEmpty(keyFileName)) {
            BufferedInputStream inputStream = new BufferedInputStream(
                    MVPApp.getInstance().getAssets().open(keyFileName));
            try {
                X509TrustManager trustManager = trustManagerForCertificates(inputStream);
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, new TrustManager[]{trustManager}, null);
                sslSocketFactory = sslContext.getSocketFactory();
                builder.sslSocketFactory(sslSocketFactory, trustManager);
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            }
        }
        builder.connectTimeout(connectTimeOut, TimeUnit.SECONDS);
        builder.readTimeout(readTimeOut, TimeUnit.SECONDS);
        builder.writeTimeout(writeTimeOut, TimeUnit.SECONDS);
        if (debug) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        builder.hostnameVerifier((hostname, session) -> true);
        builder.addInterceptor(networkInterceptor);
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }


    private static X509TrustManager trustManagerForCertificates(InputStream in)
            throws GeneralSecurityException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
        if (certificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }

        // Put the certificates a key store.
        char[] password = "password".toCharArray(); // Any password will work.
        KeyStore keyStore = newEmptyKeyStore(password);
        int index = 0;
        for (Certificate certificate : certificates) {
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificate);
        }

        // Use it to build an X509 trust manager.
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
    }

    private static KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream in = null; // By convention, 'null' creates an empty key store.
            keyStore.load(in, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }


}
