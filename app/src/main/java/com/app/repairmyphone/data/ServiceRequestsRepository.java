package com.app.repairmyphone.data;

import com.app.repairmyphone.model.ServiceRequest;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface ServiceRequestsRepository {

    void addServiceRequest(ServiceRequest serviceRequest, MutableLiveData<Boolean> success);

    void retrieveServiceRequestByCenterId(String centerId, MutableLiveData<List<ServiceRequest>> serviceRequestListMutableLiveData);

    void retrieveServiceRequestsByClientPhone(String phoneNUmber, MutableLiveData<List<ServiceRequest>> serviceRequestListMutableLiveData);

    void updateServiceRequest(ServiceRequest serviceRequest, MutableLiveData<Boolean> success);
}
