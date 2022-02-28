package com.app.cms.domain;

import com.app.cms.data.MaintenanceCenterRepository;
import com.app.cms.model.MaintenanceCenter;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class MaintenanceCenterRepositoryImpl implements MaintenanceCenterRepository {
    @Override
    public void loginWithCenterCredentials(String email, String password, MutableLiveData<Boolean> success) {
        if (email.equals("hanan") && password.equals("123")) {
            success.setValue(true);
        } else {
            success.setValue(false);
        }
    }

    @Override
    public void registerNewCenter(MaintenanceCenter center, MutableLiveData<MaintenanceCenter> centerLiveData) {
        if (center.getEmail().equals("hanan") && center.getPassword().equals("123")) {
            centerLiveData.setValue(center);
        } else {
            centerLiveData.setValue(null);
        }
    }

    @Override
    public void addNewCenter(MaintenanceCenter center, MutableLiveData<Boolean> success) {
        if (center.getName().equals("hanan")) {
            success.setValue(true);
        } else {
            success.setValue(false);
        }
    }

    @Override
    public void retrieveAllCenters(MutableLiveData<List<MaintenanceCenter>> allCentersLiveData) {

    }
}
