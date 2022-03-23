package com.app.repairmyphone.domain.usecase;

import com.app.repairmyphone.data.MaintenanceCenterRepository;
import com.app.repairmyphone.model.MaintenanceCenter;

import androidx.lifecycle.MutableLiveData;

public class AddCenterUseCase {

    private final MaintenanceCenterRepository maintenanceCenterRepository;

    public AddCenterUseCase(MaintenanceCenterRepository maintenanceCenterRepository) {
        this.maintenanceCenterRepository = maintenanceCenterRepository;
    }

    public void execute(MaintenanceCenter center, MutableLiveData<Boolean> success) {
        maintenanceCenterRepository.addNewCenter(center, success);
    }
}
