package com.app.repairmyphone.domain.usecase;

import com.app.repairmyphone.data.ServicesRepository;
import com.app.repairmyphone.model.Service;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class RetrieveServicesUseCase {

    private final ServicesRepository servicesRepository;

    public RetrieveServicesUseCase(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public void executeByCenterId(String centerId, MutableLiveData<List<Service>> serviceListLiveData) {
        servicesRepository.retrieveServicesByCenter(centerId, serviceListLiveData);
    }

    public void executeByCategoryId(String categoryId, MutableLiveData<List<String>> serviceListLiveData) {
        servicesRepository.retrieveServicesByCategory(categoryId, serviceListLiveData);
    }
}
