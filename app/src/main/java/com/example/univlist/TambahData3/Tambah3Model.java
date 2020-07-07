package com.example.univlist.TambahData3;

import com.google.firebase.firestore.DocumentId;

import java.util.List;

public class Tambah3Model {
    @DocumentId
    private String id;
    private List<Integer> a_tahun;
    private List<Integer> b_pendaftar;
    private List<Integer> c_diterima;

    public Tambah3Model(){}

    public Tambah3Model(String id, List<Integer> a_tahun, List<Integer> b_pendaftar, List<Integer> c_diterima) {
        this.id = id;
        this.a_tahun = a_tahun;
        this.b_pendaftar = b_pendaftar;
        this.c_diterima = c_diterima;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getA_tahun() {
        return a_tahun;
    }

    public void setA_tahun(List<Integer> a_tahun) {
        this.a_tahun = a_tahun;
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
}
