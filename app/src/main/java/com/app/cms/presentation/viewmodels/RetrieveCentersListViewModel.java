package com.app.cms.presentation.viewmodels;

import com.app.cms.Injection;
import com.app.cms.domain.usecase.RetrieveCentersListUseCase;
import com.app.cms.model.MaintenanceCenter;
import com.app.cms.model.Service;

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
