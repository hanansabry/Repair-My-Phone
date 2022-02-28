package com.app.cms.domain;

import com.app.cms.data.CategoriesRepository;
import com.app.cms.model.Category;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class CategoriesRepositoryImpl implements CategoriesRepository {
    @Override
    public void addNewCategory(Category category, MutableLiveData<Boolean> success) {

    }

    @Override
    public void retrieveAllCategories(MutableLiveData<List<Category>> categoryListLiveData) {
        List<Category> categoryList = new ArrayList<>();
        Category c1 = new Category();
        c1.setName("Cat1");
        Category c2 = new Category();
        c2.setName("Cat2");
        categoryList.add(c1);
        categoryList.add(c2);

        categoryListLiveData.setValue(categoryList);
    }
}
