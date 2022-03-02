package com.app.cms.domain.usecase;

import com.app.cms.data.ServicesRepository;
import com.app.cms.model.Service;

import androidx.lifecycle.MutableLiveData;

public class AddServiceUseCase {

    private final ServicesRepository servicesRepository;

    public AddServiceUseCase(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public void execute(Service service, MutableLiveData<Boolean> success) {
        servicesRepository.addNewService(service, success);
    }
}
