package com.app.cms.presentation.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;

import com.app.cms.R;
import com.app.cms.model.MaintenanceCenter;
import com.app.cms.presentation.adapters.MaintenanceCentersAdapter;
import com.app.cms.presentation.viewmodels.RetrieveCentersListViewModel;

import java.util.List;

public class MaintenanceCentersListActivity extends AppCompatActivity {

    @BindView(R.id.centerListRecyclerView)
    RecyclerView centerListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_center_list);
        ButterKnife.bind(this);

        RetrieveCentersListViewModel retrieveCentersListViewModel = ViewModelProviders.of(this).get(RetrieveCentersListViewModel.class);
        retrieveCentersListViewModel.retrieveAllCenters();
        retrieveCentersListViewModel.getCentersListLiveData().observe(this, this::initiateCentersRecyclerView);
    }

    private void initiateCentersRecyclerView(List<MaintenanceCenter> maintenanceCenters) {
        MaintenanceCentersAdapter centersAdapter = new MaintenanceCentersAdapter(maintenanceCenters, null);
        centerListRecyclerView.setAdapter(centersAdapter);
        centerListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}