package com.example.helloworld;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import java.util.List;

public class MatchesFragment extends Fragment {
    private int colCount = 3;
    private ArrayList<MatchesModel> matches;
    private OnListFragmentInteractionListener listener;
    static final String ARG_DATA_SET = "data-set";
    private MatchesViewModel viewModel;
//    private List<String> nameList = new ArrayList<String>();

    public MatchesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        matches = new ArrayList<>();

        viewModel = new MatchesViewModel();
        viewModel.getMatches(
                (dbMatches) -> {
                    matches = dbMatches;
                });
//        if (getArguments() != null) {
//            matches = getArguments().getParcelableArrayList(ARG_DATA_SET);
//        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

//        nameList.add("Elmo");
//        nameList.add("Cookie Monster");

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), colCount));
        recyclerView.setAdapter(new MatchesCardRecyclerViewAdapter(matches, listener));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
//        MatchesCardRecyclerViewAdapter adapter = new MatchesCardRecyclerViewAdapter(matches);
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
}
