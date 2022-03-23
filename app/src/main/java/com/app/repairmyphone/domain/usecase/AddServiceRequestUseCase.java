package com.app.repairmyphone.domain.usecase;

import com.app.repairmyphone.data.ServiceRequestsRepository;
import com.app.repairmyphone.model.ServiceRequest;

import androidx.lifecycle.MutableLiveData;

public class AddServiceRequestUseCase {

    private final ServiceRequestsRepository serviceRequestsRepository;

    public AddServiceRequestUseCase(ServiceRequestsRepository serviceRequestsRepository) {
        this.serviceRequestsRepository = serviceRequestsRepository;
    }

    public void execute(ServiceRequest serviceRequest, MutableLiveData<Boolean> success) {
        serviceRequestsRepository.addServiceRequest(serviceRequest, success);
    }

    public void updateServiceRequest(ServiceRequest serviceRequest, MutableLiveData<Boolean> success) {
        serviceRequestsRepository.updateServiceRequest(serviceRequest, success);
    }
}
