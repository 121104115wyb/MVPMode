package com.renogy.mvpmode.data.bean.main;

import com.renogy.mvpmode.base.request.BaseRequest;

/**
 * @author Create by 17474 on 2021/4/20.
 * Email： lishuwentimor1994@163.com
 * Describe：模拟搜索
 */
public class SearchRequest extends BaseRequest {

    public static SearchRequest of(String fuzzyContent) {
        return new SearchRequest(fuzzyContent);
    }

    //搜索内容
    private String fuzzyContent;

    public SearchRequest(String fuzzyContent) {
        this.fuzzyContent = fuzzyContent;
    }

    public void setFuzzyContent(String fuzzyContent) {
        this.fuzzyContent = fuzzyContent;
    }

    public String getFuzzyContent() {
        return fuzzyContent;
    }
}
