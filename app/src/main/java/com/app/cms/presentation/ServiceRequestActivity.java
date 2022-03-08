package com.app.cms.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.cms.R;
import com.app.cms.model.MaintenanceCenter;
import com.app.cms.model.ServiceRequest;
import com.app.cms.presentation.viewmodels.AddServiceRequestViewModel;
import com.app.cms.presentation.viewmodels.RetrieveCenterViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceRequestActivity extends AppCompatActivity {

    @BindView(R.id.centerNameEditText)
    EditText centerNameEditText;
    @BindView(R.id.phoneEditText)
    EditText phoneEditText;
    @BindView(R.id.issueEditText)
    EditText issueEditText;
    @BindView(R.id.serviceEditText)
    EditText serviceEditText;
    @BindView(R.id.regionEditText)
    EditText regionEditText;
    @BindView(R.id.offerEditText)
    EditText offerEditText;
    @BindView(R.id.feedbackEditText)
    EditText feedbackEditText;
    @BindView(R.id.commentEditText)
    EditText commentEditText;
    @BindView(R.id.appointmentEditText)
    EditText appointmentEditText;
    @BindView(R.id.dealEditText)
    EditText dealEditText;
    @BindView(R.id.btnSendRequest)
    Button btnSendRequest;
    @BindView(R.id.statusRadioGroup)
    RadioGroup statusRadioGroup;
    @BindView(R.id.statusRadioGroupView)
    View statusRadioGroupView;

    private MaintenanceCenter center;
    private String serviceName;
    private String status;
    private AddServiceRequestViewModel addServiceRequestViewModel;
    private ServiceRequest serviceRequest;
    private boolean isCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_request);
        ButterKnife.bind(this);

        serviceName = getIntent().getStringExtra(Constants.SERVICE_NAME);
        status = getIntent().getStringExtra(Constants.STATUS);
        isCenter = getIntent().getBooleanExtra(Constants.IS_CENTER, false);

        if (status.equals(Constants.NEW)) {
            String centerId = getIntent().getStringExtra(Constants.CENTER_ID);
            newStatusAction(centerId);
        } else if (status.equals(Constants.PENDING)) {
            pendingStatusAction();
        } else if (status.equals(Constants.ACCEPT_REJECT)) {
            serviceRequest = (ServiceRequest) getIntent().getExtras().get(Constants.SERVICE_REQUEST);
            if (serviceRequest.getStatus().equals(Constants.NEW)) {
                newStatusAction(serviceRequest);
            } else if (serviceRequest.getStatus().equals(Constants.PENDING)) {
                userAcceptOrRejectAction(serviceRequest);
            } else if (serviceRequest.getStatus().equals(Constants.ACCEPT) || serviceRequest.getStatus().equals(Constants.REJECT)) {
                fillAllData(serviceRequest);
            }
        }

        addServiceRequestViewModel = ViewModelProviders.of(this).get(AddServiceRequestViewModel.class);
        addServiceRequestViewModel.getSuccess().observe(this, success -> {
            if (success) {
                Toast.makeText(this, "Request is send successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error while sending request, please try again later", Toast.LENGTH_SHORT).show();
            }
        });

        addServiceRequestViewModel.getError().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }

    private void fillAllData(ServiceRequest serviceRequest) {
        phoneEditText.setEnabled(false);
        issueEditText.setEnabled(false);
        serviceEditText.setEnabled(false);
        regionEditText.setEnabled(false);
        centerNameEditText.setEnabled(false);
        offerEditText.setEnabled(false);
        feedbackEditText.setEnabled(false);
        commentEditText.setEnabled(false);
        appointmentEditText.setEnabled(false);
        dealEditText.setEnabled(false);

        phoneEditText.setText(serviceRequest.getPhone());
        issueEditText.setText(serviceRequest.getIssue());
        serviceEditText.setText(serviceRequest.getService());
        regionEditText.setText(serviceRequest.getRegion());
        centerNameEditText.setText(serviceRequest.getCenterName());
        offerEditText.setText(serviceRequest.getOffer());
        commentEditText.setText(serviceRequest.getComment());
        appointmentEditText.setText(serviceRequest.getAppointment());
        dealEditText.setText(serviceRequest.getDeal());
        feedbackEditText.setText(serviceRequest.getFeedback());

        if (isCenter & serviceRequest.getStatus().equals(Constants.ACCEPT)) {
            btnSendRequest.setText("Close Request");
        } else {
            btnSendRequest.setVisibility(View.INVISIBLE);
        }
    }

    private void userAcceptOrRejectAction(ServiceRequest serviceRequest) {
        phoneEditText.setEnabled(false);
        issueEditText.setEnabled(false);
        serviceEditText.setEnabled(false);
        regionEditText.setEnabled(false);
        centerNameEditText.setEnabled(false);
        offerEditText.setEnabled(false);
        feedbackEditText.setEnabled(true);
        commentEditText.setEnabled(false);
        appointmentEditText.setEnabled(false);
        dealEditText.setEnabled(false);

        phoneEditText.setText(serviceRequest.getPhone());
        issueEditText.setText(serviceRequest.getIssue());
        serviceEditText.setText(serviceRequest.getService());
        regionEditText.setText(serviceRequest.getRegion());
        centerNameEditText.setText(serviceRequest.getCenterName());
        offerEditText.setText(serviceRequest.getOffer());
        commentEditText.setText(serviceRequest.getComment());
        appointmentEditText.setText(serviceRequest.getAppointment());
        dealEditText.setText(serviceRequest.getDeal());
        btnSendRequest.setText("Submit");
        statusRadioGroupView.setVisibility(View.VISIBLE);
    }

    private void pendingStatusAction() {
        serviceRequest = (ServiceRequest) getIntent().getExtras().get(Constants.SERVICE_REQUEST);

        phoneEditText.setEnabled(false);
        phoneEditText.setText(serviceRequest.getPhone());

        issueEditText.setEnabled(false);
        issueEditText.setText(serviceRequest.getIssue());

        serviceEditText.setEnabled(false);
        serviceEditText.setText(serviceRequest.getService());

        regionEditText.setEnabled(false);
        regionEditText.setText(serviceRequest.getRegion());

        centerNameEditText.setEnabled(false);
        centerNameEditText.setText(serviceRequest.getCenterName());

        feedbackEditText.setEnabled(false);

        offerEditText.setEnabled(true);
        commentEditText.setEnabled(true);
        appointmentEditText.setEnabled(true);
        dealEditText.setEnabled(true);
        btnSendRequest.setText("Submit");
    }

    private void newStatusAction(String centerId) {
        phoneEditText.setEnabled(true);
        issueEditText.setEnabled(true);
        serviceEditText.setEnabled(false);
        regionEditText.setEnabled(true);
        centerNameEditText.setEnabled(false);
        offerEditText.setEnabled(false);
        feedbackEditText.setEnabled(false);
        commentEditText.setEnabled(false);
        appointmentEditText.setEnabled(false);
        dealEditText.setEnabled(false);

        RetrieveCenterViewModel retrieveCenterViewModel = ViewModelProviders.of(this).get(RetrieveCenterViewModel.class);
        retrieveCenterViewModel.getCenterById(centerId);
        retrieveCenterViewModel.getMaintenanceCenterLiveData().observe(this, center -> {
            if (center != null) {
                this.center = center;
                serviceEditText.setText(serviceName);
                centerNameEditText.setText(center.getName());
            } else {
                Toast.makeText(this, "Error while retrieving center data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void newStatusAction(ServiceRequest serviceRequest) {
        phoneEditText.setEnabled(true);
        issueEditText.setEnabled(true);
        serviceEditText.setEnabled(false);
        regionEditText.setEnabled(true);
        centerNameEditText.setEnabled(false);
        offerEditText.setEnabled(false);
        feedbackEditText.setEnabled(false);
        commentEditText.setEnabled(false);
        appointmentEditText.setEnabled(false);
        dealEditText.setEnabled(false);

        serviceEditText.setText(serviceRequest.getService());
        centerNameEditText.setText(serviceRequest.getCenterName());
    }

    @OnClick(R.id.btnSendRequest)
    public void onSendRequestClicked() {
        if (status.equals(Constants.NEW)) {
            ServiceRequest serviceRequest = new ServiceRequest();
            serviceRequest.setCenterId(center.getId());
            serviceRequest.setCenterName(center.getName());
            serviceRequest.setStatus(status);
            serviceRequest.setService(serviceName);
            serviceRequest.setPhone(phoneEditText.getText().toString());
            serviceRequest.setIssue(issueEditText.getText().toString());
            serviceRequest.setRegion(regionEditText.getText().toString());

            addServiceRequestViewModel.addServiceRequest(serviceRequest);
        } else if (status.equals(Constants.PENDING)) {
            serviceRequest.setOffer(offerEditText.getText().toString());
            serviceRequest.setComment(commentEditText.getText().toString());
            serviceRequest.setAppointment(appointmentEditText.getText().toString());
            serviceRequest.setDeal(dealEditText.getText().toString());
            serviceRequest.setStatus(Constants.PENDING);
            addServiceRequestViewModel.centerSendOfferOfServiceRequest(serviceRequest);
        } else if (status.equals(Constants.ACCEPT_REJECT)) {
            if (isCenter) {
                serviceRequest.setStatus("Delivered");
                addServiceRequestViewModel.clientAcceptRejectOffer(serviceRequest, isCenter);
            } else {
                serviceRequest.setFeedback(feedbackEditText.getText().toString());
                String status = "";
                if (statusRadioGroup.getCheckedRadioButtonId() == R.id.approveStatus) {
                    status = Constants.ACCEPT;
                } else if (statusRadioGroup.getCheckedRadioButtonId() == R.id.rejectStatus) {
                    status = Constants.REJECT;
                }
                serviceRequest.setStatus(status);
                addServiceRequestViewModel.clientAcceptRejectOffer(serviceRequest, isCenter);
            }
        }
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}