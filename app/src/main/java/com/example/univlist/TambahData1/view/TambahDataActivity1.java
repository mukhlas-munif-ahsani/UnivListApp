package com.example.univlist.TambahData1.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.univlist.R;
import com.example.univlist.TambahData2.TambahDataActivity2;
import com.example.univlist.TambahData1.pres.TambahDataPres;
import com.example.univlist.TambahData1.pres.TambahDataPresInt;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TambahDataActivity1 extends AppCompatActivity implements TambahDataViewInt {

    private TambahDataPresInt tambahDataPresInt;

    private TextInputEditText mNamaUniv;
    private TextInputEditText mNamaProdi;
    private AutoCompleteTextView mKategoriProdi;
    private TextInputEditText mDaerahUniv;
    private TextInputEditText mDayaTampung;
    private TextInputEditText mLink;

    private Button mAddData1Btn;
    private Button mBatalBtn;

    @BindView(R.id.tambahDataProgress)
    ProgressBar progressBar;

    @BindView(R.id.add_nama)
    TextInputLayout univLay;
    @BindView(R.id.addKategori)
    TextInputLayout prodiLay;
    @BindView(R.id.addUniv)
    TextInputLayout kategoriLay;
    @BindView(R.id.addDaerah)
    TextInputLayout daerahLay;
    @BindView(R.id.addLink)
    TextInputLayout dayaLay;
    @BindView(R.id.addDayaTampung)
    TextInputLayout linkLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        ButterKnife.bind(this);

        tambahDataPresInt = new TambahDataPres(this);
        tambahDataPresInt.onCreate();

        mNamaUniv = findViewById(R.id.add_univ_field);
        mNamaProdi = findViewById(R.id.add_prodi);
        mKategoriProdi = findViewById(R.id.add_kategori_dropdown);
        mDaerahUniv = findViewById(R.id.add_daerah);
        mDayaTampung = findViewById(R.id.add_daya_tampung);
        mLink = findViewById(R.id.add_link);

        mAddData1Btn = findViewById(R.id.addData1);
        mBatalBtn = findViewById(R.id.batal_btn);

        String[] levelItems = new String[]{
                "SOSHUM",
                "SAINTEK"
        };

        ArrayAdapter<String> kategori = new ArrayAdapter<>(
                TambahDataActivity1.this, R.layout.dropdown_kategori, levelItems
        );

        mKategoriProdi.setAdapter(kategori);
    }

    @Override
    protected void onDestroy() {
        tambahDataPresInt.onDestroy();
        super.onDestroy();
    }

    @Override
    public void shwoProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showMessage(String txt) {
        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setUnivError(String txt) {
        univLay.setError(txt);
    }

    @Override
    public void setProdiError(String txt) {
        prodiLay.setError(txt);
    }

    @Override
    public void setDaerahError(String txt) {
        daerahLay.setError(txt);
    }

    @Override
    public void setDayaError(String txt) {
        dayaLay.setError(txt);
    }

    @Override
    public void setLinkError(String txt) {
        linkLay.setError(txt);
    }

    @Override
    public void setKategoriError(String txt) {
        kategoriLay.setError(txt);
    }

    @Override
    public void setInputs(boolean enabled) {
        mNamaUniv.setEnabled(enabled);
        mNamaProdi.setEnabled(enabled);
        mKategoriProdi.setEnabled(enabled);
        mDaerahUniv.setEnabled(enabled);
        mDayaTampung.setEnabled(enabled);
        mLink.setEnabled(enabled);
        mAddData1Btn.setEnabled(enabled);
        mBatalBtn.setEnabled(enabled);
    }

    @OnClick(R.id.addData1)
    public void onClick() {
        String univ = mNamaUniv.getText().toString();
        String prodi = mNamaProdi.getText().toString();
        String kategori = mKategoriProdi.getText().toString();
        String daerah = mDaerahUniv.getText().toString();
        String daya = mDayaTampung.getText().toString();
        String link = mLink.getText().toString();

        if (!univ.isEmpty() && !prodi.isEmpty() && !kategori.isEmpty() && !daerah.isEmpty() && !daya.isEmpty() && !link.isEmpty()) {
            tambahDataPresInt.validateData(univ, prodi, kategori, daerah, daya, link);
        } else {
            showMessage("Kolom Tidak Boleh Kosong");
        }

    }

    @OnClick({R.id.batal_btn})
    public void batalOnClick() {
        finish();
    }

    @Override
    public void navigateToTambah2(String univ, String kategori, String prodi) {
        Intent intent = new Intent(this, TambahDataActivity2.class);
        intent.putExtra("univ", univ);
        intent.putExtra("kategori", kategori);
        intent.putExtra("prodi", prodi);
        Log.d("ACTIVITY", univ + prodi + kategori);
        startActivity(intent);
        finish();
    }
}
