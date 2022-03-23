package com.app.repairmyphone.presentation.viewmodels;

import com.app.repairmyphone.Injection;
import com.app.repairmyphone.domain.usecase.RetrieveServicesUseCase;
import com.app.repairmyphone.model.Service;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetrieveServicesViewModel extends ViewModel {

    private final RetrieveServicesUseCase retrieveServicesUseCase;
    private MutableLiveData<List<Service>> serviceListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<String>> serviceNamesListMutableLiveData = new MutableLiveData<>();

    public RetrieveServicesViewModel() {
        retrieveServicesUseCase = Injection.getRetrieveServicesUseCase();
    }

    public void retrieveServicesByCenterId(String centerId) {
        retrieveServicesUseCase.executeByCenterId(centerId, serviceListMutableLiveData);
    }

    public void retrieveServicesByCategoryId(String categoryId) {
        retrieveServicesUseCase.executeByCategoryId(categoryId, serviceNamesListMutableLiveData);
    }

    public MutableLiveData<List<Service>> getServiceListLiveData() {
        return serviceListMutableLiveData;
    }

    public MutableLiveData<List<String>> getServiceNamesListMutableLiveData() {
        return serviceNamesListMutableLiveData;
    }
}
