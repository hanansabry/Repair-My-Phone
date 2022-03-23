package com.app.repairmyphone.presentation.viewmodels;

import com.app.repairmyphone.Injection;
import com.app.repairmyphone.domain.usecase.RetrieveCenterUseCase;
import com.app.repairmyphone.model.MaintenanceCenter;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetrieveCenterViewModel extends ViewModel {

    private final RetrieveCenterUseCase retrieveCenterUseCase;
    private MutableLiveData<MaintenanceCenter> maintenanceCenterMutableLiveData = new MutableLiveData<>();

    public RetrieveCenterViewModel() {
        retrieveCenterUseCase = Injection.getCenterUseCase();
    }

    public void getCenterById(String uId) {
        retrieveCenterUseCase.execute(uId, maintenanceCenterMutableLiveData);
    }

    public MutableLiveData<MaintenanceCenter> getMaintenanceCenterLiveData() {
        return maintenanceCenterMutableLiveData;
    }
}
