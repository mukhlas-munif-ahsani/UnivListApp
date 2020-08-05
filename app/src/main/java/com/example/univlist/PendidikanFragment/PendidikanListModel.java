package com.example.univlist.PendidikanFragment;

import com.google.firebase.firestore.DocumentId;

import java.util.List;

public class PendidikanListModel {
    @DocumentId
    private String saintek_id;
    private String jumlah_univ;
    private String kategori;
    private List<String> univ;

    public PendidikanListModel (){

    }

    public PendidikanListModel(String saintek_id, String jumlah_univ, String kategori, List<String> univ) {
        this.saintek_id = saintek_id;
        this.jumlah_univ = jumlah_univ;
        this.kategori = kategori;
        this.univ = univ;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public List<String> getUniv() {
        return univ;
    }

    public void setUniv(List<String> univ) {
        this.univ = univ;
    }

    public String getSaintek_id() {
        return saintek_id;
    }

    public void setSaintek_id(String saintek_id) {
        this.saintek_id = saintek_id;
    }

    public String getJumlah_univ() {
        return jumlah_univ;
    }

    public void setJumlah_univ(String jumlah_univ) {
        this.jumlah_univ = jumlah_univ;
    }
}
