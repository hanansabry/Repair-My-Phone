package com.app.cms.model;

public class LocationAttr {

    private String id;
    private double lang;
    private double lat;

    public LocationAttr() {
    }

    public LocationAttr(double lang, double lat) {
        this.lang = lang;
        this.lat = lat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLang() {
        return lang;
    }

    public void setLang(double lang) {
        this.lang = lang;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
