package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ProfileFragment extends Fragment {
    TextView nameTextView;
    TextView occupationTextView;
    TextView descriptionTextView;
    private SignUpActivity.Profile profile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        nameTextView = view.findViewById(R.id.name_text);
        occupationTextView = view.findViewById(R.id.occupation_text);
        descriptionTextView = view.findViewById(R.id.description_text);

        nameTextView.setText(profile.name + ", " + profile.age);
        occupationTextView.setText(profile.occupation);
        descriptionTextView.setText(profile.desc);

        setUpToolbar(view);

        return view;
    }

    void setProfile(SignUpActivity.Profile profile) {
        this.profile = profile;
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.topAppBar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.navigation_drawer, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }
}
