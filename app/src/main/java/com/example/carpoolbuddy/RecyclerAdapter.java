package com.example.carpoolbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.content.Context;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private ArrayList<Vehicle> vehicleList;
    private OnViewClickListner onViewClickListner;

    public RecyclerAdapter(ArrayList<Vehicle> vehicleList, VehiclesInfoActivity vehiclesInfoActivity) {
        this.vehicleList = vehicleList;
        this.onViewClickListner = onViewClickListner;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_view, parent, false);

        RecyclerViewHolder holder = new RecyclerViewHolder(myView, VehiclesInfoActivity);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.modelText(vehicleList.get(position).getModel());
        holder.typeText(vehicleList.get(position).getVehicleType());
        holder.capacityText("Capacity: "+vehicleList.get(position).getCapacity());
        holder.basePriceText("€"+vehicleList.get(position).getBasePrice());
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public interface OnViewClickListner {
        public void onViewClick(int position);
    }
}
