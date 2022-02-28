package com.app.cms.domain;

import com.app.cms.data.ServicesRepository;
import com.app.cms.model.Service;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class ServicesRepositoryImpl implements ServicesRepository {
    @Override
    public void addNewService(Service service, MutableLiveData<Boolean> success) {

    }

    @Override
    public void retrieveServicesByCenter(String centerId, MutableLiveData<List<Service>> serviceListLiveData) {

    }

    @Override
    public void retrieveServicesByCategory(String categoryId, MutableLiveData<List<Service>> serviceListLiveData) {

    }
}
