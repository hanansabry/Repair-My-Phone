package com.app.cms.domain.usecase;

import com.app.cms.data.MaintenanceCenterRepository;

import androidx.lifecycle.MutableLiveData;

public class LoginUseCase {

    private final MaintenanceCenterRepository maintenanceCenterRepository;

    public LoginUseCase(MaintenanceCenterRepository maintenanceCenterRepository) {
        this.maintenanceCenterRepository = maintenanceCenterRepository;
    }

    public void execute(String email, String password, MutableLiveData<Boolean> success) {
        maintenanceCenterRepository.loginWithCenterCredentials(email, password, success);
    }
}
