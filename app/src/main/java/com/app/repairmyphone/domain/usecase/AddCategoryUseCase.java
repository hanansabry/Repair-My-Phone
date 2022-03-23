package com.app.repairmyphone.domain.usecase;

import com.app.repairmyphone.data.CategoriesRepository;

import androidx.lifecycle.MutableLiveData;

public class AddCategoryUseCase {

    private final CategoriesRepository categoriesRepository;

    public AddCategoryUseCase(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public void execute(String categoryName, MutableLiveData<Boolean> success) {
        categoriesRepository.addNewCategory(categoryName, success);
    }
}
