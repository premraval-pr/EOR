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

    public MyMessagesData(String __textview_PostIDForMessageView, String __textview_NameForMessageView, String __textview_MessageShort) {
        this.__textview_PostIDForMessageView = __textview_PostIDForMessageView;
        this.__textview_NameForMessageView = __textview_NameForMessageView;
        this.__textview_MessageShort = __textview_MessageShort;
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
