package com.app.cms.presentation.viewmodels;

import com.app.cms.Injection;
import com.app.cms.domain.usecase.AddServiceUseCase;
import com.app.cms.model.Service;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddServiceViewModel extends ViewModel {

    private final AddServiceUseCase addServiceUseCase;
    private MutableLiveData<Boolean> success = new MutableLiveData<>();

    public AddServiceViewModel() {
        addServiceUseCase = Injection.getAddServiceUseCase();
    }

    public void addNewService(String centerId, Service service) {
        addServiceUseCase.execute(centerId, service, success);
    }

    public MutableLiveData<Boolean> getSuccess() {
        return success;
    }
}
