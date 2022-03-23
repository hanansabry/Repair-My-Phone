package com.app.repairmyphone.presentation.viewmodels;

import com.app.repairmyphone.Injection;
import com.app.repairmyphone.domain.usecase.AddServiceUseCase;
import com.app.repairmyphone.model.Service;

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
