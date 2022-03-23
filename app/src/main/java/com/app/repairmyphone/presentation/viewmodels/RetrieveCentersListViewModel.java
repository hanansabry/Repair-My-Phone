package com.app.repairmyphone.presentation.viewmodels;

import com.app.repairmyphone.Injection;
import com.app.repairmyphone.domain.usecase.RetrieveCentersListUseCase;
import com.app.repairmyphone.model.MaintenanceCenter;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetrieveCentersListViewModel extends ViewModel {

    private final RetrieveCentersListUseCase retrieveCentersListUseCase;
    private MutableLiveData<List<MaintenanceCenter>> centersListMutableLiveData = new MutableLiveData<>();

    public RetrieveCentersListViewModel() {
        retrieveCentersListUseCase = Injection.getRetrieveCentersUseCase();
    }

    public void retrieveAllCenters() {
        retrieveCentersListUseCase.execute(centersListMutableLiveData);
    }

    public void retrieveCentersByCategoryServiceId(String categoryId, String serviceName) {
        retrieveCentersListUseCase.executeByCategoryServiceId(categoryId, serviceName, centersListMutableLiveData);
    }

    public MutableLiveData<List<MaintenanceCenter>> getCentersListLiveData() {
        return centersListMutableLiveData;
    }
}
