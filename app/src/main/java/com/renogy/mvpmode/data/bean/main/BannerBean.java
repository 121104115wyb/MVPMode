package com.renogy.mvpmode.data.bean.main;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Create by 17474 on 2021/4/25.
 * Email： lishuwentimor1994@163.com
 * Describe：主页面banner功能
 */
public class BannerBean {

    private List<Banner> bannerList;

    public List<Banner> getBannerList() {
        return bannerList;
    }

    public List<String> getTipList() {
        if (bannerList == null) return null;
        List<String> tips = new ArrayList<>(bannerList.size());
        for (Banner banner : bannerList) {
            tips.add(banner.getTips());
        }
        return tips;
    }

    public void setBannerList(List<Banner> bannerList) {
        this.bannerList = bannerList;
    }

    public static class Banner {

        private String url;
        private String tips;
        private String skipUrl;

        public Banner(String url, String tips, String skipUrl) {
            this.url = url;
            this.tips = tips;
            this.skipUrl = skipUrl;
        }

        public String getSkipUrl() {
            return skipUrl;
        }

        public void setSkipUrl(String skipUrl) {
            this.skipUrl = skipUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }
    }


}
