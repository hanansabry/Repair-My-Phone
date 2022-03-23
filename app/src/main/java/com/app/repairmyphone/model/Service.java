package com.app.repairmyphone.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Service implements Parcelable {

    private String id;
    private String name;
    private double priceRate;
    private String category;

    public Service() {
    }

    protected Service(Parcel in) {
        id = in.readString();
        name = in.readString();
        priceRate = in.readDouble();
        category = in.readString();
    }

    public static final Creator<Service> CREATOR = new Creator<Service>() {
        @Override
        public Service createFromParcel(Parcel in) {
            return new Service(in);
        }

        @Override
        public Service[] newArray(int size) {
            return new Service[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(double priceRate) {
        this.priceRate = priceRate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeDouble(priceRate);
        dest.writeString(category);
    }
}
