package com.app.repairmyphone.domain.usecase;

import com.app.repairmyphone.data.CategoriesRepository;

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
