package com.renogy.mvpmode.data.bean.main;

/**
 * @author Create by 17474 on 2021/5/7.
 * Email： lishuwentimor1994@163.com
 * Describe：
 */
public class PostImgBean {

    private String path;

    private boolean isAdd;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public PostImgBean(String path, boolean isAdd) {
        this.path = path;
        this.isAdd = isAdd;
    }
}
