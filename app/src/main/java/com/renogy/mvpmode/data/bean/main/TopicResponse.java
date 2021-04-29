package com.renogy.mvpmode.data.bean.main;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Create by 17474 on 2021/4/28.
 * Email： lishuwentimor1994@163.com
 * Describe：帖子的数据
 */
public class TopicResponse {

    /**
     * contentList : [{"isFocus":1,"isLike":0,"isReply":0,"isView":0,"postInnerId":190,"postPageFavourites":0,"postPageLikes":0,"postPageReplys":0,"postPageShares":0,"postPageViews":0,"postPageVirtualFavourites":0,"postPageVirtualLikes":0,"postPageVirtualReplys":0,"postPageVirtualShares":1,"postPageVirtualViews":0,"postTitle":"马test13 标题","postUpdateDatetime":1576802549000,"postUserInnerId":3,"userImage":"http://dcbuilding--oss-test.oss-cn-beijing.aliyuncs.com/shared/image/bbbf048d-f7c9-4f41-884d-d7490d08bb45_2019-12-23%2017%3A55%3A47.jpg","userNickName":"东尼Adamccccc","userSex":1}]
     * totalSize : 22
     */

    @SerializedName("totalSize")
    private String totalSize;
    @SerializedName("postContentList")
    private List<ContentListBean> contentList;

    public String getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(String totalSize) {
        this.totalSize = totalSize;
    }

    public List<ContentListBean> getContentList() {
        return contentList;
    }

    public void setContentList(List<ContentListBean> contentList) {
        this.contentList = contentList;
    }

    public static class ContentListBean {
        /**
         * isFocus : 1
         * isLike : 0
         * isReply : 0
         * isView : 0
         * postInnerId : 190
         * postPageFavourites : 0
         * postPageLikes : 0
         * postPageReplys : 0
         * postPageShares : 0
         * postPageViews : 0
         * postPageVirtualFavourites : 0
         * postPageVirtualLikes : 0
         * postPageVirtualReplys : 0
         * postPageVirtualShares : 1
         * postPageVirtualViews : 0
         * postTitle : 马test13 标题
         * postUpdateDatetime : 1576802549000
         * postUserInnerId : 3
         * "topicInnerId":话题的id,
         * "topicName":"话题的名称",
         * userImage : http://dcbuilding--oss-test.oss-cn-beijing.aliyuncs.com/shared/image/bbbf048d-f7c9-4f41-884d-d7490d08bb45_2019-12-23%2017%3A55%3A47.jpg
         * userNickName : 东尼Adamccccc
         * userSex : 1
         * postImgCount 5 总的图片的数量
         */

        @SerializedName("isFocus")
        private String isFocus;
        @SerializedName("isLike")
        private String isLike;
        @SerializedName("isReply")
        private String isReply;
        @SerializedName("isView")
        private String isView;
        @SerializedName("postInnerId")
        private String postInnerId;
        @SerializedName("postPageFavourites")
        private String postPageFavourites;
        @SerializedName("postPageLikes")
        private String postPageLikes;
        @SerializedName("postPageReplys")
        private String postPageReplys;
        @SerializedName("postPageShares")
        private String postPageShares;
        @SerializedName("postPageViews")
        private String postPageViews;
        @SerializedName("postPageVirtualFavourites")
        private String postPageVirtualFavourites;
        @SerializedName("postPageVirtualLikes")
        private String postPageVirtualLikes;
        @SerializedName("postPageVirtualReplys")
        private String postPageVirtualReplys;
        @SerializedName("postPageVirtualShares")
        private String postPageVirtualShares;
        @SerializedName("postPageVirtualViews")
        private String postPageVirtualViews;
        @SerializedName("postTitle")
        private String postTitle;
        @SerializedName("postUpdateDatetime")
        private String postUpdateDatetime;
        @SerializedName("postUserInnerId")
        private String postUserInnerId;
        @SerializedName("topicInnerId")
        private Long topicInnerId;
        @SerializedName("topicName")
        private String topicName;
        @SerializedName("userImage")
        private String userImage;
        @SerializedName("userNickName")
        private String userNickName;
        @SerializedName("userSex")
        private String userSex;

        @SerializedName("postImg1")
        private String postImg1;
        @SerializedName("postImg2")
        private String postImg2;
        @SerializedName("postImg3")
        private String postImg3;
        @SerializedName("postImgCount")
        private int postImgCount;

        public void setPostImgCount(int postImgCount) {
            this.postImgCount = postImgCount;
        }

        public int getPostImgCount() {
            return postImgCount;
        }

        public Long getTopicInnerId() {
            return topicInnerId;
        }

