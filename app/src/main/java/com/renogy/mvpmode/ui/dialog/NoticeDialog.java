package com.renogy.mvpmode.ui.dialog;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.lxj.xpopup.core.CenterPopupView;
import com.renogy.mvpmode.R;

/**
 * @author Create by 17474 on 2021/5/7.
 * Email： lishuwentimor1994@163.com
 * Describe：提示框
 */
@SuppressLint("ViewConstructor")
public class NoticeDialog extends CenterPopupView {
    private String text_title;
    private String text_content;
    private String text_confirm;
    private String text_cancel;
    private OnClickListener onClickListener;

    public static NoticeDialog of(NoticeDialogBuilder noticeDialogBuilder) {
        return new NoticeDialog(noticeDialogBuilder);
    }


    public NoticeDialog(NoticeDialogBuilder noticeDialogBuilder) {
        super(ActivityUtils.getTopActivity());
        this.text_title = noticeDialogBuilder.text_title;
        this.text_content = noticeDialogBuilder.text_content;
        this.text_confirm = noticeDialogBuilder.text_confirm;
        this.text_cancel = noticeDialogBuilder.text_cancel;
        this.onClickListener = noticeDialogBuilder.onClickListener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_notice;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        TextView mTitle = findViewById(R.id.title);
        TextView mContent = findViewById(R.id.content);
        TextView mCancel = findViewById(R.id.cancel);
        TextView mConfirm = findViewById(R.id.confirm);
        if (!TextUtils.isEmpty(text_title)) {
            mTitle.setText(text_title);
        }
        if (!TextUtils.isEmpty(text_content)) {
            mContent.setText(text_content);
        }
        if (!TextUtils.isEmpty(text_confirm)) {
            mConfirm.setText(text_confirm);
        }
        if (!TextUtils.isEmpty(text_cancel)) {
            mCancel.setText(text_cancel);
        }

        mConfirm.setOnClickListener(v -> {
            if (onClickListener != null) {
                onClickListener.onConfirmClick(v);
            }
            delayDismiss(200);
        });

        mCancel.setOnClickListener(v -> {
            if (onClickListener != null) {
                onClickListener.onCancelClick(v);
            }
            delayDismiss(200);
        });
    }


    public static class NoticeDialogBuilder {

        private String text_title;
        private String text_content;
        private String text_confirm;
        private String text_cancel;
        private OnClickListener onClickListener;

        public NoticeDialog build() {
            return new NoticeDialog(this);
        }

        public NoticeDialogBuilder setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }

        public String getText_title() {
            return text_title;
        }

        public NoticeDialogBuilder textTitle(String text_title) {
            this.text_title = text_title;
            return this;
        }

        public String getText_content() {
            return text_content;
        }

        public NoticeDialogBuilder textContent(String text_content) {
            this.text_content = text_content;
            return this;
        }

        public String getText_confirm() {
            return text_confirm;
        }

        public NoticeDialogBuilder textConfirm(String text_confirm) {
            this.text_confirm = text_confirm;
            return this;
        }

        public String getText_cancel() {
            return text_cancel;
        }

        public NoticeDialogBuilder textCancel(String text_cancel) {
            this.text_cancel = text_cancel;
            return this;
        }


    }


   public interface OnClickListener {

        void onConfirmClick(View view);

        void onCancelClick(View view);

    }


}
