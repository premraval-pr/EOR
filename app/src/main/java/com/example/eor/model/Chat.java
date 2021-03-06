package com.example.eor.model;

import com.google.firebase.Timestamp;

public class Chat {

    String Chat,userid;
    Timestamp timecreated;

    public Chat() {
    }

    public Chat(String chat, String userid,Timestamp time) {
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

    public Timestamp getTimecreated(){ return timecreated; }
}
