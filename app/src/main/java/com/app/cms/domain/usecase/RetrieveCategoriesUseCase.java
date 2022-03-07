package com.app.cms.domain.usecase;

import com.app.cms.data.CategoriesRepository;
import com.app.cms.model.Category;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class RetrieveCategoriesUseCase {

    private final CategoriesRepository categoriesRepository;

    public RetrieveCategoriesUseCase(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public void execute(MutableLiveData<List<String>> categoryListMutableLiveData) {
        categoriesRepository.retrieveAllCategories(categoryListMutableLiveData);
    }
}
