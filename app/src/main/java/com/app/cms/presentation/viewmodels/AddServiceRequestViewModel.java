package com.app.cms.presentation.viewmodels;

import com.app.cms.Injection;
import com.app.cms.domain.usecase.AddServiceRequestUseCase;
import com.app.cms.model.ServiceRequest;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddServiceRequestViewModel extends ViewModel {

    private final AddServiceRequestUseCase addServiceRequestUseCase;
    private MutableLiveData<Boolean> success = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public AddServiceRequestViewModel() {
        addServiceRequestUseCase = Injection.getAddServiceRequestUseCase();
    }

    public void addServiceRequest(ServiceRequest serviceRequest) {
        if (isValidNewData(serviceRequest.getPhone(), serviceRequest.getIssue(), serviceRequest.getRegion())) {
            addServiceRequestUseCase.execute(serviceRequest, success);
        } else {
            error.setValue("You must enter phone number & issue");
        }
    }

    public void centerSendOfferOfServiceRequest(ServiceRequest serviceRequest) {
        if (isValidPendingData(serviceRequest.getOffer(), serviceRequest.getComment(), serviceRequest.getAppointment(), serviceRequest.getDeal())) {
            addServiceRequestUseCase.updateServiceRequest(serviceRequest, success);
        } else {
            error.setValue("You must enter Offer & Comment & Appointment & Deal");
        }
    }

    public void clientAcceptRejectOffer(ServiceRequest serviceRequest, boolean isCenter) {
        if (isCenter) {
            addServiceRequestUseCase.updateServiceRequest(serviceRequest, success);
        } else {
            if (isValidAcceptRejectData(serviceRequest.getFeedback(), serviceRequest.getStatus())) {
                addServiceRequestUseCase.updateServiceRequest(serviceRequest, success);
            } else {
                error.setValue("You must enter Feedback & Status");
            }
        }
    }

    private boolean isValidNewData(String phone, String issue, String region) {
        return !phone.isEmpty() && !issue.isEmpty() && !region.isEmpty();
    }

    private boolean isValidPendingData(String offer, String comment, String appointment, String deal) {
        return !offer.isEmpty() && !comment.isEmpty() && !deal.isEmpty() && !appointment.isEmpty();
    }

    private boolean isValidAcceptRejectData(String feedback, String status) {
        return !feedback.isEmpty() && !status.isEmpty();
    }

    public MutableLiveData<Boolean> getSuccess() {
        return success;
    }

    public MutableLiveData<String> getError() {
        return error;
    }
}
