package com.example.eor;

class Chat {

    String Chat;
    int usericon;
    boolean isMe;

    public Chat() {
    }

    public Chat(String chat, int usericon, boolean isMe) {
        Chat = chat;
        this.usericon = usericon;
        this.isMe = isMe;
    }

    public String getChat() {
        return Chat;
    }

    public void setChat(String chat) {
        Chat = chat;
    }

    public int getUsericon() {
        return usericon;
    }

    public void setUsericon(int usericon) {
        this.usericon = usericon;
    }

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "Chat='" + Chat + '\'' +
                ", usericon=" + usericon +
                ", isMe=" + isMe +
                '}';
    }
}
