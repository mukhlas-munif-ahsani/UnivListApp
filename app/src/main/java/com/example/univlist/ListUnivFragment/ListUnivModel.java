package com.example.univlist.ListUnivFragment;

import com.google.firebase.firestore.DocumentId;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListUnivModel {

    @DocumentId
    private String univId;
    private String nama;
    private String daerah;
    private List<String> prodi;

    public ListUnivModel(){

    }

    public ListUnivModel(String univId, String nama, String daerah, List<String> prodi) {
        this.univId = univId;
        this.nama = nama;
        this.daerah = daerah;
        this.prodi = prodi;
    }

    public String getUnivId() {
        return univId;
    }

    public void setUnivId(String univId) {
        this.univId = univId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public List<String> getProdi() {
        return prodi;
    }

    public void setProdi(List<String> prodi) {
        this.prodi = prodi;
    }
}
