package com.example.carpoolbuddy;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    protected TextView modelText;
    protected TextView typeText;
    protected TextView capacityText;
    protected TextView basePriceText;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        modelText = itemView.findViewById(R.id.modelTextView);
        typeText = itemView.findViewById(R.id.typeTextView);
        capacityText = itemView.findViewById(R.id.capacityTextView);
        basePriceText = itemView.findViewById(R.id.basePriceTextView);
    }

    public void modelText(String model) {
    }

    public void typeText(String vehicleType) {
    }

    public void capacityText(String s) {
    }

    public void basePriceText(String s) {
    }
}
