package com.app.cms.domain.usecase;

import com.app.cms.data.MaintenanceCenterRepository;
import com.app.cms.model.MaintenanceCenter;

import androidx.lifecycle.MutableLiveData;

public class GetCenterUseCase {

    private final MaintenanceCenterRepository maintenanceCenterRepository;

    public GetCenterUseCase(MaintenanceCenterRepository maintenanceCenterRepository) {
        this.maintenanceCenterRepository = maintenanceCenterRepository;
    }

    public void execute(String uId, MutableLiveData<MaintenanceCenter> centerMutableLiveData) {
        maintenanceCenterRepository.retrieveCenterById(uId, centerMutableLiveData);
    }
}
