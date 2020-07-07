package com.example.univlist.DetailProdi;

import java.util.List;

public class DetailProdiEvents {
    public static final int onGetDataSuccess = 0;
    public static final int onGetDataError = 1;

    private int eventType;
    private String Error;

    private String dataDaerah, dataKategori, dataProdi, dataDayaTampung, link;
    private List<Integer> dataTahun;
    private List<Integer> dataPendaftar;
    private List<Integer> dataDiterima;
    private List<String> dataJurusanPemilih;
    private List<String> dataSekolahJurusan;
    private List<String> dataTahunJurusan;
    private List<String> dataDiterimaJurusan;

    public static int getOnGetDataSuccess() {
        return onGetDataSuccess;
    }

    public static int getOnGetDataError() {
        return onGetDataError;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getDataDaerah() {
        return dataDaerah;
    }

    public void setDataDaerah(String dataDaerah) {
        this.dataDaerah = dataDaerah;
    }

    public String getDataKategori() {
        return dataKategori;
    }

    public void setDataKategori(String dataKategori) {
        this.dataKategori = dataKategori;
    }

    public String getDataProdi() {
        return dataProdi;
    }

    public void setDataProdi(String dataProdi) {
        this.dataProdi = dataProdi;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDataDayaTampung() {
        return dataDayaTampung;
    }

    public void setDataDayaTampung(String dataDayaTampung) {
        this.dataDayaTampung = dataDayaTampung;
    }

    public List<Integer> getDataTahun() {
        return dataTahun;
    }

    public void setDataTahun(List<Integer> dataTahun) {
        this.dataTahun = dataTahun;
    }

    public List<Integer> getDataPendaftar() {
        return dataPendaftar;
    }

    public void setDataPendaftar(List<Integer> dataPendaftar) {
        this.dataPendaftar = dataPendaftar;
    }

    public List<Integer> getDataDiterima() {
        return dataDiterima;
    }

    public void setDataDiterima(List<Integer> dataDiterima) {
        this.dataDiterima = dataDiterima;
    }

    public List<String> getDataJurusanPemilih() {
        return dataJurusanPemilih;
    }

    public void setDataJurusanPemilih(List<String> dataJurusanPemilih) {
        this.dataJurusanPemilih = dataJurusanPemilih;
    }

    public List<String> getDataSekolahJurusan() {
        return dataSekolahJurusan;
    }

    public void setDataSekolahJurusan(List<String> dataSekolahJurusan) {
        this.dataSekolahJurusan = dataSekolahJurusan;
    }

    public List<String> getDataTahunJurusan() {
        return dataTahunJurusan;
    }

    public void setDataTahunJurusan(List<String> dataTahunJurusan) {
        this.dataTahunJurusan = dataTahunJurusan;
    }

    public List<String> getDataDiterimaJurusan() {
        return dataDiterimaJurusan;
    }

    public void setDataDiterimaJurusan(List<String> dataDiterimaJurusan) {
        this.dataDiterimaJurusan = dataDiterimaJurusan;
    }
}
