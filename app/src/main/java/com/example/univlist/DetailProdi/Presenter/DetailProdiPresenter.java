package com.example.univlist.DetailProdi.Presenter;

import android.util.Log;

import com.example.univlist.DetailProdi.DetailProdiEvents;
import com.example.univlist.DetailProdi.Model.DetailProdiRepo;
import com.example.univlist.DetailProdi.View.DetailProdiActivityInt;
import com.example.univlist.EventBus.EventBus;
import com.example.univlist.EventBus.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DetailProdiPresenter implements DetailProdiPresInt {
    private DetailProdiRepo detailProdiRepo;
    private DetailProdiActivityInt mDetailProdiActivityInt;

    private EventBus eventBus;

    public DetailProdiPresenter(DetailProdiActivityInt detailProdiActivityInt) {
        detailProdiRepo = new DetailProdiRepo();
        mDetailProdiActivityInt = detailProdiActivityInt;
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Subscribe
    public void onEventMainThread(DetailProdiEvents events) {
        switch (events.getEventType()) {
            case DetailProdiEvents.onGetDataSuccess:
                onGetdataSuccess(events.getDataDaerah(), events.getDataDayaTampung(), events.getDataKategori(), events.getDataProdi(), events.getLink(), events.getDataJurusanPemilih(), events.getDataTahun(), events.getDataPendaftar(), events.getDataDiterima(), events.getDataSekolahJurusan(), events.getDataTahunJurusan(), events.getDataDiterimaJurusan());
                Log.d("name event main", "" + events.getDataDaerah());
                break;
            case DetailProdiEvents.onGetDataError:
                onGetDataError(events.getError());
                break;
        }
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mDetailProdiActivityInt = null;
        eventBus.unregister(this);
    }

    public void onGetdataSuccess(String daerah, String daya_tampung, String kategori, String prodi, String link, List<String> jurusan_pilihan, List<Integer> tahun, List<Integer> pendaftar, List<Integer> diterima, List<String> sekolah_jurusan, List<String> tahun_jurusan, List<String> diterima_jurusan) {

        if (daerah != null) {
            mDetailProdiActivityInt.setDaerah(daerah);
        } else {
            mDetailProdiActivityInt.setDaerah("Data Kosong");
        }

        if (daya_tampung != null) {
            mDetailProdiActivityInt.setDayaTampung(daya_tampung);
        } else {
            mDetailProdiActivityInt.setDayaTampung("Data Kosong");
        }

        if (kategori != null) {
            mDetailProdiActivityInt.setKategori(kategori);
        } else {
            mDetailProdiActivityInt.setKategori("Data Kosong");
        }

        if (prodi != null) {
            mDetailProdiActivityInt.setProdi(prodi);
        } else {
            mDetailProdiActivityInt.setProdi("Data Kosong");
            mDetailProdiActivityInt.setProdiRed();
        }

        if (link != null) {
            mDetailProdiActivityInt.setLink(link);
        } else {
            mDetailProdiActivityInt.setLink("Data Kosong");
        }

        if (jurusan_pilihan != null) {
            mDetailProdiActivityInt.setProgramPilihan(jurusan_pilihan);
        } else {
            mDetailProdiActivityInt.setProgramPilihan(Collections.singletonList("Data Kosong"));
        }

        if (tahun != null || pendaftar != null || diterima != null) {
            mDetailProdiActivityInt.setSebaranSiswaTahun(tahun, pendaftar, diterima);
        } else {
            mDetailProdiActivityInt.setSebaranSiswaTahun(Collections.singletonList(0), Collections.singletonList(0), Collections.singletonList(0));
        }

        if (sekolah_jurusan != null || tahun_jurusan != null || diterima_jurusan != null){
            mDetailProdiActivityInt.setSebaranSiswaJurusan(sekolah_jurusan, tahun_jurusan, diterima_jurusan);
        } else {
            mDetailProdiActivityInt.setSebaranSiswaJurusan(Collections.singletonList("Data Kosong"), Collections.singletonList("0"), Collections.singletonList("0"));
        }

        mDetailProdiActivityInt.hideProgress();
    }

    public void onGetDataError(String errorMsg){
        mDetailProdiActivityInt.showMessage(errorMsg);
    }

    public void getData(String univ, String kategori, String prodi) {
        Log.d("DETAIL PRODI PRESS", univ + kategori + prodi);
        detailProdiRepo.loadData(univ, kategori, prodi);
    }
}
