package com.app.repairmyphone.presentation.center;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.app.repairmyphone.R;
import com.app.repairmyphone.presentation.viewmodels.LoginViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getSuccess().observe(this, success -> {
            if (success) {
                startActivity(new Intent(this, MaintenanceCenterHomeActivity.class));
            } else {
                Toast.makeText(this, "Invalid credentials..", Toast.LENGTH_SHORT).show();
            }
        });

        loginViewModel.getError().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.btnLogin)
    public void onLoginClicked() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        loginViewModel.login(email, password);
    }

    @OnClick(R.id.btnRegister)
    public void onRegisterClicked() {
        startActivity(new Intent(this, MaintenanceCenterRegisterationActivity.class));
    }
}