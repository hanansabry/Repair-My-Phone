package com.app.cms.presentation.center;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.widget.Toast;

import com.app.cms.R;
import com.app.cms.model.Service;
import com.app.cms.presentation.AddServiceDialog;
import com.app.cms.presentation.Constants;
import com.app.cms.presentation.adapters.ServicesAdapter;
import com.app.cms.presentation.viewmodels.AddServiceViewModel;
import com.app.cms.presentation.viewmodels.RetrieveServicesViewModel;

import java.util.List;

public class ServicesActivity extends AppCompatActivity {

    @BindView(R.id.servicesRecyclerView)
    RecyclerView servicesRecyclerView;

    private AddServiceViewModel addServiceViewModel;
    private String categoryName;
    private String centerId;
    private ServicesAdapter servicesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        ButterKnife.bind(this);

        categoryName = getIntent().getStringExtra(Constants.CATEGORY_NAME);
        centerId = getIntent().getStringExtra(Constants.CENTER_ID);

        RetrieveServicesViewModel retrieveServicesViewModel = ViewModelProviders.of(this).get(RetrieveServicesViewModel.class);
        //get center id
        retrieveServicesViewModel.retrieveServicesByCenterId(centerId);
        retrieveServicesViewModel.getServiceListLiveData().observe(this, this::initiateServicesRecyclerView);

        addServiceViewModel = ViewModelProviders.of(this).get(AddServiceViewModel.class);
        addServiceViewModel.getSuccess().observe(this, success -> {
            if (success) {
                servicesAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Service is added successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error, please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initiateServicesRecyclerView(List<Service> services) {
        servicesAdapter = new ServicesAdapter(services);
        servicesRecyclerView.setAdapter(servicesAdapter);
        servicesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.addServiceFab)
    public void onAddServiceFabClicked () {
        AddServiceDialog addServiceDialog = new AddServiceDialog(this, (serviceName, priceRate) -> {
            Service service = new Service();
            service.setName(serviceName);
            service.setPriceRate(Double.parseDouble(priceRate));
            service.setCategory(categoryName);
            addServiceViewModel.addNewService(centerId, service);
        });
        addServiceDialog.show();
    }
}