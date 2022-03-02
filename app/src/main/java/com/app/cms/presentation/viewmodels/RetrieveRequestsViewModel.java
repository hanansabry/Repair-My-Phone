package com.app.cms.presentation.viewmodels;

import com.app.cms.Injection;
import com.app.cms.domain.usecase.RetrieveRequestsUseCase;
import com.app.cms.model.ServiceRequest;

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

    public MutableLiveData<List<ServiceRequest>> getServiceRequestsLiveData() {
        return serviceRequestsMutableLiveData;
    }
}
