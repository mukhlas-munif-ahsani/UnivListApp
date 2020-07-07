package com.example.univlist.TambahData2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.univlist.DetailProdi.Adapters.ProgramPilihanAdapter;
import com.example.univlist.R;
import com.example.univlist.TambahData3.TambahDataActivity3;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TambahDataActivity2 extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = firebaseFirestore.collection("UNIVERSITAS");

    @BindView(R.id.tambahData2Progress)
    ProgressBar progressBar;
    @BindView(R.id.rv_program_pilihan_tambah_data)
    RecyclerView recyclerView;
    @BindView(R.id.add_tambah_data_2)
    TextInputEditText tambahDataTxt;
    @BindView(R.id.addData2)
    Button addData;
    @BindView(R.id.addData2Next)
    Button next2;

    String univ;
    String kategori;
    String prodi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data2);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        univ = intent.getStringExtra("univ");
        kategori = intent.getStringExtra("kategori");
        prodi = intent.getStringExtra("prodi");

        Log.d("BLABLA", univ + prodi + kategori);
        loadData(univ, kategori, prodi);
    }

    public void showMessage(String txt) {
        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void setInputs(boolean enabled) {
        tambahDataTxt.setEnabled(enabled);
        addData.setEnabled(enabled);
        next2.setEnabled(enabled);
    }

    public void setProgramPilihan(List<String> programPilihan) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TambahDataProgramPilihanAdapter programPilihanAdapter = new TambahDataProgramPilihanAdapter(programPilihan, this);
        recyclerView.setAdapter(programPilihanAdapter);

        onLoadSuccess();
    }

    public void onGetdataSuccess(List<String> jurusan_pilihan) {

        if (jurusan_pilihan != null) {
            setProgramPilihan(jurusan_pilihan);
        } else {
            setProgramPilihan(Collections.singletonList("Data Kosong"));
        }

    }

    @OnClick(R.id.addData2)
    public void addDataOnClick() {
        setInputs(false);
        showProgress();
        if (!tambahDataTxt.getText().toString().isEmpty()){
            addData(tambahDataTxt.getText().toString());
        } else {
            setInputs(true);
            hideProgress();
            showMessage("Kolom Tidak Boleh Kosong");
        }

    }

    @OnClick(R.id.addData2Next)
    public void nextOnClick() {
        Intent intent = new Intent(this, TambahDataActivity3.class);
        intent.putExtra("univ", univ);
        intent.putExtra("kategori", kategori);
        intent.putExtra("prodi", prodi);
        Log.d("LOAD LOAD", univ + kategori + prodi);
        startActivity(intent);
    }

    public void loadData(String documentId, String collectionId, final String document2Id) {

        firebaseFirestore.collection("UNIVERSITAS").document(documentId).collection(collectionId).document(document2Id).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()) {
                            TambahData2Model detailProdiModel = documentSnapshot.toObject(TambahData2Model.class);
                            detailProdiModel.setId(document2Id);

                            List<String> jurusan_pilihan = null;
                            if (detailProdiModel.getJurusan_pemilih() != null) {
                                jurusan_pilihan = detailProdiModel.getJurusan_pemilih();

                                Log.d("LOAD LOAD anying", String.valueOf(jurusan_pilihan));
                            }
                            Log.d("LOAD LOAD ANY", String.valueOf(jurusan_pilihan));

                            onGetdataSuccess(jurusan_pilihan);

                        }

                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String errorMessage = e.getMessage();
                showMessage(errorMessage);
            }
        });

    }

    public void addData(String jurusan_pemilih) {

        List<String> pemilih = Arrays.asList(jurusan_pemilih);

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("jurusan_pemilih", pemilih);

        firebaseFirestore.collection("UNIVERSITAS").document(univ)
                .collection(kategori).document(prodi).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();

                    if (documentSnapshot.get("jurusan_pemilih") != null) {

                        firebaseFirestore.collection("UNIVERSITAS").document(univ)
                                .collection(kategori).document(prodi)
                                .update("jurusan_pemilih", FieldValue.arrayUnion(jurusan_pemilih)).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    onInputSucces();
                                } else {
                                    String errorMessage = task.getException().getMessage();
                                    showMessage(errorMessage);
                                }
                            }
                        });
                    } else {
                        firebaseFirestore.collection("UNIVERSITAS").document(univ)
                                .collection(kategori).document(prodi)
                                .update(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    onInputSucces();
                                } else {
                                    String errorMessage = task.getException().getMessage();
                                    showMessage(errorMessage);
                                }
                            }
                        });
                    }
                }
            }
        });

//        firebaseFirestore.collection("UNIVERSITAS").document(univ)
//                .collection(kategori).document(prodi)
//                .set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()){
//                    onInputSucces();
//                } else {
//                    String errorMessage = task.getException().getMessage();
//                    showMessage(errorMessage);
//                }
//            }
//        });
    }

    private void onInputSucces() {
        loadData(univ, kategori, prodi);
        Log.d("LOAD", univ + prodi + kategori);
    }

    public void onLoadSuccess() {
        hideProgress();
        setInputs(true);
        tambahDataTxt.setText("");
    }

}
