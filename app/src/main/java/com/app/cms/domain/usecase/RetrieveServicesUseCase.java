package com.app.cms.domain.usecase;

import com.app.cms.data.ServicesRepository;
import com.app.cms.model.Service;

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

    public void executeByCategoryId(String categoryId, MutableLiveData<List<Service>> serviceListLiveData) {
        servicesRepository.retrieveServicesByCategory(categoryId, serviceListLiveData);
    }
}
