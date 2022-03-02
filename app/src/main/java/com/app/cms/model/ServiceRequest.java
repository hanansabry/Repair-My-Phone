package com.app.cms.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ServiceRequest implements Parcelable {

    private String id;
    private String phone;
    private MaintenanceCenter center;
    private String issue;
    private String service;
    private String region;
    private String offer;
    private String feedback;
    private String comment;
    private Date appointment;
    private String status;

    public ServiceRequest() {
    }

    protected ServiceRequest(Parcel in) {
        id = in.readString();
        phone = in.readString();
        issue = in.readString();
        service = in.readString();
        region = in.readString();
        offer = in.readString();
        feedback = in.readString();
        comment = in.readString();
        status = in.readString();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MaintenanceCenter getCenter() {
        return center;
    }

    public void setCenter(MaintenanceCenter center) {
        this.center = center;
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

    public Date getAppointment() {
        return appointment;
    }

    public void setAppointment(Date appointment) {
        this.appointment = appointment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(phone);
        dest.writeString(issue);
        dest.writeString(service);
        dest.writeString(region);
        dest.writeString(offer);
        dest.writeString(feedback);
        dest.writeString(comment);
        dest.writeString(status);
    }
}
