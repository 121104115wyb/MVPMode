package com.renogy.mvpmode.data.server;

import com.renogy.mvpmode.data.bean.main.BannerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Create by 17474 on 2021/4/25.
 * Email： lishuwentimor1994@163.com
 * Describe：本地数据
 */
public class LocalData {

    private final static BannerBean bannerBean = new BannerBean();

    static {
        List<BannerBean.Banner> bannerList = new ArrayList<>();
        bannerList.add(new BannerBean.Banner(
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimgx.xiawu.com%2Fxzimg%2Fi4%2Fi3%2F10065031735436270%2FT1BZA8XmRaXXXXXXXX_%21%210-item_pic.jpg&refer=http%3A%2F%2Fimgx.xiawu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1621909996&t=ca50930800d9dabf8989683ceec5ab4e"
                , "雪女，么么哒", "https://shouji.baidu.com/software/28603458.html"));
        bannerList.add(new BannerBean.Banner(
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F201604%2F23%2F20160423222421_csP4w.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1621910033&t=a56109664f4b18af9068022789cc901b",
                "少司命，你为啥不说话啊", "https://baijiahao.baidu.com/s?id=1697973719290303558&wfr=spider&for=pc"));
        bannerList.add(new BannerBean.Banner(
                "https://gss0.baidu.com/70cFfyinKgQFm2e88IuM_a/forum/pic/item/81ae6bec54e736d1745167ff9b504fc2d4626923.jpg"
                , "韩信，胯下之辱", "http://live.baidu.com/m/media/pclive/pchome/live.html?room_id=4375321372&source=search"));
        bannerList.add(new BannerBean.Banner(
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg2.tapimg.com%2Fbbcode%2Fimages%2F1ba41d05c914075d3e7d1bab92d7d6b4.png&refer=http%3A%2F%2Fimg2.tapimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1621910072&t=6b97413ec9fc2accc06ad1ffd1a5647c"
                , "哇，美女啊", "https://baijiahao.baidu.com/s?id=1697913555458708690&wfr=spider&for=pc"));
        bannerBean.setBannerList(bannerList);
    }

    public static BannerBean loadBanner() {
        return bannerBean;
    }

}
