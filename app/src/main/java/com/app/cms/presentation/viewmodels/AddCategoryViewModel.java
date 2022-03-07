package com.app.cms.presentation.viewmodels;

import com.app.cms.Injection;
import com.app.cms.domain.usecase.AddCategoryUseCase;
import com.app.cms.model.Category;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddCategoryViewModel extends ViewModel {

    private final AddCategoryUseCase addCategoryUseCase;
    private MutableLiveData<Boolean> success = new MutableLiveData<>();

    public AddCategoryViewModel() {
        addCategoryUseCase = Injection.getAddCategoryUseCase();
    }

    public void addNewCategory(String categoryName) {
        addCategoryUseCase.execute(categoryName, success);
    }

    public MutableLiveData<Boolean> isCategoryAdded() {
        return success;
    }
}
