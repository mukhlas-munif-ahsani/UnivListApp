package com.example.univlist.SoshumFragment;

import androidx.annotation.NonNull;

import com.example.univlist.SaintekFragment.SaintekListModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class SoshumRepo {

    private SoshumRepo.OnFirestoreTaskComplete onFirestoreTaskComplete;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference soshumRef = firebaseFirestore.collection("SOSHUM");

    public SoshumRepo(SoshumRepo.OnFirestoreTaskComplete onFirestoreTaskComplete) {
        this.onFirestoreTaskComplete = onFirestoreTaskComplete;
    }

    public void getSoshumData() {
        soshumRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    onFirestoreTaskComplete.soshumListDataAdded(task.getResult().toObjects(SoshumListModel.class));
                } else {
                    onFirestoreTaskComplete.onError(task.getException());
                }
            }
        });
    }

    public interface OnFirestoreTaskComplete {
        void soshumListDataAdded(List<SoshumListModel> saintekListModelList);

        void onError(Exception e);
    }
}
