package com.example.univlist.TambahData2;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.Exclude;

import java.util.List;

public class TambahData2Model {
    @DocumentId
    private String id;
    private List<String> jurusan_pemilih;

    public TambahData2Model(){}

    public TambahData2Model(String id, List<String> jurusan_pemilih) {
        this.id = id;
        this.jurusan_pemilih = jurusan_pemilih;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getJurusan_pemilih() {
        return jurusan_pemilih;
    }

    public void setJurusan_pemilih(List<String> jurusan_pemilih) {
        this.jurusan_pemilih = jurusan_pemilih;
    }
}
