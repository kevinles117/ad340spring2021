package com.example.helloworld;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MatchesCardRecyclerViewAdapter extends RecyclerView.Adapter<MatchesCardViewHolder>{

    private List<String> nameList;
    private Button likeButton;

    MatchesCardRecyclerViewAdapter(List<String> nameList) {
        this.nameList = nameList;
    }

    @NonNull
    @Override
    public MatchesCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card, parent, false);
        likeButton = layoutView.findViewById(R.id.like_button);

        return new MatchesCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesCardViewHolder holder, int position) {
        if (nameList != null && position < nameList.size()) {
            String name = nameList.get(position);
            holder.matchName.setText(name);

            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(likeButton.getContext(), "You liked " + name, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }
}
