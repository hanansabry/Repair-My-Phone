package com.app.cms.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.cms.R;
import com.app.cms.model.MaintenanceCenter;
import com.app.cms.model.Service;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceAvailableCentersAdapter extends RecyclerView.Adapter<ServiceAvailableCentersAdapter.CenterViewHolder> {

    private List<MaintenanceCenter> centerList;
    private OnServiceCenterClickedListener listener;
    private String serviceName;

    public ServiceAvailableCentersAdapter(List<MaintenanceCenter> centerList, String serviceName, OnServiceCenterClickedListener listener) {
        this.centerList = centerList;
        this.listener = listener;
        this.serviceName = serviceName;
    }

    @NonNull
    @Override
    public CenterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_list_item_layout, parent, false);
        return new CenterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CenterViewHolder holder, int position) {
        MaintenanceCenter center = centerList.get(position);
        holder.centerNameTextView.setText(center.getName());
        holder.regionTextView.setText(center.getRegion());
        holder.phoneTextView.setText(center.getPhone());
        Service service = getServiceFromCenter(center);
        holder.priceRateTextView.setText(String.format("%s", service.getPriceRate()));
        holder.showDetailsButton.setOnClickListener(v -> listener.onCenterClicked(center));
    }

    private Service getServiceFromCenter(MaintenanceCenter center) {
        for (String key : center.getServices().keySet()) {
            Service s = center.getServices().get(key);
            if (serviceName.equals(s.getName())) {
                return s;
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return centerList.size();
    }

    static class CenterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.centerNameTextView)
        TextView centerNameTextView;
        @BindView(R.id.regionTextView)
        TextView regionTextView;
        @BindView(R.id.phoneTextView)
        TextView phoneTextView;
        @BindView(R.id.priceRateTextView)
        TextView priceRateTextView;
        @BindView(R.id.showDetailsButton)
        ImageButton showDetailsButton;

        public CenterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this ,itemView);
        }
    }

    public interface OnServiceCenterClickedListener {
        void onCenterClicked(MaintenanceCenter center);
    }
}
