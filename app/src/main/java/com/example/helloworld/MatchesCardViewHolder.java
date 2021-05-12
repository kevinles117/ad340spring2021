package com.example.helloworld;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MatchesCardViewHolder extends RecyclerView.ViewHolder{

    public ImageView matchImage;
    public TextView matchName;

    public MatchesCardViewHolder(@NonNull View itemView) {
        super(itemView);
        matchImage = itemView.findViewById(R.id.match_image);
        matchName = itemView.findViewById(R.id.match_name);
    }
}
