package com.example.univlist.DetailProdi.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.univlist.DetailProdi.Adapters.ProgramPilihanAdapter;
import com.example.univlist.DetailProdi.Adapters.SebaranSiswaAdapter;
import com.example.univlist.DetailProdi.Adapters.SebaranSiswaJurusanAdapter;
import com.example.univlist.DetailProdi.Presenter.DetailProdiPresInt;
import com.example.univlist.DetailProdi.Presenter.DetailProdiPresenter;
import com.example.univlist.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailProdiActivity extends AppCompatActivity implements DetailProdiActivityInt {

    private DetailProdiPresInt detailProdiPresInt;

    private Toolbar toolbar;
    private ProgressBar  detailProdiProgress;
    private RecyclerView rvProgramPilihan;
    private RecyclerView rvSebaranSiswaTahun;
    private RecyclerView rvSebaranSiswaJurusan;

    private TextView daerahTxt;
    private TextView prodiTxt;
    private TextView kategoriTxt;
    private TextView dayaTpngTxt;
    String[] mobileArray = {"Android", "IPhone", "WindowsMobile", "Blackberry",
            "WebOS", "Ubuntu", "Windows7", "Max OS X"};

    private String univ, prodi, kategori;

    @BindView(R.id.linkTxt)
    TextView linkTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_univ);

        ButterKnife.bind(this);

        detailProdiPresInt = new DetailProdiPresenter(this);
        detailProdiPresInt.onCreate();

        detailProdiProgress = findViewById(R.id.detail_prodi_progress);
        rvProgramPilihan = findViewById(R.id.rv_program_pilihan);
        rvSebaranSiswaTahun = findViewById(R.id.rv_sebaran_siswa);
        rvSebaranSiswaJurusan = findViewById(R.id.rv_sebaran_siswa_jurusan);
        daerahTxt = findViewById(R.id.daerahTxt);
        prodiTxt = findViewById(R.id.prodiTxt);
        kategoriTxt = findViewById(R.id.kategoriTxt);
        dayaTpngTxt = findViewById(R.id.dayaTpngTxt);
        toolbar = findViewById(R.id.Toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        univ = intent.getStringExtra("univ");
        prodi = intent.getStringExtra("prodi");
        kategori = intent.getStringExtra("kategori");
        Log.d("ACTIVITY", univ + prodi + kategori);
        detailProdiPresInt.getData(univ, kategori, prodi);

        toolbar.setTitle(univ);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailProdiPresInt.onDestroy();
    }

    @Override
    public void setDaerah(String daerah) {
        daerahTxt.setText(daerah);
    }

    @Override
    public void setDaerahRed() {
        daerahTxt.setTextColor(getResources().getColor(R.color.red_400));
    }

    @Override
    public void setProdi(String prodi) {
        prodiTxt.setText(prodi);
    }

    @Override
    public void setProdiRed() {
        prodiTxt.setTextColor(getResources().getColor(R.color.red_400));
    }

    @Override
    public void setKategori(String kategori) {
        kategoriTxt.setText(kategori);
    }

    @Override
    public void setKategoriRed() {

    }

    @Override
    public void setDayaTampung(String dayaTampung) {
        dayaTpngTxt.setText(dayaTampung);
    }

    @Override
    public void setDayaTampungRed() {

    }

    @Override
    public void setProgramPilihan(List<String> programPilihan) {
        rvProgramPilihan.setLayoutManager(new LinearLayoutManager(this));
        ProgramPilihanAdapter programPilihanAdapter = new ProgramPilihanAdapter(programPilihan, this);
        rvProgramPilihan.setAdapter(programPilihanAdapter);
    }

    @Override
    public void setSebaranSiswaTahun(List<Integer> tahun, List<Integer> pendaftar, List<Integer> diterima) {
        rvSebaranSiswaTahun.setLayoutManager(new LinearLayoutManager(this));
        SebaranSiswaAdapter sebaranSiswaAdapter = new SebaranSiswaAdapter(tahun, pendaftar, diterima, this);
        rvSebaranSiswaTahun.setAdapter(sebaranSiswaAdapter);
    }

    @Override
    public void setSebaranSiswaJurusan(List<String> sekolahJurusan, List<String> tahunJurusan, List<String> diterimaJurusan) {
        rvSebaranSiswaJurusan.setLayoutManager(new LinearLayoutManager(this));
        SebaranSiswaJurusanAdapter sebaranSiswaJurusanAdapter = new SebaranSiswaJurusanAdapter(sekolahJurusan, tahunJurusan, diterimaJurusan, this);
        rvSebaranSiswaJurusan.setAdapter(sebaranSiswaJurusanAdapter);
    }

    @Override
    public void setLink(String link) {
        linkTxt.setText(link);
        linkTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(link));
                startActivity(intent);
            }
        });
    }

    @Override
    public void hideProgress() {
        detailProdiProgress.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        detailProdiProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG)
                .show();
    }
}
