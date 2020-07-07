package com.example.univlist.SaintekFragment;

import androidx.annotation.NonNull;

import com.example.univlist.SaintekFragment.SaintekListModel;
import com.example.univlist.SoshumFragment.SoshumListModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class SaintekRepository {

    private OnFirestoreTaskComplete onFirestoreTaskComplete;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference saintekRef = firebaseFirestore.collection("SAINTEK");


    public SaintekRepository(OnFirestoreTaskComplete onFirestoreTaskComplete) {
        this.onFirestoreTaskComplete = onFirestoreTaskComplete;
    }

    public void getSaintekData() {
        saintekRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    onFirestoreTaskComplete.sanitekListDataAdded(task.getResult().toObjects(SaintekListModel.class));
                } else {
                    onFirestoreTaskComplete.onError(task.getException());
                }
            }
        });
    }

    public interface OnFirestoreTaskComplete {
        void sanitekListDataAdded(List<SaintekListModel> saintekListModelList);

        void onError(Exception e);
    }

}
