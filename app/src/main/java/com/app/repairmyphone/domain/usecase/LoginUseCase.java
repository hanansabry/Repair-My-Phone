package com.app.repairmyphone.domain.usecase;

import com.app.repairmyphone.data.MaintenanceCenterRepository;

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
