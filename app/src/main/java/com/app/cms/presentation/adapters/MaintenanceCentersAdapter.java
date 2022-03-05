package com.app.cms.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.cms.R;
import com.app.cms.model.MaintenanceCenter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MaintenanceCentersAdapter extends RecyclerView.Adapter<MaintenanceCentersAdapter.CenterViewHolder> {

    private List<MaintenanceCenter> centerList;
    private OnCenterClickedListener listener;

    public MaintenanceCentersAdapter(List<MaintenanceCenter> centerList, OnCenterClickedListener listener) {
        this.centerList = centerList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CenterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.centers_list_item_layout, parent, false);
        return new CenterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CenterViewHolder holder, int position) {
        MaintenanceCenter center = centerList.get(position);
        holder.centerNameTextView.setText(center.getName());
        holder.categoryTextView.setText(center.getCategory());
        holder.phoneTextView.setText(center.getPhone());
        holder.regionTextView.setText(center.getRegion());
        if (listener != null) {
            holder.itemView.setOnClickListener(v -> listener.onCenterClicked(center));
        }
    }

    @Override
    public int getItemCount() {
        return centerList.size();
    }

    static class CenterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.centerNameTextView)
        TextView centerNameTextView;
        @BindView(R.id.categoryTextView)
        TextView categoryTextView;
        @BindView(R.id.phoneTextView)
        TextView phoneTextView;
        @BindView(R.id.regionTextView)
        TextView regionTextView;

        public CenterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this ,itemView);
        }
    }

    public interface OnCenterClickedListener {
        void onCenterClicked(MaintenanceCenter center);
    }
}
