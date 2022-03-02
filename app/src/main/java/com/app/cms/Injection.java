package com.app.cms;

import com.app.cms.data.CategoriesRepository;
import com.app.cms.data.MaintenanceCenterRepository;
import com.app.cms.data.ServiceRequestsRepository;
import com.app.cms.data.ServicesRepository;
import com.app.cms.domain.CategoriesRepositoryImpl;
import com.app.cms.domain.MaintenanceCenterRepositoryImpl;
import com.app.cms.domain.ServiceRequestsRepositoryImpl;
import com.app.cms.domain.ServicesRepositoryImpl;
import com.app.cms.domain.usecase.AddCenterUseCase;
import com.app.cms.domain.usecase.AddServiceUseCase;
import com.app.cms.domain.usecase.GetCenterUseCase;
import com.app.cms.domain.usecase.LoginUseCase;
import com.app.cms.domain.usecase.RegisterUseCase;
import com.app.cms.domain.usecase.RetrieveCategoriesUseCase;
import com.app.cms.domain.usecase.RetrieveRequestsUseCase;
import com.app.cms.domain.usecase.RetrieveServicesUseCase;
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

    public static GetCenterUseCase getCenterUseCase() {
        return new GetCenterUseCase(getMaintenanceCenterRepository());
    }


    public static RetrieveServicesUseCase getRetrieveServicesUseCase() {
        return new RetrieveServicesUseCase(getServicesRepository());
    }

    private static ServicesRepository getServicesRepository() {
        return new ServicesRepositoryImpl();
    }

    public static AddServiceUseCase getAddServiceUseCase() {
        return new AddServiceUseCase(getServicesRepository());
    }

    public static RetrieveRequestsUseCase getRetrieveRequestsUseCase() {
        return new RetrieveRequestsUseCase(getServicesRequestsRepository());
    }

    private static ServiceRequestsRepository getServicesRequestsRepository() {
        return new ServiceRequestsRepositoryImpl();
    }
}
