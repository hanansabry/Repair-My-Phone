package com.app.repairmyphone.domain.usecase;

import com.app.repairmyphone.data.MaintenanceCenterRepository;
import com.app.repairmyphone.model.MaintenanceCenter;

import androidx.lifecycle.MutableLiveData;

public class RetrieveCenterUseCase {

    private final MaintenanceCenterRepository maintenanceCenterRepository;

    public RetrieveCenterUseCase(MaintenanceCenterRepository maintenanceCenterRepository) {
        this.maintenanceCenterRepository = maintenanceCenterRepository;
    }

    public void execute(String uId, MutableLiveData<MaintenanceCenter> centerMutableLiveData) {
        maintenanceCenterRepository.retrieveCenterById(uId, centerMutableLiveData);
    }
}
