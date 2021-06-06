package com.example.helloworld;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.models.MatchesModel;
import com.example.helloworld.viewmodels.MatchesViewModel;

import java.util.ArrayList;

public class MatchesFragment extends Fragment {
    private int colCount = 3;
    private ArrayList<MatchesModel> matches = new ArrayList<>();
    private OnListFragmentInteractionListener listener;
    private MatchesViewModel viewModel;
    private LocationManager locationManager;

    public MatchesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_matches, container, false);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), colCount));
        MatchesCardRecyclerViewAdapter adapter = new MatchesCardRecyclerViewAdapter(matches, listener);
        recyclerView.setAdapter(adapter);
        viewModel = new MatchesViewModel();
        viewModel.getMatches(
                (ArrayList<MatchesModel> dbMatches) -> {
                    matches = dbMatches;
                    MatchesCardRecyclerViewAdapter mAdapter = new MatchesCardRecyclerViewAdapter(matches, listener);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            listener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    public MatchesViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(MatchesViewModel vm) {
        viewModel = vm;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.navigation_drawer, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(MatchesModel matches);
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}
