package com.app.cms.presentation.center;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.app.cms.R;
import com.app.cms.model.MaintenanceCenter;
import com.app.cms.presentation.Constants;
import com.app.cms.presentation.viewmodels.RetrieveCenterViewModel;


public class MaintenanceCenterHomeActivity extends AppCompatActivity {

    @BindView(R.id.centerNameTextView)
    TextView centerNameTextView;
    private MaintenanceCenter center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_center_home);
        ButterKnife.bind(this);

        //get current maintenance center (true is temporary)
        if (true) {
            String uId = "hanan";
            RetrieveCenterViewModel retrieveCenterViewModel = ViewModelProviders.of(this).get(RetrieveCenterViewModel.class);
            retrieveCenterViewModel.getCenterById(uId);
            retrieveCenterViewModel.getMaintenanceCenterLiveData().observe(this, center -> {
                if (center != null) {
                    this.center = center;
                    String centerName = center.getName();
                    centerNameTextView.setText(centerName);
                } else {
                    Toast.makeText(this, "Error, Please login again", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(this, LoginActivity.class));
                }
            });
        } else {
            //if user not logged in
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.servicesView)
    public void onServicesClicked() {
        Intent intent = new Intent(this, ServicesActivity.class);
        intent.putExtra(Constants.CATEGORY_NAME, center.getCategory());
        startActivity(intent);
    }

    @OnClick(R.id.requestsView)
    public void onRequestsView() {
        Intent intent = new Intent(this, RequestsActivity.class);
        intent.putExtra(Constants.CENTER_ID, center.getId());
        startActivity(new Intent(this, RequestsActivity.class));
    }
}