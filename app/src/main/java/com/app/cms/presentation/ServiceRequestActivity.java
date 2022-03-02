package com.app.cms.presentation;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.app.cms.R;
import com.app.cms.model.ServiceRequest;

public class ServiceRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_request);
        ButterKnife.bind(this);

        ServiceRequest serviceRequest = (ServiceRequest) getIntent().getExtras().get(Constants.SERVICE_REQUEST);
        Toast.makeText(this, serviceRequest.getPhone(), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}