package com.example.helloworld;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.MatchesFragment.OnListFragmentInteractionListener;
import com.example.helloworld.models.MatchesModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MatchesCardRecyclerViewAdapter extends RecyclerView.Adapter<MatchesCardViewHolder>{

    private ArrayList<MatchesModel> matches;
    private OnListFragmentInteractionListener listener;
    private Button likeButton;

    MatchesCardRecyclerViewAdapter(ArrayList<MatchesModel> matches, OnListFragmentInteractionListener listener) {
        this.matches = matches;
        this.listener = listener;
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
        holder.matches = matches.get(position);
        holder.matchName.setText(matches.get(position).name);
        Picasso.with(likeButton.getContext())
            .load(matches.get(position).imageUrl)
                .resize(R.dimen.matchImageDim, R.dimen.matchImageDim).into(holder.matchImage);
//        if (matches != null && position < matches.size()) {
//            String name = matches.get(position);
//            holder.matchName.setText(name);

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(likeButton.getContext(), "You liked " + holder.matchName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }
}
