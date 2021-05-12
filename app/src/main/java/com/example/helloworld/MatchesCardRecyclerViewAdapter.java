package com.example.helloworld;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class MatchesCardRecyclerViewAdapter extends RecyclerView.Adapter<MatchesCardViewHolder>{

    private List<String> nameList;

    MatchesCardRecyclerViewAdapter(List<String> nameList) {
        this.nameList = nameList;
    }

    @NonNull
    @Override
    public MatchesCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new MatchesCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesCardViewHolder holder, int position) {
        if (nameList != null && position < nameList.size()) {
            String name = nameList.get(position);
            holder.matchName.setText(name);
        }
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }
}
