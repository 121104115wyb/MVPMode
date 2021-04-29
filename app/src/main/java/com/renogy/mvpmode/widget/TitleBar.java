package com.renogy.mvpmode.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.blankj.utilcode.util.SizeUtils;
import com.renogy.mvpmode.R;

/**
 * @author Create by 17474 on 2021/4/28.
 * Email： lishuwentimor1994@163.com
 * Describe：定义您的标题栏
 */
public class TitleBar extends ConstraintLayout {

    private ImageView leftImg;
    private ImageView rightImg;
    private TextView titleView;
    private boolean finish;
    private TitleViewClick titleViewClick;
    private LeftClick leftClick;
    private RightClick rightClick;

    public TitleBar(@NonNull Context context) {
        this(context, null);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.include_title_bar, this);
        leftImg = constraintLayout.findViewById(R.id.leftImg);
        rightImg = constraintLayout.findViewById(R.id.rightImg);
        titleView = constraintLayout.findViewById(R.id.titleText);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        leftImg.setImageResource(typedArray.getResourceId(R.styleable.TitleBar_left_img, R.drawable.icon_back));
        leftImg.setVisibility(typedArray.getBoolean(R.styleable.TitleBar_left_gone, false) ? INVISIBLE : VISIBLE);
        rightImg.setImageResource(typedArray.getResourceId(R.styleable.TitleBar_right_img, R.drawable.icon_back));
        rightImg.setVisibility(typedArray.getBoolean(R.styleable.TitleBar_right_gone, false) ? INVISIBLE : VISIBLE);
        titleView.setText(typedArray.getString(R.styleable.TitleBar_title_text));
        titleView.setTextColor(typedArray.getColor(R.styleable.TitleBar_title_textColor, context.getResources().getColor(R.color.text_title_color)));
        float defaultTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, getResources().getDisplayMetrics());
        float textSize = typedArray.getDimension(R.styleable.TitleBar_title_textSize, defaultTextSize);
        titleView.setTextSize(SizeUtils.px2sp(textSize));
        titleView.setVisibility(typedArray.getBoolean(R.styleable.TitleBar_title_gone, false) ? INVISIBLE : VISIBLE);
        finish = typedArray.getBoolean(R.styleable.TitleBar_default_finish, true);
        leftImg.setOnClickListener(v -> {
            if (finish) {
                ((Activity) context).finish();
                return;
            }
            if (leftClick != null) {
                leftClick.onClickListener(v);
                return;
            }
            if (titleViewClick != null) {
                titleViewClick.onLeftClickListener(v);
            }
        });

        rightImg.setOnClickListener(v -> {
            if (rightClick != null) {
                rightClick.onClickListener(v);
                return;
            }
            if (titleViewClick != null) {
                titleViewClick.onRightClickListener(v);
            }
        });
        typedArray.recycle();
    }


    public void setTitle(String title) {
        if (titleView != null) {
            titleView.setText(title);
        }
    }

    public void setLeftImg(@DrawableRes int resId) {
        if (leftImg != null) {
            leftImg.setImageResource(resId);
        }
    }

    public void setRightImg(@DrawableRes int resId) {
        if (rightImg != null) {
            rightImg.setImageResource(resId);
        }
    }

    public void setTitleViewClick(TitleViewClick viewClick) {
        this.titleViewClick = viewClick;
    }


    public void setLeftClick(LeftClick leftClick) {
        this.leftClick = leftClick;
    }

    public void setRightClick(RightClick rightClick) {
        this.rightClick = rightClick;
    }

    public interface LeftClick {

        void onClickListener(View view);
    }

    public interface RightClick {

        void onClickListener(View view);
    }

    public interface TitleViewClick {

        void onLeftClickListener(View view);

        void onRightClickListener(View view);
    }

}
