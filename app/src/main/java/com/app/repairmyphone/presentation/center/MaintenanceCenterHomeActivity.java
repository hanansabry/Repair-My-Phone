package com.app.repairmyphone.presentation.center;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.app.repairmyphone.R;
import com.app.repairmyphone.model.MaintenanceCenter;
import com.app.repairmyphone.presentation.Constants;
import com.app.repairmyphone.presentation.StartActivity;
import com.app.repairmyphone.presentation.viewmodels.RetrieveCenterViewModel;
import com.google.firebase.auth.FirebaseAuth;


public class MaintenanceCenterHomeActivity extends AppCompatActivity {

    @BindView(R.id.centerNameTextView)
    TextView centerNameTextView;
    private MaintenanceCenter center;
    private String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_center_home);
        ButterKnife.bind(this);

        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();

        //get current maintenance center (true is temporary)
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
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
                pd.hide();
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
        intent.putExtra(Constants.CENTER_ID, uId);
        startActivity(intent);
    }

    @OnClick(R.id.requestsView)
    public void onRequestsView() {
        Intent intent = new Intent(this, RequestsActivity.class);
        intent.putExtra(Constants.CENTER_ID, center.getId());
        startActivity(intent);
    }

    @OnClick(R.id.btnLogout)
    public void onLogoutClicked() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}