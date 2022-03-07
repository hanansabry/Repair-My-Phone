package com.app.cms.presentation.viewmodels;

import com.app.cms.Injection;
import com.app.cms.domain.usecase.RetrieveCategoriesUseCase;
import com.app.cms.model.Category;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetrieveCategoriesViewModel extends ViewModel {

    private final RetrieveCategoriesUseCase retrieveCategoriesUseCase;
    private MutableLiveData<List<String>> categoryListMutableLiveData = new MutableLiveData<>();

    public RetrieveCategoriesViewModel() {
        retrieveCategoriesUseCase = Injection.getRetrieveCategoriesUseCase();
    }

    public void retrieveCategories() {
        retrieveCategoriesUseCase.execute(categoryListMutableLiveData);
    }

    public MutableLiveData<List<String>> getCategoriesLiveData() {
        return categoryListMutableLiveData;
    }
}
