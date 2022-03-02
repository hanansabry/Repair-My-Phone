package com.app.cms.domain;

import com.app.cms.data.ServicesRepository;
import com.app.cms.model.Service;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class ServicesRepositoryImpl implements ServicesRepository {
    @Override
    public void addNewService(Service service, MutableLiveData<Boolean> success) {
        if (service != null) {
            success.setValue(true);
        } else {
            success.setValue(false);
        }
    }

    @Override
    public void retrieveServicesByCenter(String centerId, MutableLiveData<List<Service>> serviceListLiveData) {
        if (centerId.equals("hanan")) {
            List<Service> services = new ArrayList<>();
            Service s1 = new Service();
            s1.setName("Service 1");
            s1.setPriceRate(12.5);

            Service s2 = new Service();
            s2.setName("Service 2");
            s2.setPriceRate(48.4);

            Service s3 = new Service();
            s3.setName("Service 3");
            s3.setPriceRate(125.3);

            Service s4 = new Service();
            s4.setName("Service 4");
            s4.setPriceRate(20.5);

            services.add(s1);
            services.add(s2);
            services.add(s3);
            services.add(s4);
            serviceListLiveData.setValue(services);
        }
    }

    @Override
    public void retrieveServicesByCategory(String categoryId, MutableLiveData<List<Service>> serviceListLiveData) {

    }
}
