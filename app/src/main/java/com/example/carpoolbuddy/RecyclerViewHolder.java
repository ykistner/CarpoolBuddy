package com.example.carpoolbuddy;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected TextView modelText;
    protected TextView typeText;
    protected TextView capacityText;
    protected TextView basePriceText;

    private RecyclerAdapter.OnViewClickListner onViewClickListner;

    public RecyclerViewHolder(@NonNull View itemView, RecyclerAdapter.OnViewClickListner onViewClickListner) {
        super(itemView);
        this.onViewClickListner = onViewClickListner;

        modelText = itemView.findViewById(R.id.modelTextView);
        typeText = itemView.findViewById(R.id.typeTextView);
        capacityText = itemView.findViewById(R.id.capacityTextView);
        basePriceText = itemView.findViewById(R.id.basePriceTextView);

        itemView.setOnClickListener(this);
    }

    public void modelText(String model) {
        modelText.setText(model);
    }

    public void typeText(String vehicleType) {
        typeText.setText(vehicleType);
    }

    public void capacityText(String s) {
        capacityText.setText(s);
    }

    public void basePriceText(String s) {
        basePriceText.setText(s);
    }

    @Override
    public void onClick(View v) {
        onViewClickListner.onViewClick(getAdapterPosition());
    }
}
