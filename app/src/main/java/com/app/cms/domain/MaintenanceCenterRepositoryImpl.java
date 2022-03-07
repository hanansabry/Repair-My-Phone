package com.app.cms.domain;

import com.app.cms.data.MaintenanceCenterRepository;
import com.app.cms.model.MaintenanceCenter;
import com.app.cms.model.Service;
import com.app.cms.presentation.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class MaintenanceCenterRepositoryImpl implements MaintenanceCenterRepository {
    private final FirebaseAuth auth;
    private final FirebaseDatabase database;

    public MaintenanceCenterRepositoryImpl() {
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public void loginWithCenterCredentials(String email, String password, MutableLiveData<Boolean> success) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> success.setValue(task.isSuccessful()));
    }

    @Override
    public void registerNewCenter(MaintenanceCenter center, MutableLiveData<MaintenanceCenter> centerLiveData) {
        auth.createUserWithEmailAndPassword(center.getEmail(), center.getPassword()).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                center.setId(task.getResult().getUser().getUid());
                centerLiveData.setValue(center);
            } else {
                centerLiveData.setValue(null);
            }
        });
    }

    @Override
    public void addNewCenter(MaintenanceCenter center, MutableLiveData<Boolean> success) {
        DatabaseReference databaseReference = database.getReference(Constants.CENTERS_NODE);
        databaseReference.child(center.getId()).setValue(center).addOnCompleteListener(task -> success.setValue(task.isSuccessful()));
    }

    @Override
    public void retrieveAllCenters(MutableLiveData<List<MaintenanceCenter>> allCentersLiveData) {
        DatabaseReference databaseReference = database.getReference(Constants.CENTERS_NODE);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<MaintenanceCenter> centers = new ArrayList<>();
                for (DataSnapshot centerSnapshot: dataSnapshot.getChildren()) {
                    MaintenanceCenter center = new MaintenanceCenter();
                    center.setId(centerSnapshot.getKey());
                    centers.add(center);
                }
                allCentersLiveData.setValue(centers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                allCentersLiveData.setValue(null);
            }
        });
    }

    @Override
    public void retrieveCenterById(String uId, MutableLiveData<MaintenanceCenter> centerMutableLiveData) {
        DatabaseReference databaseReference = database.getReference(Constants.CENTERS_NODE);
        databaseReference.child(uId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MaintenanceCenter center = dataSnapshot.getValue(MaintenanceCenter.class);
                center.setId(dataSnapshot.getKey());
                for (DataSnapshot centerSnapshot : dataSnapshot.getChildren()) {
                    if (centerSnapshot.getKey().equals("services")) {
                        HashMap<String, Service> serviceHashMap = (HashMap<String, Service>) centerSnapshot.getValue();
                        serviceHashMap.size();
                    }
                }
                centerMutableLiveData.setValue(center);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                centerMutableLiveData.setValue(null);
            }
        });
    }

    @Override
    public void retrieveCentersByCategoryServiceId(String categoryId, String serviceId, MutableLiveData<List<MaintenanceCenter>> centersListMutableLiveData) {
        DatabaseReference databaseReference = database.getReference(Constants.CENTERS_NODE);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<MaintenanceCenter> centers = new ArrayList<>();
                for (DataSnapshot centerSnapshot: dataSnapshot.getChildren()) {
                    MaintenanceCenter center = centerSnapshot.getValue(MaintenanceCenter.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
//                allCentersLiveData.setValue(null);
            }
        });
    }
}
