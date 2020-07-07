package com.example.univlist.TambahData4;

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

import com.example.univlist.DetailProdi.Adapters.SebaranSiswaJurusanAdapter;
import com.example.univlist.MainActivity;
import com.example.univlist.R;
import com.example.univlist.TambahData3.Tambah3Model;
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

public class TambahDataActivity4 extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @BindView(R.id.tambahData4Progress)
    ProgressBar progressBar;
    @BindView(R.id.rv_sebaran_siswa_jurusan_tambah_data)
    RecyclerView recyclerView;
    @BindView(R.id.add_tambah_data_4_1)
    TextInputEditText mSekolahJurusan;
    @BindView(R.id.add_tambah_data_4_2)
    TextInputEditText mTahunJurusan;
    @BindView(R.id.add_tambah_data_4_3)
    TextInputEditText mDiterimaJurusan;
    @BindView(R.id.addData4)
    Button mAddData;
    @BindView(R.id.addData4Next)
    Button mNext;

    String univ;
    String kategori;
    String prodi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data4);

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
        mSekolahJurusan.setEnabled(enabled);
        mTahunJurusan.setEnabled(enabled);
        mDiterimaJurusan.setEnabled(enabled);
        mAddData.setEnabled(enabled);
        mNext.setEnabled(enabled);
    }

    public void setSebaranSiswaJurusan(List<String> sekolahJurusan, List<String> tahunJurusan, List<String> diterimaJurusan) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SebaranSiswaJurusanAdapter sebaranSiswaJurusanAdapter = new SebaranSiswaJurusanAdapter(sekolahJurusan, tahunJurusan, diterimaJurusan, this);
        recyclerView.setAdapter(sebaranSiswaJurusanAdapter);

        onLoadSuccess();
    }

    public void onGetdataSuccess(List<String> sekolah_jurusan, List<String> tahun_jurusan, List<String> diterima_jurusan) {

        if (sekolah_jurusan != null || tahun_jurusan != null || diterima_jurusan != null){
            setSebaranSiswaJurusan(sekolah_jurusan, tahun_jurusan, diterima_jurusan);
        } else {
            setSebaranSiswaJurusan(Collections.singletonList("Data Kosong"), Collections.singletonList("0"), Collections.singletonList("0"));
        }

    }

    @OnClick(R.id.addData4)
    public void addDataOnClick() {
        showProgress();
        setInputs(false);
        if (!mSekolahJurusan.getText().toString().isEmpty() && !mTahunJurusan.getText().toString().isEmpty() && !mDiterimaJurusan.getText().toString().isEmpty()){
            addData(mSekolahJurusan.getText().toString(),
                    mTahunJurusan.getText().toString(),
                    mDiterimaJurusan.getText().toString());
        } else {
            hideProgress();
            setInputs(true);
            showMessage("Kolom Tidak Boleh Kosong");
        }

    }

    @OnClick(R.id.addData4Next)
    public void nextOnClick() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void addData(String sekolahJurusan, String tahunJurusan, String diterimaJurusan) {

        List<String> mSekolahJurusan = Arrays.asList(sekolahJurusan);
        List<String> mTahunJurusan = Arrays.asList(tahunJurusan);
        List<String> mDiterimaJurusan = Arrays.asList(diterimaJurusan);

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("sekolah_jurusan", mSekolahJurusan);
        userMap.put("tahun_jurusan", mTahunJurusan);
        userMap.put("diterima_jurusan", mDiterimaJurusan);

        firebaseFirestore.collection("UNIVERSITAS").document(univ)
                .collection(kategori).document(prodi).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();

                    if (documentSnapshot.get("a_tahun") != null) {

                        firebaseFirestore.collection("UNIVERSITAS").document(univ)
                                .collection(kategori).document(prodi)
                                .update("sekolah_jurusan", FieldValue.arrayUnion(sekolahJurusan),
                                        "tahun_jurusan", FieldValue.arrayUnion(tahunJurusan),
                                        "diterima_jurusan", FieldValue.arrayUnion(diterimaJurusan)).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                            Tambah4Model tambah4Model = documentSnapshot.toObject(Tambah4Model.class);
                            tambah4Model.setId(document2Id);

                            List<String> sekolah = null;
                            if (tambah4Model.getSekolah_jurusan() != null) {
                                sekolah = tambah4Model.getSekolah_jurusan();
                            }

                            List<String> tahunJurusan = null;
                            if (tambah4Model.getTahun_jurusan() != null) {
                                tahunJurusan = tambah4Model.getTahun_jurusan();
                            }

                            List<String> diterimaJurusan = null;
                            if (tambah4Model.getDiterima_jurusan() != null) {
                                diterimaJurusan = tambah4Model.getDiterima_jurusan();
                            }

                            onGetdataSuccess(sekolah, tahunJurusan, diterimaJurusan);

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

    public void onInputSucces(){
        loadData(univ, kategori, prodi);
    }

    private void onLoadSuccess() {
        hideProgress();
        setInputs(true);
        mSekolahJurusan.setText("");
        mTahunJurusan.setText("");
        mDiterimaJurusan.setText("");
    }
}
