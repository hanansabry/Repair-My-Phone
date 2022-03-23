package com.app.repairmyphone.presentation.client;

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

import com.app.repairmyphone.R;
import com.app.repairmyphone.model.MaintenanceCenter;
import com.app.repairmyphone.presentation.Constants;
import com.app.repairmyphone.presentation.ServiceRequestActivity;
import com.app.repairmyphone.presentation.adapters.ServiceAvailableCentersAdapter;
import com.app.repairmyphone.presentation.viewmodels.RetrieveCentersListViewModel;

import java.util.List;

public class ServiceDetailsActivity extends AppCompatActivity {

    @BindView(R.id.serviceNameTextView)
    TextView serviceNameTextView;
    @BindView(R.id.centerListRecyclerView)
    RecyclerView centerListRecyclerView;
    @BindView(R.id.showDetailsButton)
    ImageButton showDetailsButton;
    private String service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
        ButterKnife.bind(this);

        String categoryName = getIntent().getStringExtra(Constants.CATEGORY_NAME);
        service = getIntent().getStringExtra(Constants.SERVICE_NAME);

        serviceNameTextView.setText(service);
        showDetailsButton.setVisibility(View.INVISIBLE);

        RetrieveCentersListViewModel retrieveCentersListViewModel = ViewModelProviders.of(this).get(RetrieveCentersListViewModel.class);
        retrieveCentersListViewModel.retrieveCentersByCategoryServiceId(categoryName, service);
        retrieveCentersListViewModel.getCentersListLiveData().observe(this, this::initiateCenterListRecyclerView);
    }

    private void initiateCenterListRecyclerView(List<MaintenanceCenter> maintenanceCenters) {
        ServiceAvailableCentersAdapter centersAdapter = new ServiceAvailableCentersAdapter(maintenanceCenters, service, center -> {
            Intent intent = new Intent(this, ServiceRequestActivity.class);
            intent.putExtra(Constants.CENTER_ID, center.getId());
            intent.putExtra(Constants.SERVICE_NAME, service);
            intent.putExtra(Constants.STATUS, Constants.NEW);
            startActivity(intent);
        });
        centerListRecyclerView.setAdapter(centersAdapter);
        centerListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}