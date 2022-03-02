package com.app.cms.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.cms.R;
import com.app.cms.model.Service;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder> {

    private List<Service> serviceList;

    public ServicesAdapter(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item_layout, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service service = serviceList.get(position);
        holder.serviceNameTextView.setText(service.getName());
        holder.servicePriceRateTextView.setText(String.format("%s", service.getPriceRate()));
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.serviceNameTextView)
        TextView serviceNameTextView;
        @BindView(R.id.servicePriceRateTextView)
        TextView servicePriceRateTextView;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
