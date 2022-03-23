package com.app.repairmyphone.presentation.viewmodels;

import com.app.repairmyphone.Injection;
import com.app.repairmyphone.domain.usecase.RetrieveRequestsUseCase;
import com.app.repairmyphone.model.ServiceRequest;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetrieveRequestsViewModel extends ViewModel {

    private final RetrieveRequestsUseCase retrieveRequestsUseCase;
    private MutableLiveData<List<ServiceRequest>> serviceRequestsMutableLiveData = new MutableLiveData<>();

    public RetrieveRequestsViewModel() {
        retrieveRequestsUseCase = Injection.getRetrieveRequestsUseCase();
    }

    public void getServiceRequestsByCenterId(String centerId) {
        retrieveRequestsUseCase.executeByCenterId(centerId, serviceRequestsMutableLiveData);
    }

    public void getServiceRequestsByClientPhone(String phoneNumber) {
        retrieveRequestsUseCase.executeByClientPhone(phoneNumber, serviceRequestsMutableLiveData);
    }

    public MutableLiveData<List<ServiceRequest>> getServiceRequestsLiveData() {
        return serviceRequestsMutableLiveData;
    }
}
