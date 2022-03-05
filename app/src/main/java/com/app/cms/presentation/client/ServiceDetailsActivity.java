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
import com.app.cms.model.MaintenanceCenter;
import com.app.cms.model.Service;
import com.app.cms.presentation.Constants;
import com.app.cms.presentation.ServiceRequestActivity;
import com.app.cms.presentation.adapters.MaintenanceCentersAdapter;
import com.app.cms.presentation.adapters.ServiceAvailableCentersAdapter;
import com.app.cms.presentation.viewmodels.RetrieveCentersListViewModel;

import java.util.List;

public class ServiceDetailsActivity extends AppCompatActivity {

    @BindView(R.id.serviceNameTextView)
    TextView serviceNameTextView;
    @BindView(R.id.centerListRecyclerView)
    RecyclerView centerListRecyclerView;
    @BindView(R.id.showDetailsButton)
    ImageButton showDetailsButton;
    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
        ButterKnife.bind(this);

        String categoryId = getIntent().getStringExtra(Constants.CATEGORY_ID);
        service = (Service) getIntent().getExtras().get(Constants.SERVICE);

        serviceNameTextView.setText(service.getName());
        showDetailsButton.setVisibility(View.INVISIBLE);

        RetrieveCentersListViewModel retrieveCentersListViewModel = ViewModelProviders.of(this).get(RetrieveCentersListViewModel.class);
        retrieveCentersListViewModel.retrieveCentersByCategoryServiceId(categoryId, service.getId());
        retrieveCentersListViewModel.getCentersListLiveData().observe(this, this::initiateCenterListRecyclerView);
    }

    private void initiateCenterListRecyclerView(List<MaintenanceCenter> maintenanceCenters) {
        ServiceAvailableCentersAdapter centersAdapter = new ServiceAvailableCentersAdapter(maintenanceCenters, service, center -> {
            Intent intent = new Intent(this, ServiceRequestActivity.class);
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