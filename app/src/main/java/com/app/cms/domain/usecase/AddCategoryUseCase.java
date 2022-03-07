package com.app.cms.domain.usecase;

import com.app.cms.data.CategoriesRepository;
import com.app.cms.model.Category;

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
