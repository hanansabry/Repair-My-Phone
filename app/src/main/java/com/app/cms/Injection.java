package com.app.cms;

import com.app.cms.data.CategoriesRepository;
import com.app.cms.data.MaintenanceCenterRepository;
import com.app.cms.domain.CategoriesRepositoryImpl;
import com.app.cms.domain.MaintenanceCenterRepositoryImpl;
import com.app.cms.domain.usecase.AddCenterUseCase;
import com.app.cms.domain.usecase.LoginUseCase;
import com.app.cms.domain.usecase.RegisterUseCase;
import com.app.cms.domain.usecase.RetrieveCategoriesUseCase;
import com.app.cms.presentation.viewmodels.RetrieveCategoriesViewModel;

public class Injection {
    public static RegisterUseCase getRegisterUseCase() {
        return new RegisterUseCase(getMaintenanceCenterRepository());
    }

    private static MaintenanceCenterRepository getMaintenanceCenterRepository() {
        return new MaintenanceCenterRepositoryImpl();
    }

    public static LoginUseCase getLoginUseCase() {
        return new LoginUseCase(getMaintenanceCenterRepository());
    }

    public static RetrieveCategoriesUseCase getRetrieveCategoriesUseCase() {
        return new RetrieveCategoriesUseCase(getCategoriesRepository());
    }

    private static CategoriesRepository getCategoriesRepository() {
        return new CategoriesRepositoryImpl();
    }

    public static AddCenterUseCase getAddCenterUseCase() {
        return new AddCenterUseCase(getMaintenanceCenterRepository());
    }
}
