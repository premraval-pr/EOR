/*
 * Page Use: Message Model Class for Message
 * Date Created: March 14,2020
 * Author: Karan Patel
 */

package com.example.eor.model;
public class MyMessagesData
{
    private int __imageview_icon_message;
    private String __textview_NameForMessageView;
    private String __textview_MessageShort;

    public MyMessagesData(int __imageview_icon_message, String __textview_NameForMessageView, String __textview_MessageShort) {
        this.__imageview_icon_message = __imageview_icon_message;
        this.__textview_NameForMessageView = __textview_NameForMessageView;
        this.__textview_MessageShort = __textview_MessageShort;
    }

    public int get__imageview_icon_message() {
        return __imageview_icon_message;
    }

    public void set__imageview_icon_message(int __imageview_icon_message) {
        this.__imageview_icon_message = __imageview_icon_message;
    }

    public String get__textview_NameForMessageView() {
        return __textview_NameForMessageView;
    }

    public void set__textview_NameForMessageView(String __textview_NameForMessageView) {
        this.__textview_NameForMessageView = __textview_NameForMessageView;
    }

    public String get__textview_MessageShort() {
        return __textview_MessageShort;
    }

    public void set__textview_MessageShort(String __textview_MessageShort) {
        this.__textview_MessageShort = __textview_MessageShort;
    }
}
