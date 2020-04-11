package com.example.eor;

class Chat {

    String Chat,userid;
    long timecreated;

    public Chat() {
    }

    public Chat(String chat, String userid,long time) {
        this.Chat = chat;
        this.userid = userid;
        this.timecreated = time;
    }

    public String getChat() {
        return Chat;
    }

    public void setChat(String chat) {
        Chat = chat;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public long getTimecreated(){ return timecreated; }
}
