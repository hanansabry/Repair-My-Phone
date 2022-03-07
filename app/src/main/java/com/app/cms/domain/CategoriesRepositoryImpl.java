package com.app.cms.domain;

import com.app.cms.data.CategoriesRepository;
import com.app.cms.model.Category;
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

public class CategoriesRepositoryImpl implements CategoriesRepository {

    private final DatabaseReference categoriesReference = FirebaseDatabase.getInstance().getReference(Constants.CATEGORIES_NODE);
    @Override
    public void addNewCategory(String categoryName, MutableLiveData<Boolean> success) {
        categoriesReference.child("name").setValue(categoryName).addOnCompleteListener(task -> success.setValue(task.isSuccessful()));
    }

    @Override
    public void retrieveAllCategories(MutableLiveData<List<String>> categoryListLiveData) {
        categoriesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> categoryList = new ArrayList<>();
                for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                    categoryList.add(categorySnapshot.getValue(String.class));
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
