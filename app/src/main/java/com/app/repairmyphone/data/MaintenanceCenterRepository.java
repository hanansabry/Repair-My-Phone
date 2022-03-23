package com.app.repairmyphone.data;

import com.app.repairmyphone.model.MaintenanceCenter;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface MaintenanceCenterRepository {

    void loginWithCenterCredentials(String email, String password, MutableLiveData<Boolean> success);

    void registerNewCenter(MaintenanceCenter center, MutableLiveData<MaintenanceCenter> centerLiveData);

    void addNewCenter(MaintenanceCenter center, MutableLiveData<Boolean> success);

    void retrieveAllCenters(MutableLiveData<List<MaintenanceCenter>> allCentersLiveData);

    void retrieveCenterById(String uId, MutableLiveData<MaintenanceCenter> centerMutableLiveData);

    void retrieveCentersByCategoryServiceId(String categoryId, String serviceName, MutableLiveData<List<MaintenanceCenter>> centersListMutableLiveData);
}
