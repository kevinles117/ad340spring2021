package com.example.helloworld.datamodels;

import com.example.helloworld.models.MatchesModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MatchesDataModel {
    private FirebaseFirestore db;
    private List<ListenerRegistration> listeners;

    public MatchesDataModel() {
        db = FirebaseFirestore.getInstance();
        listeners = new ArrayList<>();
    }

    public void addMatches(MatchesModel match) {
        CollectionReference matchesRef = db.collection("matches");
        matchesRef.add(match);
    }

    public void getMatches(Consumer<QuerySnapshot> dataChangedCallback, Consumer<FirebaseFirestoreException> dataErrorCallback) {
        ListenerRegistration listener = db.collection("matches")
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if (e != null) {
                        dataErrorCallback.accept(e);
                    }
                    dataChangedCallback.accept(queryDocumentSnapshots);
                });
        listeners.add(listener);
    }

    public void updateMatches(MatchesModel match) {
        DocumentReference matchesRef = db.collection("matches").document(match.uid);
        Map<String, Object> data = new HashMap<>();
        data.put("liked", match.liked);
        matchesRef.update(data);
    }

    public void clear() {
        listeners.forEach(ListenerRegistration::remove);
    }
}
