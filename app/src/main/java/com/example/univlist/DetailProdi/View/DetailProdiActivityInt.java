package com.example.univlist.DetailProdi.View;

import java.util.List;

public interface DetailProdiActivityInt {
    void setDaerah (String daerah);
    void setDaerahRed ();
    void setProdi (String prodi);
    void setProdiRed ();
    void setKategori (String kategori);
    void setKategoriRed ();
    void setDayaTampung (String dayaTampung);
    void setDayaTampungRed ();
    void setProgramPilihan (List<String> programPilihan);
    void setSebaranSiswaTahun(List<Integer> tahun, List<Integer> pendaftar, List<Integer> diterima);
    void setSebaranSiswaJurusan(List<String> sekolahJurusan, List<String> tahunJurusan, List<String> diterimaJurusan);
    void setLink(String link);
    void hideProgress();
    void showProgress();
    void showMessage(String msg);
}
