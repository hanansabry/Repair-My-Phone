package com.app.cms.domain;

import com.app.cms.data.ServiceRequestsRepository;
import com.app.cms.model.MaintenanceCenter;
import com.app.cms.model.ServiceRequest;
import com.app.cms.presentation.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class ServiceRequestsRepositoryImpl implements ServiceRequestsRepository {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Constants.SERVICE_REQUEST_NODE);

    @Override
    public void addServiceRequest(ServiceRequest serviceRequest, MutableLiveData<Boolean> success) {
        databaseReference.push().setValue(serviceRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                success.setValue(task.isSuccessful());
            }
        });
    }

    @Override
    public void retrieveServiceRequestByCenterId(String centerId, MutableLiveData<List<ServiceRequest>> serviceRequestListMutableLiveData) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ServiceRequest> serviceRequests = new ArrayList<>();
                for (DataSnapshot serviceRequestSnapshot : snapshot.getChildren()) {
                    ServiceRequest serviceRequest = serviceRequestSnapshot.getValue(ServiceRequest.class);
                    serviceRequest.setId(serviceRequestSnapshot.getKey());
                    if (serviceRequest.getCenterId().equals(centerId)) {
                        serviceRequests.add(serviceRequest);
                    }
                }
                serviceRequestListMutableLiveData.setValue(serviceRequests);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                serviceRequestListMutableLiveData.setValue(null);
            }
        });
    }

    @Override
    public void retrieveServiceRequestsByClientPhone(String phoneNUmber, MutableLiveData<List<ServiceRequest>> serviceRequestListMutableLiveData) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ServiceRequest> serviceRequests = new ArrayList<>();
                for (DataSnapshot serviceRequestSnapshot : snapshot.getChildren()) {
                    ServiceRequest serviceRequest = serviceRequestSnapshot.getValue(ServiceRequest.class);
                    serviceRequest.setId(serviceRequestSnapshot.getKey());
                    if (serviceRequest.getPhone().equals(phoneNUmber)) {
                        serviceRequests.add(serviceRequest);
                    }
                }
                serviceRequestListMutableLiveData.setValue(serviceRequests);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                serviceRequestListMutableLiveData.setValue(null);
            }
        });
    }

    @Override
    public void updateServiceRequest(ServiceRequest serviceRequest, MutableLiveData<Boolean> success) {
        databaseReference.child(serviceRequest.getId()).setValue(serviceRequest).addOnCompleteListener(task -> {
            success.setValue(task.isSuccessful());
        });
    }
}
