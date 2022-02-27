package com.app.cms.presentation.center;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;

import com.app.cms.R;
import com.app.cms.presentation.ServiceRequest;

public class RequestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked () {
        onBackPressed();
    }

    @OnClick(R.id.showDetailsButton)
    public void onShowDetailsClicked () {
        startActivity(new Intent(this, ServiceRequest.class));
    }
}