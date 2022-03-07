package com.app.cms.data;

import com.app.cms.model.Category;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface CategoriesRepository {

    void addNewCategory(String categoryName, MutableLiveData<Boolean> success);

    void retrieveAllCategories(MutableLiveData<List<String>> categoryListLiveData);
}
