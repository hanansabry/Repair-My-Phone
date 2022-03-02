package com.app.cms.presentation.center;

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

import com.app.cms.R;
import com.app.cms.model.ServiceRequest;
import com.app.cms.presentation.Constants;
import com.app.cms.presentation.ServiceRequestActivity;
import com.app.cms.presentation.adapters.ServicesRequestsAdapter;
import com.app.cms.presentation.viewmodels.RetrieveRequestsViewModel;

import java.util.List;

public class RequestsActivity extends AppCompatActivity {

    private String centerId;
    @BindView(R.id.requestsRecyclerView)
    RecyclerView requestsRecyclerView;
    @BindView(R.id.showDetailsButton)
    ImageButton showDetailsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        ButterKnife.bind(this);

        centerId = getIntent().getStringExtra(Constants.CENTER_ID);
        RetrieveRequestsViewModel retrieveRequestsViewModel = ViewModelProviders.of(this).get(RetrieveRequestsViewModel.class);
        retrieveRequestsViewModel.getServiceRequestsByCenterId(centerId);
        retrieveRequestsViewModel.getServiceRequestsLiveData().observe(this, this::initiateRequestsRecyclerView);

        showDetailsButton.setVisibility(View.GONE);
    }

    private void initiateRequestsRecyclerView(List<ServiceRequest> serviceRequests) {
        ServicesRequestsAdapter requestsAdapter = new ServicesRequestsAdapter(serviceRequests, serviceRequest -> {
            Intent intent = new Intent(this, ServiceRequestActivity.class);
            intent.putExtra(Constants.SERVICE_REQUEST, serviceRequest);
            startActivity(intent);
        });
        requestsRecyclerView.setAdapter(requestsAdapter);
        requestsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked () {
        onBackPressed();
    }

    @OnClick(R.id.showDetailsButton)
    public void onShowDetailsClicked () {
        startActivity(new Intent(this, ServiceRequestActivity.class));
    }
}