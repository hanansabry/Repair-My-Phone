package com.app.repairmyphone.presentation.center;

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

import com.app.repairmyphone.R;
import com.app.repairmyphone.model.ServiceRequest;
import com.app.repairmyphone.presentation.Constants;
import com.app.repairmyphone.presentation.ServiceRequestActivity;
import com.app.repairmyphone.presentation.adapters.ServicesRequestsAdapter;
import com.app.repairmyphone.presentation.viewmodels.RetrieveRequestsViewModel;

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
            intent.putExtra(Constants.CENTER_ID, centerId);
            intent.putExtra(Constants.SERVICE_REQUEST, serviceRequest);
            if (serviceRequest.getStatus().equals(Constants.NEW) || serviceRequest.getStatus().equals(Constants.PENDING)) {
                intent.putExtra(Constants.STATUS, Constants.PENDING);
            } else {
                intent.putExtra(Constants.STATUS, Constants.ACCEPT_REJECT);
                intent.putExtra(Constants.IS_CENTER, true);
            }
            startActivity(intent);
        });
        requestsRecyclerView.setAdapter(requestsAdapter);
        requestsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestsAdapter.notifyDataSetChanged();
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