package com.app.cms.domain.usecase;

import com.app.cms.data.MaintenanceCenterRepository;
import com.app.cms.model.MaintenanceCenter;

import androidx.lifecycle.MutableLiveData;

public class RegisterUseCase {

    private final MaintenanceCenterRepository maintenanceCenterRepository;

    public RegisterUseCase(MaintenanceCenterRepository maintenanceCenterRepository) {
        this.maintenanceCenterRepository = maintenanceCenterRepository;
    }

    public void execute(MaintenanceCenter center, MutableLiveData<MaintenanceCenter> centerLiveData) {
        maintenanceCenterRepository.registerNewCenter(center, centerLiveData);
    }
}
