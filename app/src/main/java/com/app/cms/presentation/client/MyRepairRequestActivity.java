package com.app.cms.presentation.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.cms.R;
import com.app.cms.model.ServiceRequest;
import com.app.cms.presentation.Constants;
import com.app.cms.presentation.ServiceRequestActivity;
import com.app.cms.presentation.adapters.ClientServiceRequestsAdapter;
import com.app.cms.presentation.viewmodels.RetrieveRequestsViewModel;

import java.util.List;

public class MyRepairRequestActivity extends AppCompatActivity {

    @BindView(R.id.showDetailsButton)
    ImageButton showDetailsButton;
    @BindView(R.id.phoneNumberTextView)
    TextView phoneNumberTextView;
    @BindView(R.id.clientRepairListRecyclerView)
    RecyclerView clientRepairListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_repair_request);
        ButterKnife.bind(this);
        showDetailsButton.setVisibility(View.INVISIBLE);

        String phoneNumber = (String) getIntent().getExtras().get(Constants.PHONE_NUMBER);
        phoneNumberTextView.setText(phoneNumber);

        RetrieveRequestsViewModel retrieveRequestsViewModel = ViewModelProviders.of(this).get(RetrieveRequestsViewModel.class);
        retrieveRequestsViewModel.getServiceRequestsByClientPhone(phoneNumber);
        retrieveRequestsViewModel.getServiceRequestsLiveData().observe(this, this::initiateClientRepairRequestsRecyclerView);
    }

    private void initiateClientRepairRequestsRecyclerView(List<ServiceRequest> serviceRequests) {
        ClientServiceRequestsAdapter adapter = new ClientServiceRequestsAdapter(serviceRequests, serviceRequest -> {
            Intent intent = new Intent(MyRepairRequestActivity.this, ServiceRequestActivity.class);
            intent.putExtra(Constants.SERVICE_REQUEST, serviceRequest);
            intent.putExtra(Constants.STATUS, Constants.ACCEPT_REJECT);
            startActivity(intent);
        });
        clientRepairListRecyclerView.setAdapter(adapter);
        clientRepairListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}