        public void setTopicInnerId(Long topicInnerId) {
            this.topicInnerId = topicInnerId;
        }

        public String getTopicName() {
            return "# " + topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public String getPostImg1() {
            return postImg1;
        }

        public void setPostImg1(String postImg1) {
            this.postImg1 = postImg1;
        }

        public String getPostImg2() {
            return postImg2;
        }

        public void setPostImg2(String postImg2) {
            this.postImg2 = postImg2;
        }

        public String getPostImg3() {
            return postImg3;
        }

        public void setPostImg3(String postImg3) {
            this.postImg3 = postImg3;
        }


        public String getIsFocus() {
            return isFocus;
        }

        public void setIsFocus(String isFocus) {
            this.isFocus = isFocus;
        }

        public String getIsLike() {
            return isLike;
        }

        public void setIsLike(String isLike) {
            this.isLike = isLike;
        }

        public String getIsReply() {
            return isReply;
        }

        public void setIsReply(String isReply) {
            this.isReply = isReply;
        }

        public String getIsView() {
            return isView;
        }

        public void setIsView(String isView) {
            this.isView = isView;
        }

        public String getPostInnerId() {
            return postInnerId;
        }

        public void setPostInnerId(String postInnerId) {
            this.postInnerId = postInnerId;
        }

        public String getPostPageFavourites() {
            return postPageFavourites;
        }

        public void setPostPageFavourites(String postPageFavourites) {
            this.postPageFavourites = postPageFavourites;
        }

        public String getPostPageLikes() {
            return postPageLikes;
        }

        public void setPostPageLikes(String postPageLikes) {
            this.postPageLikes = postPageLikes;
        }

        public String getPostPageReplys() {
            return postPageReplys;
        }

        public void setPostPageReplys(String postPageReplys) {
            this.postPageReplys = postPageReplys;
        }

        public String getPostPageShares() {
            return postPageShares;
        }

        public void setPostPageShares(String postPageShares) {
            this.postPageShares = postPageShares;
        }

        public String getPostPageViews() {
            return postPageViews;
        }

        public void setPostPageViews(String postPageViews) {
            this.postPageViews = postPageViews;
        }

        public String getPostPageVirtualFavourites() {
            return postPageVirtualFavourites;
        }

        public void setPostPageVirtualFavourites(String postPageVirtualFavourites) {
            this.postPageVirtualFavourites = postPageVirtualFavourites;
        }

        public String getPostPageVirtualLikes() {
            return "点赞数："+postPageVirtualLikes;
        }

        public void setPostPageVirtualLikes(String postPageVirtualLikes) {
            this.postPageVirtualLikes = postPageVirtualLikes;
        }

        public String getPostPageVirtualReplys() {
            return "评论数："+postPageVirtualReplys;
        }

        public void setPostPageVirtualReplys(String postPageVirtualReplys) {
            this.postPageVirtualReplys = postPageVirtualReplys;
        }

        public String getPostPageVirtualShares() {
            return postPageVirtualShares;
        }

        public void setPostPageVirtualShares(String postPageVirtualShares) {
            this.postPageVirtualShares = postPageVirtualShares;
        }

        public String getPostPageVirtualViews() {
            return "浏览数："+postPageVirtualViews;
        }

        public void setPostPageVirtualViews(String postPageVirtualViews) {
            this.postPageVirtualViews = postPageVirtualViews;
        }

        public String getPostTitle() {
            return postTitle;
        }

        public void setPostTitle(String postTitle) {
            this.postTitle = postTitle;
        }

        public String getPostUpdateDatetime() {
            return postUpdateDatetime;
        }

        public void setPostUpdateDatetime(String postUpdateDatetime) {
            this.postUpdateDatetime = postUpdateDatetime;
        }

        public String getPostUserInnerId() {
            return postUserInnerId;
        }

        public void setPostUserInnerId(String postUserInnerId) {
            this.postUserInnerId = postUserInnerId;
        }

        public String getUserImage() {
            return userImage;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }

        public String getUserNickName() {
            return userNickName;
        }

        public void setUserNickName(String userNickName) {
            this.userNickName = userNickName;
        }

        public String getUserSex() {
            return userSex;
        }

        public void setUserSex(String userSex) {
            this.userSex = userSex;
        }


        //获取图片列表
//        public List<String> getImgLists() {
//            List<String> list = new ArrayList<>();
//            if (!TextUtils.isEmpty(postImg1)) {
//                list.add(postImg1);
//            }
//            if (!TextUtils.isEmpty(postImg2)) {
//                list.add(postImg2);
//            }
//            if (!TextUtils.isEmpty(postImg3)) {
//                list.add(postImg3);
//            }
//            return list;
//        }
    }

}
