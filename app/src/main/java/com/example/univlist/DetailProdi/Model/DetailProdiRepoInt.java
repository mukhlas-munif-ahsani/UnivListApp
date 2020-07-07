package com.example.univlist.DetailProdi.Model;

public interface DetailProdiRepoInt {

    void getData(String documentId, String collectionId, String document2Id);
    void loadData(String documentId, String collectionId, final String document2Id);
}
