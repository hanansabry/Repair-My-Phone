package com.app.repairmyphone.data;

import com.app.repairmyphone.model.Service;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface ServicesRepository {

    void addNewService(String centerId, Service service, MutableLiveData<Boolean> success);

    void retrieveServicesByCenter(String centerId, MutableLiveData<List<Service>> serviceListLiveData);

    void retrieveServicesByCategory(String categoryId, MutableLiveData<List<String>> serviceListLiveData);
}
