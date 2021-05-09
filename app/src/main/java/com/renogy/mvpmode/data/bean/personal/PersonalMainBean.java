package com.renogy.mvpmode.data.bean.personal;

/**
 * @author Create by 17474 on 2021/5/6.
 * Email： lishuwentimor1994@163.com
 * Describe：个人中心主页面的本地类
 */
public class PersonalMainBean {

    private int startImgRes;

    private int contentResId;

    private int endImgRes;

    public static PersonalMainBean of(int startImgRes, int contentResId, int endImgRes) {
        return new PersonalMainBean(startImgRes, contentResId, endImgRes);
    }

    public PersonalMainBean(int startImgRes, int contentResId, int endImgRes) {
        this.startImgRes = startImgRes;
        this.contentResId = contentResId;
        this.endImgRes = endImgRes;
    }

    public int getStartImgRes() {
        return startImgRes;
    }

    public void setStartImgRes(int startImgRes) {
        this.startImgRes = startImgRes;
    }

    public int getContentResId() {
        return contentResId;
    }

    public void setContentResId(int contentResId) {
        this.contentResId = contentResId;
    }

    public int getEndImgRes() {
        return endImgRes;
    }

    public void setEndImgRes(int endImgRes) {
        this.endImgRes = endImgRes;
    }
}
