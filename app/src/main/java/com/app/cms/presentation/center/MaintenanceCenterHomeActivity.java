package com.app.cms.presentation.center;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import com.app.cms.R;


public class MaintenanceCenterHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_center_home);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.servicesView)
    public void onServicesClicked() {
        startActivity(new Intent(this, ServicesActivity.class));
    }

    @OnClick(R.id.requestsView)
    public void onRequestsView() {
        startActivity(new Intent(this, RequestsActivity.class));
    }
}