package com.example.eor;

public class POST_EXPLOREPOSTACTIVITY
{
    private String __id;
    private String __itemTitle;
    private String __location;
    private String __userName;
    private double __price;

    public POST_EXPLOREPOSTACTIVITY() {
    }

    public POST_EXPLOREPOSTACTIVITY(String __id, String __itemTitle, String __location,double __price,String __userName) {
        this.__id = __id;
        this.__itemTitle = __itemTitle;
        this.__location = __location;
        this.__price=__price;
        this.__userName=__userName;
    }

    public String get__id() {
        return __id;
    }

    public void set__id(String __id) {
        this.__id = __id;
    }

    public String get__itemTitle() {
        return __itemTitle;
    }

    public void set__itemTitle(String __itemTitle) {
        this.__itemTitle = __itemTitle;
    }

    public String get__location() {
        return __location;
    }

    public void set__location(String __location) {
        this.__location = __location;
    }

    public double get__price() {
        return __price;
    }

    public void set__price(double __price) {
        this.__price = __price;
    }

    public String get__userName() {
        return __userName;
    }

    public void set__userName(String __userName) {
        this.__userName = __userName;
    }

    @Override
    public String toString() {
        return "POST_EXPLOREPOSTACTIVITY{" +
                "__id='" + __id + '\'' +
                ", __itemTitle='" + __itemTitle + '\'' +
                ", __location='" + __location + '\'' +
                ", __userName='" + __userName + '\'' +
                ", __price=" + __price +
                '}';
    }
}
