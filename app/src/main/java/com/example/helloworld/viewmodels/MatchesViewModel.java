package com.example.helloworld.viewmodels;

import android.util.Log;

import com.example.helloworld.datamodels.MatchesDataModel;
import com.example.helloworld.models.MatchesModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.function.Consumer;

public class MatchesViewModel {

    private MatchesDataModel matchesDataModel;

    public MatchesViewModel() {
        matchesDataModel = new MatchesDataModel();
    }

    public void addMatches(MatchesModel match) {
        matchesDataModel.addMatches(match);
    }

    public void getMatches(Consumer<ArrayList<MatchesModel>> responseCallBack) {
        matchesDataModel.getMatches(
                (QuerySnapshot querySnapshot) -> {
                    if(querySnapshot != null) {
                        ArrayList<MatchesModel> matchesModels =  new ArrayList<>();
                        for (DocumentSnapshot matchesSnapshot : querySnapshot.getDocuments()) {
                            MatchesModel match = matchesSnapshot.toObject(MatchesModel.class);
                            assert match != null;
                            match.uid = matchesSnapshot.getId();
                            matchesModels.add(match);
                        }
                        responseCallBack.accept(matchesModels);
                    }
                },
                (databaseError -> System.out.println("Error reading matches: " + databaseError))
        );
    }

    public void updateMatches(MatchesModel match) {
        matchesDataModel.updateMatchesById(match);
    }

    public void clear() {
        matchesDataModel.clear();
    }
}
