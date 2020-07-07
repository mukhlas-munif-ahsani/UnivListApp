package com.example.univlist.ListUnivFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.univlist.DetailProdi.View.DetailProdiActivity;
import com.example.univlist.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ListUnivActivity extends AppCompatActivity implements ListUnivAdapter.OnItemClickListener {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference univListRef = firebaseFirestore.collection("UNIVERSITAS");
    private ListUnivAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    Query query;

    public ProgressBar progressBar;
    private RecyclerView recyclerView;
    private String prodi, kategori;
    private Toolbar toolbar;

    private Animation fadeInAnim;
    private Animation fadeOutAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_univ);

        toolbar = findViewById(R.id.Toolbar);
        recyclerView = findViewById(R.id.list_view);
        progressBar = findViewById(R.id.list_progress);
        fadeInAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeOutAnim = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        toolbar.setNavigationIcon(R.drawable.ic_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        prodi = intent.getStringExtra("prodi");
        kategori = intent.getStringExtra("kategori");
        Toast.makeText(this, prodi, Toast.LENGTH_LONG).show();
        query = univListRef.whereArrayContains("prodi", prodi);

//        univListRef.document(prodi).update("univ", FieldValue.arrayUnion("blabla"));

        toolbar.setTitle(prodi);

        //Useless line

            recyclerView.startAnimation(fadeInAnim);
            progressBar.startAnimation(fadeOutAnim);
            progressBar.setVisibility(View.INVISIBLE);

        //-----

        FirestoreRecyclerOptions<ListUnivModel> options = new FirestoreRecyclerOptions.Builder<ListUnivModel>()
                .setQuery(query, ListUnivModel.class)
                .build();

        adapter = new ListUnivAdapter(options);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(String univ, int position) {
       Toast.makeText(this, univ, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, DetailProdiActivity.class);
        intent.putExtra("univ", univ);
        intent.putExtra("prodi", prodi);
        intent.putExtra("kategori", kategori);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
