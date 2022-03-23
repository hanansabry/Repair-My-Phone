package com.app.repairmyphone.domain;

import com.app.repairmyphone.data.CategoriesRepository;
import com.app.repairmyphone.presentation.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class CategoriesRepositoryImpl implements CategoriesRepository {

    private final DatabaseReference categoriesReference = FirebaseDatabase.getInstance().getReference(Constants.CATEGORIES_NODE);
    @Override
    public void addNewCategory(String categoryName, MutableLiveData<Boolean> success) {
        categoriesReference.push().setValue(categoryName).addOnCompleteListener(task -> success.setValue(task.isSuccessful()));
    }

    @Override
    public void retrieveAllCategories(MutableLiveData<List<String>> categoryListLiveData) {
        categoriesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> categoryList = new ArrayList<>();
                for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                    HashMap<String, String> category = (HashMap<String, String>) categorySnapshot.getValue();
                    Map.Entry<String, String> entry = category.entrySet().iterator().next();
                    String categoryName =  entry.getValue();
                    categoryList.add(categoryName);
                }
                categoryListLiveData.setValue(categoryList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                categoryListLiveData.setValue(null);
            }
        });
    }
}
