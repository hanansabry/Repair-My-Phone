package com.app.cms.presentation.viewmodels;

import com.app.cms.Injection;
import com.app.cms.domain.usecase.GetCenterUseCase;
import com.app.cms.model.MaintenanceCenter;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GetCenterViewModel extends ViewModel {

    private final GetCenterUseCase getCenterUseCase;
    private MutableLiveData<MaintenanceCenter> maintenanceCenterMutableLiveData = new MutableLiveData<>();

    public GetCenterViewModel() {
        getCenterUseCase = Injection.getCenterUseCase();
    }

    public void getCenterById(String uId) {
        getCenterUseCase.execute(uId, maintenanceCenterMutableLiveData);
    }

    public MutableLiveData<MaintenanceCenter> getMaintenanceCenterLiveData() {
        return maintenanceCenterMutableLiveData;
    }
}
