package com.app.cms.presentation;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.cms.R;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddServiceDialog extends Dialog {

    private DialogListener dialogListener;
    private Context context;
    @BindView(R.id.serviceNameEditText)
    EditText serviceNameEditText;
    @BindView(R.id.servicePriceRateEditText)
    EditText servicePriceRateEditText;
    @BindView(R.id.dialog_title)
    TextView dialog_title;

    public AddServiceDialog(@NonNull Context context, DialogListener dialogListener) {
        super(context);
        this.context = context;
        this.dialogListener = dialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view = View.inflate(getContext(), R.layout.add_service_dialog, null);
        setContentView(view);
        ButterKnife.bind(this, view);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

    }

    @OnClick(R.id.btn_yes)
    public void onAddClicked() {
        String serviceName = serviceNameEditText.getText().toString();
        String priceRate = servicePriceRateEditText.getText().toString();
        if (serviceName.isEmpty() || priceRate.isEmpty()) {
            Toast.makeText(context, "You must add name & price rate", Toast.LENGTH_SHORT).show();
        } else {
            dialogListener.onAddClicked(serviceName, priceRate);
            dismiss();
        }
    }

    @OnClick(R.id.btn_no)
    public void onCancelClicked() {
        dismiss();
    }

    public interface DialogListener {
        void onAddClicked(String serviceName, String priceRate);
    }

}
