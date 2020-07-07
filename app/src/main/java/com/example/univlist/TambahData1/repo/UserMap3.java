package com.example.univlist.TambahData1.repo;

import java.util.List;

public class UserMap3 {
    private String kategori;
    private List<String> univ;

    public UserMap3(){}

    public UserMap3(String kategori, List<String> univ) {
        this.kategori = kategori;
        this.univ = univ;
    }

    public String getKategori() {
        return kategori;
    }

    public List<String> getUniv() {
        return univ;
    }
}
