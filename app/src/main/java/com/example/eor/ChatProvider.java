package com.example.eor;

import java.util.ArrayList;
import java.util.List;

public class ChatProvider {

    static List<Chat> chats = new ArrayList<Chat>();

    static {
        chats.add(new Chat("hello,I am prem.", R.drawable.prem_1, true));
        chats.add(new Chat("I am interesting in your product. Can you brief me thed etail of yor product. please", R.drawable.prem_1, true));
        chats.add(new Chat("Ofcourse.", R.drawable.nishi_1, false));
        chats.add(new Chat("3100 lumens colour brightness and 3100 lumens white brightness", R.drawable.nishi_1, false));
        chats.add(new Chat("Full HD 1080p resolution (1920 x 1200) & Equipped with 2 HDMI port", R.drawable.nishi_1, false));
        chats.add(new Chat("Comes with a remote control and batteries", R.drawable.nishi_1, false));
        chats.add(new Chat("Perfect!", R.drawable.prem_1, true));
        chats.add(new Chat("I also wanted to ask that at what time I can pick this up", R.drawable.prem_1, true));
        chats.add(new Chat("I prefer in the evening but I am flexible", R.drawable.prem_1, true));
        chats.add(new Chat("Yeah, evening 5pm looks fine to me. WBU?", R.drawable.nishi_1, false));
        chats.add(new Chat("Perfect!", R.drawable.prem_1, true));
    }
}

