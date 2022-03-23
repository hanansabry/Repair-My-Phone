package com.app.repairmyphone.domain.usecase;

import com.app.repairmyphone.data.ServiceRequestsRepository;
import com.app.repairmyphone.model.ServiceRequest;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class RetrieveRequestsUseCase {

    private final ServiceRequestsRepository serviceRequestsRepository;

    public RetrieveRequestsUseCase(ServiceRequestsRepository serviceRequestsRepository) {
        this.serviceRequestsRepository = serviceRequestsRepository;
    }

    public void executeByCenterId(String centerId, MutableLiveData<List<ServiceRequest>> serviceRequestListMutableLiveData) {
        serviceRequestsRepository.retrieveServiceRequestByCenterId(centerId, serviceRequestListMutableLiveData);
    }

    public void executeByClientPhone(String phoneNumber, MutableLiveData<List<ServiceRequest>> serviceRequestListMutableLiveData) {
        serviceRequestsRepository.retrieveServiceRequestsByClientPhone(phoneNumber, serviceRequestListMutableLiveData);
    }
}
