package com.app.repairmyphone.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceRequest implements Parcelable {

    private String id;
    private String phone;
    private String centerId;
    private String centerName;
    private String issue;
    private String service;
    private String region;
    private String offer;
    private String feedback;
    private String comment;
    private String appointment;
    private String status;
    private String deal;

    public ServiceRequest() {
    }

    protected ServiceRequest(Parcel in) {
        id = in.readString();
        phone = in.readString();
        centerId = in.readString();
        centerName = in.readString();
        issue = in.readString();
        service = in.readString();
        region = in.readString();
        offer = in.readString();
        feedback = in.readString();
        comment = in.readString();
        appointment = in.readString();
        status = in.readString();
        deal = in.readString();
    }

    public static final Creator<ServiceRequest> CREATOR = new Creator<ServiceRequest>() {
        @Override
        public ServiceRequest createFromParcel(Parcel in) {
            return new ServiceRequest(in);
        }

        @Override
        public ServiceRequest[] newArray(int size) {
            return new ServiceRequest[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(phone);
        dest.writeString(centerId);
        dest.writeString(centerName);
        dest.writeString(issue);
        dest.writeString(service);
        dest.writeString(region);
        dest.writeString(offer);
        dest.writeString(feedback);
        dest.writeString(comment);
        dest.writeString(appointment);
        dest.writeString(status);
        dest.writeString(deal);
    }
}
