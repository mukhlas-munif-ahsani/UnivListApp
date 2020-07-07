package com.example.univlist.SoshumFragment;

import com.google.firebase.firestore.DocumentId;

import java.util.List;

public class SoshumListModel {

    @DocumentId
    private String soshum_id;
    private String jumlah_univ;
    private String kategori;
    private List<String> univ;

    public SoshumListModel(){

    }

    public SoshumListModel(String soshum_id, String jumlah_univ, String kategori, List<String> univ) {
        this.soshum_id = soshum_id;
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

    public String getSoshum_id() {
        return soshum_id;
    }

    public void setSoshum_id(String soshum_id) {
        this.soshum_id = soshum_id;
    }

    public String getJumlah_univ() {
        return jumlah_univ;
    }

    public void setJumlah_univ(String jumlah_univ) {
        this.jumlah_univ = jumlah_univ;
    }
}
