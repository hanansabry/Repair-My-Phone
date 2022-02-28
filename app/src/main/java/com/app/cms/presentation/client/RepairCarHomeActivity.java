package com.app.cms.presentation.client;

import android.content.Intent;
import android.os.Bundle;

import com.app.cms.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RepairCarHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_car_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.maintenanceListView)
    public void onMaintenanceListClicked() {
        startActivity(new Intent(this, MaintenanceShopListActivity.class));
    }

    @OnClick(R.id.searchMaintenanceBtn)
    public void onSearchMaintenanceClicked() {
        startActivity(new Intent(this, ServiceDetailsActivity.class));
    }

    @OnClick(R.id.repairRequestBtn)
    public void onMyRepairRequestClicked() {
        startActivity(new Intent(this, MyRepairRequestActivity.class));
    }
}