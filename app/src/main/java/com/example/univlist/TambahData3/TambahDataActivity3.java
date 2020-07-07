package com.example.univlist.TambahData3;

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

import com.example.univlist.DetailProdi.Adapters.SebaranSiswaAdapter;
import com.example.univlist.R;
import com.example.univlist.TambahData2.TambahData2Model;
import com.example.univlist.TambahData4.TambahDataActivity4;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TambahDataActivity3 extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @BindView(R.id.tambahData3Progress)
    ProgressBar progressBar;
    @BindView(R.id.rv_sebaran_siswa_tambah_data)
    RecyclerView recyclerView;
    @BindView(R.id.add_tambah_data_3_1)
    TextInputEditText tahun;
    @BindView(R.id.add_tambah_data_3_2)
    TextInputEditText pendaftar;
    @BindView(R.id.add_tambah_data_3_3)
    TextInputEditText diterima;
    @BindView(R.id.addData3)
    Button tambah;
    @BindView(R.id.addData3Next)
    Button next;

    String univ;
    String kategori;
    String prodi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data3);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        univ = intent.getStringExtra("univ");
        kategori = intent.getStringExtra("kategori");
        prodi = intent.getStringExtra("prodi");

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
        tahun.setEnabled(enabled);
        pendaftar.setEnabled(enabled);
        diterima.setEnabled(enabled);
        tambah.setEnabled(enabled);
        next.setEnabled(enabled);
    }

    public void setSebaranSiswaTahun(List<Integer> tahun, List<Integer> pendaftar, List<Integer> diterima) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Tambah3Adapter sebaranSiswaAdapter = new Tambah3Adapter(tahun, pendaftar, diterima, this);
        recyclerView.setAdapter(sebaranSiswaAdapter);

        onLoadSucces();
    }

    public void onGetdataSuccess(List<Integer> tahun, List<Integer> pendaftar, List<Integer> diterima) {

        if (tahun != null || pendaftar != null || diterima != null) {
            setSebaranSiswaTahun(tahun, pendaftar, diterima);
        } else {
            setSebaranSiswaTahun(Collections.singletonList(0), Collections.singletonList(0), Collections.singletonList(0));
        }

    }

    @OnClick(R.id.addData3)
    public void addDataOnClick() {
        showProgress();
        setInputs(false);
        if (!tahun.getText().toString().isEmpty() && !pendaftar.getText().toString().isEmpty() && !diterima.getText().toString().isEmpty()){
            addData(Integer.parseInt(tahun.getText().toString()),
                    Integer.parseInt(pendaftar.getText().toString()),
                    Integer.parseInt(diterima.getText().toString()));
        } else {
            hideProgress();
            setInputs(true);
            showMessage("Kolom Tidak Boleh Kosong");
        }

    }

    @OnClick(R.id.addData3Next)
    public void nextOnClick() {
        Intent intent = new Intent(this, TambahDataActivity4.class);
        intent.putExtra("univ", univ);
        intent.putExtra("kategori", kategori);
        intent.putExtra("prodi", prodi);
        Log.d("LOAD LOAD", univ + kategori + prodi);
        startActivity(intent);
    }

    public void addData(Integer tahun, Integer pendaftar, Integer diterima) {

        List<Integer> mTahun = Arrays.asList(tahun);
        List<Integer> mPendaftar = Arrays.asList(pendaftar);
        List<Integer> mDiterima = Arrays.asList(diterima);

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("a_tahun", mTahun);
        userMap.put("b_pendaftar", mPendaftar);
        userMap.put("c_diterima", mDiterima);

        firebaseFirestore.collection("UNIVERSITAS").document(univ)
                .collection(kategori).document(prodi).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();

                    if (documentSnapshot.get("a_tahun") != null) {

                        firebaseFirestore.collection("UNIVERSITAS").document(univ)
                                .collection(kategori).document(prodi)
                                .update("a_tahun", FieldValue.arrayUnion(tahun),
                                        "b_pendaftar", FieldValue.arrayUnion(pendaftar),
                                        "c_diterima", FieldValue.arrayUnion(diterima)).addOnCompleteListener(new OnCompleteListener<Void>() {
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

    }

    public void loadData(String documentId, String collectionId, final String document2Id) {

        firebaseFirestore.collection("UNIVERSITAS").document(documentId).collection(collectionId).document(document2Id).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()) {
                            Tambah3Model tambah3Model = documentSnapshot.toObject(Tambah3Model.class);
                            tambah3Model.setId(document2Id);

                            List<Integer> tahun = null;
                            if (tambah3Model.getA_tahun() != null) {
                                tahun = tambah3Model.getA_tahun();
                            }

                            List<Integer> pendaftar = null;
                            if (tambah3Model.getB_pendaftar() != null) {
                                pendaftar = tambah3Model.getB_pendaftar();
                            }

                            List<Integer> diterima = null;
                            if (tambah3Model.getC_diterima() != null) {
                                diterima = tambah3Model.getC_diterima();
                            }

                            onGetdataSuccess(tahun, pendaftar, diterima);

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

    public void onInputSucces() {
        loadData(univ, kategori, prodi);
    }

    public void onLoadSucces() {
        hideProgress();
        setInputs(true);
        tahun.setText("");
        pendaftar.setText("");
        diterima.setText("");
    }
}
