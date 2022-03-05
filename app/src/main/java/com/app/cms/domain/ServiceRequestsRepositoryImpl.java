package com.app.cms.domain;

import com.app.cms.data.ServiceRequestsRepository;
import com.app.cms.model.MaintenanceCenter;
import com.app.cms.model.ServiceRequest;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class ServiceRequestsRepositoryImpl implements ServiceRequestsRepository {
    @Override
    public void retrieveServiceRequestByCenterId(String centerId, MutableLiveData<List<ServiceRequest>> serviceRequestListMutableLiveData) {
        ServiceRequest serviceRequest1 = new ServiceRequest();
        serviceRequest1.setPhone("234564351");
        serviceRequest1.setService("service 1");
        serviceRequest1.setStatus("new");

        ServiceRequest serviceRequest2 = new ServiceRequest();
        serviceRequest2.setPhone("01014736447");
        serviceRequest2.setService("service 2");
        serviceRequest2.setStatus("pending");

        ServiceRequest serviceRequest3 = new ServiceRequest();
        serviceRequest3.setPhone("79793213");
        serviceRequest3.setService("service 3");
        serviceRequest3.setStatus("done");

        ServiceRequest serviceRequest4 = new ServiceRequest();
        serviceRequest4.setPhone("01231345");
        serviceRequest4.setService("service 4");
        serviceRequest4.setStatus("pending");

        List<ServiceRequest> list = new ArrayList<>();
        list.add(serviceRequest1);
        list.add(serviceRequest2);
        list.add(serviceRequest3);
        list.add(serviceRequest4);

        serviceRequestListMutableLiveData.setValue(list);
    }

    @Override
    public void retrieveServiceRequestsByClientPhone(String phoneNUmber, MutableLiveData<List<ServiceRequest>> serviceRequestListMutableLiveData) {
        MaintenanceCenter c1 = new MaintenanceCenter();
        c1.setName("Center 1");

        MaintenanceCenter c2 = new MaintenanceCenter();
        c2.setName("Center 2");

        ServiceRequest serviceRequest1 = new ServiceRequest();
        serviceRequest1.setPhone("234564351");
        serviceRequest1.setService("service 1");
        serviceRequest1.setStatus("new");
        serviceRequest1.setCenter(c1);

        ServiceRequest serviceRequest2 = new ServiceRequest();
        serviceRequest2.setPhone("01014736447");
        serviceRequest2.setService("service 2");
        serviceRequest2.setStatus("pending");
        serviceRequest2.setCenter(c1);

        ServiceRequest serviceRequest3 = new ServiceRequest();
        serviceRequest3.setPhone("79793213");
        serviceRequest3.setService("service 3");
        serviceRequest3.setStatus("done");
        serviceRequest3.setCenter(c2);

        ServiceRequest serviceRequest4 = new ServiceRequest();
        serviceRequest4.setPhone("01231345");
        serviceRequest4.setService("service 4");
        serviceRequest4.setStatus("pending");
        serviceRequest4.setCenter(c2);

        List<ServiceRequest> list = new ArrayList<>();
        list.add(serviceRequest1);
        list.add(serviceRequest2);
        list.add(serviceRequest3);
        list.add(serviceRequest4);

        serviceRequestListMutableLiveData.setValue(list);
    }
}
