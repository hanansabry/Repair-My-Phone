package com.app.cms.presentation.client;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.cms.R;
import com.app.cms.model.Category;
import com.app.cms.model.Service;
import com.app.cms.presentation.Constants;
import com.app.cms.presentation.viewmodels.RetrieveCategoriesViewModel;
import com.app.cms.presentation.viewmodels.RetrieveServicesViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RepairCarHomeActivity extends AppCompatActivity {

    private RetrieveCategoriesViewModel retrieveCategoriesViewModel;
    private RetrieveServicesViewModel retrieveServicesViewModel;

    @BindView(R.id.categoriesSpinner)
    Spinner categoriesSpinner;
    @BindView(R.id.servicesSpinner)
    Spinner servicesSpinner;
    @BindView(R.id.editTextPhone)
    TextInputEditText editTextPhone;
    private String selectedCategory;
    private Service selectedService;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_car_home);
        ButterKnife.bind(this);

        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();

        retrieveCategoriesViewModel = ViewModelProviders.of(this).get(RetrieveCategoriesViewModel.class);
        retrieveServicesViewModel = ViewModelProviders.of(this).get(RetrieveServicesViewModel.class);

        retrieveCategoriesViewModel.retrieveCategories();
        retrieveCategoriesViewModel.getCategoriesLiveData().observe(this, this::initiateCategoriesSpinner);
        retrieveServicesViewModel.getServiceListLiveData().observe(this, this::initiateServicesSpinner);
    }

    private void initiateCategoriesSpinner(List<String> categories) {
        if (categories == null || categories.isEmpty()) {
            pd.hide();
        } else {
            ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
            categoriesAdapter.addAll(categories);
            categoriesSpinner.setAdapter(categoriesAdapter);
            categoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedCategory = categories.get(position);
                    //get services by selected category
                    retrieveServicesViewModel.retrieveServicesByCategoryId(selectedCategory);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    private void initiateServicesSpinner(List<Service> services) {
        ArrayAdapter<String> servicesAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
        ArrayList<String> servicesNames = new ArrayList<>();
        for (Service service : services) {
            servicesNames.add(service.getName());
        }
        servicesAdapter.addAll(servicesNames);
        servicesSpinner.setAdapter(servicesAdapter);
        servicesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 selectedService = services.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        pd.hide();
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.maintenanceListView)
    public void onMaintenanceListClicked() {
        startActivity(new Intent(this, MaintenanceCentersListActivity.class));
    }

    @OnClick(R.id.searchMaintenanceBtn)
    public void onSearchMaintenanceClicked() {
        if (selectedCategory == null || selectedService == null) {
            Toast.makeText(this, "You must select category and service", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, ServiceDetailsActivity.class);
            intent.putExtra(Constants.CATEGORY_NAME, selectedCategory);
            intent.putExtra(Constants.SERVICE, selectedService);
//            intent.putExtra(Constants.SERVICE_ID, selectedService.getId());
//            intent.putExtra(Constants.SERVICE_NAME, selectedService.getName());
            startActivity(intent);
        }
    }

    @OnClick(R.id.repairRequestBtn)
    public void onMyRepairRequestClicked() {
        String phoneNumber = editTextPhone.getText().toString();
        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "You must enter phone number", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, MyRepairRequestActivity.class);
            intent.putExtra(Constants.PHONE_NUMBER, phoneNumber);
            startActivity(intent);
        }
    }
}