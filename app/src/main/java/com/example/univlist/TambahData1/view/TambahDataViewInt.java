package com.example.univlist.TambahData1.view;

public interface TambahDataViewInt {
    void shwoProgress();
    void hideProgress();
    void showMessage(String txt);
    void setUnivError(String txt);
    void setProdiError(String txt);
    void setDaerahError(String txt);
    void setDayaError(String txt);
    void setLinkError(String txt);
    void setKategoriError(String txt);
    void setInputs(boolean enabled);
    void navigateToTambah2(String univ, String kategori, String prodi);
}
