package com.app.cms.presentation.viewmodels;

import com.app.cms.Injection;
import com.app.cms.domain.usecase.AddCenterUseCase;
import com.app.cms.domain.usecase.LoginUseCase;
import com.app.cms.domain.usecase.RegisterUseCase;
import com.app.cms.model.LocationAttr;
import com.app.cms.model.MaintenanceCenter;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {

    private final RegisterUseCase registerUseCase;
    private final AddCenterUseCase addCenterUseCase;
    private MutableLiveData<MaintenanceCenter> centerLiveDate = new MutableLiveData<>();
    private MutableLiveData<Boolean> addCenterSuccess = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public RegisterViewModel() {
        registerUseCase = Injection.getRegisterUseCase();
        addCenterUseCase = Injection.getAddCenterUseCase();
    }

    public void addNewCenter(MaintenanceCenter center) {
        addCenterUseCase.execute(center, addCenterSuccess);
    }

    public void register(String name, String region, Double lang, Double lat, String category, String phone,
                         String email, String password, String confirmPassword) {
        if (validate(name, region, lang, lat, category, phone, email, password, confirmPassword)) {
            MaintenanceCenter center = new MaintenanceCenter();
            center.setName(name);
            center.setPhone(phone);
            center.setEmail(email);
            center.setPassword(password);
            center.setRegion(region);
            center.setLocation(new LocationAttr(lang, lat));
            center.setCategory(category);

            registerUseCase.execute(center, centerLiveDate);
        }
    }

    private boolean validate(String name, String region, Double lang, Double lat, String category, String phone,
                             String email, String password, String confirmPassword) {
        boolean isValid = true;

        if (name == null || name.isEmpty()) {
            errorMessage.setValue("Required");
            isValid = false;
        }
        if (region == null || region.isEmpty()) {
            errorMessage.setValue("Required");
            isValid = false;
        }
        if (lang == null) {
            errorMessage.setValue("Required");
            isValid = false;
        }
        if (lat == null) {
            errorMessage.setValue("Required");
            isValid = false;
        }
        if (category == null || category.isEmpty()) {
            errorMessage.setValue("Required");
            isValid = false;
        }
        if (phone == null || phone.isEmpty()) {
            errorMessage.setValue("Required");
            isValid = false;
        }
        if (email == null || email.isEmpty()) {
            errorMessage.setValue("Required");
            isValid = false;
        }
        if (password == null || password.isEmpty()) {
            errorMessage.setValue("Required");
            isValid = false;
        }
        if (confirmPassword == null || confirmPassword.isEmpty()) {
            errorMessage.setValue("Required");
            isValid = false;
        }
        return isValid;
    }

    public MutableLiveData<MaintenanceCenter> getCenterLiveDate() {
        return centerLiveDate;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<Boolean> isCenterAddedSuccessfully() {
        return addCenterSuccess;
    }
}
