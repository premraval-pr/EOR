/*
 * Page Use: Message Model Class for Message
 * Date Created: March 14,2020
 * Author: Karan Patel
 */

package com.example.eor.model;
public class MyMessagesData
{
    private String __textview_PostIDForMessageView;
    private String __textview_NameForMessageView;
    private String __textview_MessageShort;
    private String __imageview_Message;
    private String __username_Message;
    private String __firebaseChatID;

    public String get__firebaseChatID() {
        return __firebaseChatID;
    }

    public void set__firebaseChatID(String __firebaseChatID) {
        this.__firebaseChatID = __firebaseChatID;
    }

    public String get__username_Message() {
        return __username_Message;
    }

    public void set__username_Message(String __username_Message) {
        this.__username_Message = __username_Message;
    }

    public String get__location_Message() {
        return __location_Message;
    }

    public void set__location_Message(String __location_Message) {
        this.__location_Message = __location_Message;
    }

    public MyMessagesData(String __textview_PostIDForMessageView,String __firebaseChatID, String __textview_NameForMessageView,
                          String __textview_MessageShort,
                          String __imageview_Message,
                          String __username_Message,  String __location_Message) {
        this.__textview_PostIDForMessageView = __textview_PostIDForMessageView;
        this.__textview_NameForMessageView = __textview_NameForMessageView;
        this.__textview_MessageShort = __textview_MessageShort;
        this.__imageview_Message = __imageview_Message;
        this.__username_Message = __username_Message;
        this.__firebaseChatID = __firebaseChatID;
        this.__location_Message = __location_Message;
    }

    private String __location_Message;


    public String get__imageview_Message() {
        return __imageview_Message;
    }

    public void set__imageview_Message(String __imageview_Message) {
        this.__imageview_Message = __imageview_Message;
    }

    public String get__textview_PostIDForMessageView() {
        return __textview_PostIDForMessageView;
    }

    public void set__textview_PostIDForMessageView(String __textview_PostIDForMessageView) {
        this.__textview_PostIDForMessageView = __textview_PostIDForMessageView;
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
