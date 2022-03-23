package com.app.repairmyphone.domain.usecase;

import com.app.repairmyphone.data.MaintenanceCenterRepository;
import com.app.repairmyphone.model.MaintenanceCenter;

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
