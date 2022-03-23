package com.app.repairmyphone.presentation.viewmodels;

import com.app.repairmyphone.Injection;
import com.app.repairmyphone.domain.usecase.AddCategoryUseCase;

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
