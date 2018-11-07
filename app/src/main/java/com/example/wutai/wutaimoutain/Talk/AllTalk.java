package com.example.wutai.wutaimoutain.Talk;

import java.util.List;

public class AllTalk {

    private List<TalkArrBean> talkArr;

    public List<TalkArrBean> getTalkArr() {
        return talkArr;
    }

    public void setTalkArr(List<TalkArrBean> talkArr) {
        this.talkArr = talkArr;
    }

    public static class TalkArrBean {
        /**
         * userName : 杨亚杰
         * content : This is fourth test data !
         * sendTime : 2018-10-02 09:58:10
         * picArr : ["4_0.jpg","4_1.jpg","4_2.jpg","4_3.jpg","4_4.jpg"]
         * commArr : [{"name":"测试人员","time":"2018-10-3 17:02:55","content":"这是一条测试评论"}]
         * userPic : null
         */
        private String  id;
        private String userName;
        private String content;
        private String sendTime;
        private String userPic;
        private List<String> picArr;
        private List<CommArrBean> commArr;
        public String  getTalk_id(){
            return id;
        }
        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public String getUserPic() {
            return this.userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }

        public List<String> getPicArr() {
            return picArr;
        }

        public void setPicArr(List<String> picArr) {
            this.picArr = picArr;
        }

        public List<CommArrBean> getCommArr() {
            return commArr;
        }

        public void setCommArr(List<CommArrBean> commArr) {
            this.commArr = commArr;
        }

        public static class CommArrBean {
            /**
             * name : 测试人员
             * time : 2018-10-3 17:02:55
             * content : 这是一条测试评论
             */

            private String name;
            private String time;
            private String content;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
