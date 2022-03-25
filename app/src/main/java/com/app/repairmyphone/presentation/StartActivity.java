package com.app.repairmyphone.presentation;

import android.content.Intent;
import android.os.Bundle;

import com.app.repairmyphone.R;
import com.app.repairmyphone.presentation.center.LoginActivity;
import com.app.repairmyphone.presentation.center.MaintenanceCenterHomeActivity;
import com.app.repairmyphone.presentation.center.MaintenanceCenterRegisterationActivity;
import com.app.repairmyphone.presentation.client.RepairPhoneHomeActivity;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnStart)
    public void onRepairMyPhoneClicked() {
        startActivity(new Intent(this, RepairPhoneHomeActivity.class));
    }

    @OnClick(R.id.btnCenter)
    public void onLoginAsCenterClicked() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(this, MaintenanceCenterHomeActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @OnClick(R.id.register_now_text)
    public void onRegisterClicked() {
        startActivity(new Intent(this, MaintenanceCenterRegisterationActivity.class));
    }
}