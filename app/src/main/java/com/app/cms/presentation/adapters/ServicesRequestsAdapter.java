package com.app.cms.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.cms.R;
import com.app.cms.model.ServiceRequest;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ServicesRequestsAdapter extends RecyclerView.Adapter<ServicesRequestsAdapter.ServiceRequestViewHolder> {
    
    private List<ServiceRequest> serviceRequestList;
    private ServiceRequestItemListener listener;

    public ServicesRequestsAdapter(List<ServiceRequest> serviceRequestList, ServiceRequestItemListener listener) {
        this.serviceRequestList = serviceRequestList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ServiceRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item_layout, parent, false);
        return new ServiceRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceRequestViewHolder holder, int position) {
        ServiceRequest serviceRequest = serviceRequestList.get(position);
        holder.phoneTextView.setText(serviceRequest.getPhone());
        holder.serviceNameTextView.setText(serviceRequest.getService());
        holder.statusTextView.setText(serviceRequest.getStatus());
        holder.showDetailsButton.setOnClickListener(v -> listener.onServiceRequestItemClicked(serviceRequest));
    }

    @Override
    public int getItemCount() {
        return serviceRequestList.size();
    }

    public static class ServiceRequestViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.phoneTextView)
        TextView phoneTextView;
        @BindView(R.id.serviceNameTextView)
        TextView serviceNameTextView;
        @BindView(R.id.statusTextView)
        TextView statusTextView;
        @BindView(R.id.showDetailsButton)
        ImageButton showDetailsButton;
        
        public ServiceRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ServiceRequestItemListener {
        void onServiceRequestItemClicked(ServiceRequest serviceRequest);
    }
}
