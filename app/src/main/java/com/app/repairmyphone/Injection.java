package com.app.repairmyphone;

import com.app.repairmyphone.data.CategoriesRepository;
import com.app.repairmyphone.data.MaintenanceCenterRepository;
import com.app.repairmyphone.data.ServiceRequestsRepository;
import com.app.repairmyphone.data.ServicesRepository;
import com.app.repairmyphone.domain.CategoriesRepositoryImpl;
import com.app.repairmyphone.domain.MaintenanceCenterRepositoryImpl;
import com.app.repairmyphone.domain.ServiceRequestsRepositoryImpl;
import com.app.repairmyphone.domain.ServicesRepositoryImpl;
import com.app.repairmyphone.domain.usecase.AddCategoryUseCase;
import com.app.repairmyphone.domain.usecase.AddCenterUseCase;
import com.app.repairmyphone.domain.usecase.AddServiceRequestUseCase;
import com.app.repairmyphone.domain.usecase.AddServiceUseCase;
import com.app.repairmyphone.domain.usecase.RetrieveCenterUseCase;
import com.app.repairmyphone.domain.usecase.LoginUseCase;
import com.app.repairmyphone.domain.usecase.RegisterUseCase;
import com.app.repairmyphone.domain.usecase.RetrieveCategoriesUseCase;
import com.app.repairmyphone.domain.usecase.RetrieveCentersListUseCase;
import com.app.repairmyphone.domain.usecase.RetrieveRequestsUseCase;
import com.app.repairmyphone.domain.usecase.RetrieveServicesUseCase;

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

    public static RetrieveCenterUseCase getCenterUseCase() {
        return new RetrieveCenterUseCase(getMaintenanceCenterRepository());
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

    public static RetrieveCentersListUseCase getRetrieveCentersUseCase() {
        return new RetrieveCentersListUseCase(getMaintenanceCenterRepository());
    }

    public static AddCategoryUseCase getAddCategoryUseCase() {
        return new AddCategoryUseCase(getCategoriesRepository());
    }

    public static AddServiceRequestUseCase getAddServiceRequestUseCase() {
        return new AddServiceRequestUseCase(getServicesRequestsRepository());
    }
}
