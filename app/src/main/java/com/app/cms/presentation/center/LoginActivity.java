package com.app.cms.presentation.center;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.app.cms.R;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.btnLogin)
    public void onLoginClicked() {
        startActivity(new Intent(this, MaintenanceCenterHomeActivity.class));
    }

    @OnClick(R.id.btnRegister)
    public void onRegisterClicked() {
        startActivity(new Intent(this, MaintenanceCenterRegisterationActivity.class));
    }
}