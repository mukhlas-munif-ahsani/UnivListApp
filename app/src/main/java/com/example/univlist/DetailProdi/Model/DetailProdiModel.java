package com.example.univlist.DetailProdi.Model;

import com.google.firebase.firestore.Exclude;

import java.util.List;

public class DetailProdiModel {
    private String documentId;
    private String daerah;
    private String daya_tampung;
    private String kategori;
    private String prodi;
    private String link;
    private List<Integer> a_tahun;
    private List<String> jurusan_pemilih;
    private List<Integer> b_pendaftar;
    private List<Integer> c_diterima;
    private List<String> sekolah_jurusan;
    private List<String> tahun_jurusan;
    private List<String> diterima_jurusan;

    public DetailProdiModel(){

    }

    public DetailProdiModel(String documentId, String daerah, String daya_tampung, String kategori, String prodi, String link, List<Integer> a_tahun, List<String> jurusan_pemilih, List<Integer> b_pendaftar, List<Integer> c_diterima, List<String> sekolah_jurusan, List<String> tahun_jurusan, List<String> diterima_jurusan) {
        this.documentId = documentId;
        this.daerah = daerah;
        this.daya_tampung = daya_tampung;
        this.kategori = kategori;
        this.prodi = prodi;
        this.link = link;
        this.a_tahun = a_tahun;
        this.jurusan_pemilih = jurusan_pemilih;
        this.b_pendaftar = b_pendaftar;
        this.c_diterima = c_diterima;
        this.sekolah_jurusan = sekolah_jurusan;
        this.tahun_jurusan = tahun_jurusan;
        this.diterima_jurusan = diterima_jurusan;
    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getDaya_tampung() {
        return daya_tampung;
    }

    public void setDaya_tampung(String daya_tampung) {
        this.daya_tampung = daya_tampung;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Integer> getA_tahun() {
        return a_tahun;
    }

    public void setA_tahun(List<Integer> a_tahun) {
        this.a_tahun = a_tahun;
    }

    public List<String> getJurusan_pemilih() {
        return jurusan_pemilih;
    }

    public void setJurusan_pemilih(List<String> jurusan_pemilih) {
        this.jurusan_pemilih = jurusan_pemilih;
    }

    public List<Integer> getB_pendaftar() {
        return b_pendaftar;
    }

    public void setB_pendaftar(List<Integer> b_pendaftar) {
        this.b_pendaftar = b_pendaftar;
    }

    public List<Integer> getC_diterima() {
        return c_diterima;
    }

    public void setC_diterima(List<Integer> c_diterima) {
        this.c_diterima = c_diterima;
    }

    public List<String> getSekolah_jurusan() {
        return sekolah_jurusan;
    }

    public void setSekolah_jurusan(List<String> sekolah_jurusan) {
        this.sekolah_jurusan = sekolah_jurusan;
    }

    public List<String> getTahun_jurusan() {
        return tahun_jurusan;
    }

    public void setTahun_jurusan(List<String> tahun_jurusan) {
        this.tahun_jurusan = tahun_jurusan;
    }

    public List<String> getDiterima_jurusan() {
        return diterima_jurusan;
    }

    public void setDiterima_jurusan(List<String> diterima_jurusan) {
        this.diterima_jurusan = diterima_jurusan;
    }
}
