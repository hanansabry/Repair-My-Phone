package com.app.cms.data;

import com.app.cms.model.ServiceRequest;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface ServiceRequestsRepository {

    void retrieveServiceRequestByCenterId(String centerId, MutableLiveData<List<ServiceRequest>> serviceRequestListMutableLiveData);
}
