package com.example.univlist.TambahData1.repo;

import java.util.List;

public class UserMap2 {
    private String daerah;
    private List<String> prodi;

    public UserMap2(){}

    public UserMap2(String daerah, List<String> prodi) {
        this.daerah = daerah;
        this.prodi = prodi;
    }

    public String getDaerah() {
        return daerah;
    }

    public List<String> getProdi() {
        return prodi;
    }
}
