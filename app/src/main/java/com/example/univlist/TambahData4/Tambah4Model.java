package com.example.univlist.TambahData4;

import com.google.firebase.firestore.DocumentId;

import java.util.List;

public class Tambah4Model {

    @DocumentId
    private String id;
    private List<String> sekolah_jurusan;
    private List<String> tahun_jurusan;
    private List<String> diterima_jurusan;

    public Tambah4Model(){}

    public Tambah4Model(String id, List<String> sekolah_jurusan, List<String> tahun_jurusan, List<String> diterima_jurusan) {
        this.id = id;
        this.sekolah_jurusan = sekolah_jurusan;
        this.tahun_jurusan = tahun_jurusan;
        this.diterima_jurusan = diterima_jurusan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
