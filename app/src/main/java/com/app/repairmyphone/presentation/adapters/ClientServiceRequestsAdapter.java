package com.app.repairmyphone.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.repairmyphone.R;
import com.app.repairmyphone.model.ServiceRequest;
import com.app.repairmyphone.presentation.Constants;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientServiceRequestsAdapter extends RecyclerView.Adapter<ClientServiceRequestsAdapter.ClientServiceRequestViewHolder> {

    private List<ServiceRequest> serviceRequestList;
    private OnServiceRequestClicked serviceRequestClicked;

    public ClientServiceRequestsAdapter(List<ServiceRequest> serviceRequestList, OnServiceRequestClicked serviceRequestClicked) {
        this.serviceRequestList = serviceRequestList;
        this.serviceRequestClicked = serviceRequestClicked;
    }

    @NonNull
    @Override
    public ClientServiceRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repair_list_item_layout, parent, false);
        return new ClientServiceRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientServiceRequestViewHolder holder, int position) {
        ServiceRequest serviceRequest = serviceRequestList.get(position);
        holder.centerNameTextView.setText(serviceRequest.getCenterName());
        holder.statusTextView.setText(serviceRequest.getStatus());
        holder.showDetailsButton.setOnClickListener(v -> serviceRequestClicked.onServiceRequestClicked(serviceRequest));
        if (serviceRequest.getStatus().equals(Constants.DELIVERED)) {
            holder.showDetailsButton.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return serviceRequestList.size();
    }

    static class ClientServiceRequestViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.centerNameTextView)
        TextView centerNameTextView;
        @BindView(R.id.statusTextView)
        TextView statusTextView;
        @BindView(R.id.showDetailsButton)
        ImageButton showDetailsButton;

        public ClientServiceRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnServiceRequestClicked {
        void onServiceRequestClicked(ServiceRequest serviceRequest);
    }
}
