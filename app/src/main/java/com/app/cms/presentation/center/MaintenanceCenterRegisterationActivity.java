package com.app.cms.presentation.center;

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
import com.app.cms.presentation.viewmodels.AddCategoryViewModel;
import com.app.cms.presentation.viewmodels.RegisterViewModel;
import com.app.cms.presentation.viewmodels.RetrieveCategoriesViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaintenanceCenterRegisterationActivity extends AppCompatActivity {

    private RegisterViewModel registerViewModel;
    private RetrieveCategoriesViewModel retrieveCategoriesViewModel;

    @BindView(R.id.textInputName)
    TextInputLayout textInputName;
    @BindView(R.id.editTextName)
    TextInputEditText editTextName;

    @BindView(R.id.textInputPhone)
    TextInputLayout textInputPhone;
    @BindView(R.id.editTextPhone)
    TextInputEditText editTextPhone;

    @BindView(R.id.textInputEmail)
    TextInputLayout textInputEmail;
    @BindView(R.id.editTextEmail)
    TextInputEditText editTextEmail;

    @BindView(R.id.textInputPassword)
    TextInputLayout textInputPassword;
    @BindView(R.id.editTextPassword)
    TextInputEditText editTextPassword;

    @BindView(R.id.textInputConfirm)
    TextInputLayout textInputConfirm;
    @BindView(R.id.editTextConfirm)
    TextInputEditText editTextConfirm;

    @BindView(R.id.textInputRegion)
    TextInputLayout textInputRegion;
    @BindView(R.id.editTextRegion)
    TextInputEditText editTextRegion;

    @BindView(R.id.categoriesSpinner)
    Spinner categoriesSpinner;
    @BindView(R.id.textInputCategoryName)
    TextInputLayout textInputCategoryName;
    @BindView(R.id.editTextCategoryName)
    TextInputEditText editTextCategoryName;

    @BindView(R.id.textInputLang)
    TextInputLayout textInputLang;
    @BindView(R.id.editTextLang)
    TextInputEditText editTextLang;

    @BindView(R.id.textInputLat)
    TextInputLayout textInputLat;
    @BindView(R.id.editTextLat)
    TextInputEditText editTextLat;

    @BindView(R.id.categoryNameView)
    View categoryNameView;

    private List<String> categories;
    private String selectedCategory;
    private AddCategoryViewModel addCategoryViewModel;
    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_center_registeration);
        ButterKnife.bind(this);

        pd = new ProgressDialog(this);
        pd.setTitle("Loading");

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        retrieveCategoriesViewModel = ViewModelProviders.of(this).get(RetrieveCategoriesViewModel.class);
        addCategoryViewModel = ViewModelProviders.of(this).get(AddCategoryViewModel.class);

        registerViewModel.getCenterLiveDate().observe(this, center -> {
            if (center != null) {
                registerViewModel.addNewCenter(center);
            } else {
                Toast.makeText(this, "Error while registration, Please try again later", Toast.LENGTH_LONG).show();
            }
            pd.hide();
        });

        registerViewModel.isCenterAddedSuccessfully().observe(this, success -> {
            if (success) {
                if (selectedCategory == null) {
                    addCategoryViewModel.addNewCategory(editTextCategoryName.getText().toString().trim());
                } else {
                    startActivity(new Intent(this, MaintenanceCenterHomeActivity.class));
                    finish();
                }
            } else {
                Toast.makeText(this, "Error while registration, Please try again later", Toast.LENGTH_LONG).show();
            }
            pd.hide();
        });

        registerViewModel.getErrorMessage().observe(this, error -> {
            Toast.makeText(this, "All items are required", Toast.LENGTH_LONG).show();
            pd.hide();
        });

        addCategoryViewModel.isCategoryAdded().observe(this, success -> {
            if (success) {
                startActivity(new Intent(this, MaintenanceCenterHomeActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Error while adding category", Toast.LENGTH_LONG).show();
            }
            pd.hide();
        });
        initialCategoriesSpinner();
    }

    private void initialCategoriesSpinner() {
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
        retrieveCategoriesViewModel.retrieveCategories();
        retrieveCategoriesViewModel.getCategoriesLiveData().observe(this, categories -> {
            ArrayList<String> categoriesNames = new ArrayList<>();
            if ( categories == null ||  categories.isEmpty()) {
                Toast.makeText(this, "Other Category", Toast.LENGTH_SHORT).show();
                categoriesNames.add("Other");
            } else {
                this.categories = categories;
                categoriesNames.addAll(categories);
                categoriesNames.add("Other");
            }
            categoriesAdapter.addAll(categoriesNames);
            categoriesSpinner.setAdapter(categoriesAdapter);
        });

        categoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (categories != null && position < categories.size()) {
                    selectedCategory = categories.get(position);
                    categoryNameView.setVisibility(View.GONE);
                } else {
                    selectedCategory = null;
                    categoryNameView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.btnRegister)
    public void onRegisterClicked() {
        pd.setTitle("Loading");
        pd.show();

        String name = Objects.requireNonNull(editTextName.getText()).toString().trim();
        String region = Objects.requireNonNull(editTextRegion.getText()).toString().trim();
        Double lang = editTextLang.getText().toString().isEmpty() ? null : Double.parseDouble(editTextLang.getText().toString());
        Double lat = editTextLang.getText().toString().isEmpty() ? null : Double.parseDouble(editTextLat.getText().toString());
        String category = selectedCategory == null ? Objects.requireNonNull(editTextCategoryName.getText()).toString().trim() : selectedCategory;
        String phone = Objects.requireNonNull(editTextPhone.getText()).toString().trim();
        String email = Objects.requireNonNull(editTextEmail.getText()).toString().trim();
        String password = Objects.requireNonNull(editTextPassword.getText()).toString().trim();
        String confirmPassword = Objects.requireNonNull(editTextConfirm.getText()).toString().trim();

        registerViewModel.register(name, region, lang, lat, category, phone, email, password, confirmPassword);
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}