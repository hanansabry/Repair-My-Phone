package com.app.repairmyphone.domain.usecase;

import com.app.repairmyphone.data.ServicesRepository;
import com.app.repairmyphone.model.Service;

import androidx.lifecycle.MutableLiveData;

public class AddServiceUseCase {

    private final ServicesRepository servicesRepository;

    public AddServiceUseCase(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public void execute(String centerId, Service service, MutableLiveData<Boolean> success) {
        servicesRepository.addNewService(centerId, service, success);
    }
}
