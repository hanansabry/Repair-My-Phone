package com.app.cms.domain;

import com.app.cms.data.ServicesRepository;
import com.app.cms.model.MaintenanceCenter;
import com.app.cms.model.Service;
import com.app.cms.presentation.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class ServicesRepositoryImpl implements ServicesRepository {

    private final DatabaseReference servicesReference = FirebaseDatabase.getInstance().getReference(Constants.SERVICES_NODE);
    private final DatabaseReference centersReference = FirebaseDatabase.getInstance().getReference(Constants.CENTERS_NODE);

    @Override
    public void addNewService(String centerId, Service service, MutableLiveData<Boolean> success) {
        centersReference.child(centerId).child("services").push().setValue(service)
                .addOnCompleteListener(task -> success.setValue(task.isSuccessful()));
    }

    @Override
    public void retrieveServicesByCenter(String centerId, MutableLiveData<List<Service>> serviceListLiveData) {
        centersReference.child(centerId).child("services").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Service> services = new ArrayList<>();
                for (DataSnapshot servicesSnapshot : snapshot.getChildren()) {
                    Service service = servicesSnapshot.getValue(Service.class);
                    services.add(service);
                }
                serviceListLiveData.setValue(services);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                serviceListLiveData.setValue(null);
            }
        });
    }

    @Override
    public void retrieveServicesByCategory(String categoryName, MutableLiveData<List<Service>> serviceListLiveData) {
        centersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Service> services = new ArrayList<>();
                for (DataSnapshot centerSnapshot : snapshot.getChildren()) {
                    MaintenanceCenter center = centerSnapshot.getValue(MaintenanceCenter.class);
                    if (center.getCategory().equals(categoryName)) {
                        if (center.getServices() != null) {
                            for (String key : center.getServices().keySet()) {
                                services.add(center.getServices().get(key));
                            }
                        }
                    }
                }
                serviceListLiveData.setValue(services);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                serviceListLiveData.setValue(null);
            }
        });
    }
}
