package com.app.cms.domain;

import com.app.cms.data.MaintenanceCenterRepository;
import com.app.cms.model.MaintenanceCenter;

import java.util.ArrayList;
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
        MaintenanceCenter center1 = new MaintenanceCenter();
        center1.setName("Hanan Maintenance Center");
        center1.setCategory("Category 1");
        center1.setPhone("20124505");
        center1.setRegion("region 1");

        MaintenanceCenter center2 = new MaintenanceCenter();
        center2.setName("Maintenance Center2");
        center2.setCategory("Category 2");
        center2.setPhone("20124505");
        center2.setRegion("region 2");

        MaintenanceCenter center3 = new MaintenanceCenter();
        center3.setName("Maintenance Center3");
        center3.setCategory("Category 3");
        center3.setPhone("20124505");
        center3.setRegion("region 3");

        MaintenanceCenter center4 = new MaintenanceCenter();
        center4.setName("Maintenance Center4");
        center4.setCategory("Category 4");
        center4.setPhone("20124505");
        center4.setRegion("region 4");

        MaintenanceCenter center5 = new MaintenanceCenter();
        center5.setName("Maintenance Center5");
        center5.setCategory("Category 5");
        center5.setPhone("20124505");
        center5.setRegion("region 5");

        List<MaintenanceCenter> centers = new ArrayList<>();
        centers.add(center1);
        centers.add(center2);
        centers.add(center3);
        centers.add(center4);
        centers.add(center5);

        allCentersLiveData.setValue(centers);
    }

    @Override
    public void retrieveCenterById(String uId, MutableLiveData<MaintenanceCenter> centerMutableLiveData) {
        if (uId.equals("hanan")) {
            MaintenanceCenter center = new MaintenanceCenter();
            center.setName("Hanan Maintenance Center");
            center.setCategory("Category 1");
            centerMutableLiveData.setValue(center);
        } else {
            centerMutableLiveData.setValue(null);
        }
    }

    @Override
    public void retrieveCentersByCategoryServiceId(String categoryId, String serviceId, MutableLiveData<List<MaintenanceCenter>> centersListMutableLiveData) {
        MaintenanceCenter center1 = new MaintenanceCenter();
        center1.setName("Hanan Maintenance Center");
        center1.setCategory("Category 1");
        center1.setPhone("20124505");
        center1.setRegion("region 1");

        MaintenanceCenter center2 = new MaintenanceCenter();
        center2.setName("Maintenance Center2");
        center2.setCategory("Category 2");
        center2.setPhone("20124505");
        center2.setRegion("region 2");

        MaintenanceCenter center3 = new MaintenanceCenter();
        center3.setName("Maintenance Center3");
        center3.setCategory("Category 3");
        center3.setPhone("20124505");
        center3.setRegion("region 3");

        MaintenanceCenter center4 = new MaintenanceCenter();
        center4.setName("Maintenance Center4");
        center4.setCategory("Category 4");
        center4.setPhone("20124505");
        center4.setRegion("region 4");

        MaintenanceCenter center5 = new MaintenanceCenter();
        center5.setName("Maintenance Center5");
        center5.setCategory("Category 5");
        center5.setPhone("20124505");
        center5.setRegion("region 5");

        List<MaintenanceCenter> centers = new ArrayList<>();
        if (categoryId.equals("c1") && serviceId.equals("s1")) {
            centers.add(center1);
            centers.add(center2);
        } else if (categoryId.equals("c1") && serviceId.equals("s3")) {
            centers.add(center3);
            centers.add(center4);
        } else if (categoryId.equals("c2") && serviceId.equals("s2")) {
            centers.add(center5);
        }

        centersListMutableLiveData.setValue(centers);
    }
}
