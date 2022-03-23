package com.app.repairmyphone.data;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface CategoriesRepository {

    void addNewCategory(String categoryName, MutableLiveData<Boolean> success);

    void retrieveAllCategories(MutableLiveData<List<String>> categoryListLiveData);
}
