package com.example.univlist.DetailProdi.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.univlist.DetailProdi.DetailProdiEvents;
import com.example.univlist.EventBus.EventBus;
import com.example.univlist.EventBus.GreenRobotEventBus;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class DetailProdiRepo implements DetailProdiRepoInt {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = firebaseFirestore.collection("UNIVERSITAS");

    public DetailProdiRepo() {
//        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void getData(String documentId, String collectionId, String document2Id) {

//        firebaseFirestore.collection("UNIVERSITAS").document(documentId).collection(collectionId).document(document2Id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    String daerah = task.getResult().getString("daerah");
//                    String daya_tampung = task.getResult().getString("daya tampung");
//                    String kategori = task.getResult().getString("kategori");
//                    String prodi = task.getResult().getString("prodi");
//                    List<String> jurusan_pilihan = (List<String>) task.getResult().get("jurusan_pilihan");
//                    List<String> tahun = (List<String>) task.getResult().get("a_tahun");
//                    List<Integer> pendaftar = (List<Integer>) task.getResult().get("b_pendaftar");
//                    List<Integer> diterima = (List<Integer>) task.getResult().get("c_diterima");
//
////                    Log.d("ARRAY JURUSAN", jurusan_pilihan);
//
//                    postEvent(DetailProdiEvents.onGetDataSuccess, null, daerah, daya_tampung, kategori, prodi, jurusan_pilihan, tahun, pendaftar, diterima);
//                } else {
//                    String errorMessage = task.getException().getMessage();
//
//                }
//            }
//        });
    }

    public void loadData(String documentId, String collectionId, final String document2Id) {
        collectionReference.document(documentId).collection(collectionId).document(document2Id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists()) {
                    DetailProdiModel detailProdiModel = documentSnapshot.toObject(DetailProdiModel.class);
                    detailProdiModel.setDocumentId(document2Id);

                    String daerah = null;
                    if (detailProdiModel.getDaerah() != null) {
                        daerah = detailProdiModel.getDaerah();
                    }

                    String daya_tampung = null;
                    if (detailProdiModel.getDaya_tampung() != null) {
                        daya_tampung = detailProdiModel.getDaya_tampung();
                    }

                    String kategori = null;
                    if (detailProdiModel.getKategori() != null) {
                        kategori = detailProdiModel.getKategori();
                    }

                    String prodi = null;
                    if (detailProdiModel.getProdi() != null) {
                        prodi = detailProdiModel.getProdi();
                    }

                    String link = null;
                    if (detailProdiModel.getLink() != null) {
                        link = detailProdiModel.getLink();
                    }

                    List<String> jurusan_pilihan = null;
                    if (detailProdiModel.getJurusan_pemilih() != null) {
                        jurusan_pilihan = detailProdiModel.getJurusan_pemilih();
                    }

                    List<Integer> tahun = null;
                    if (detailProdiModel.getA_tahun() != null) {
                        tahun = detailProdiModel.getA_tahun();
                    }

                    List<Integer> pendaftar = null;
                    if (detailProdiModel.getB_pendaftar() != null) {
                        pendaftar = detailProdiModel.getB_pendaftar();
                    }

                    List<Integer> diterima = null;
                    if (detailProdiModel.getC_diterima() != null) {
                        diterima = detailProdiModel.getC_diterima();
                    }

                    List<String> sekolah = null;
                    if (detailProdiModel.getSekolah_jurusan() != null) {
                        sekolah = detailProdiModel.getSekolah_jurusan();
                    }

                    List<String> tahunJurusan = null;
                    if (detailProdiModel.getTahun_jurusan() != null) {
                        tahunJurusan = detailProdiModel.getTahun_jurusan();
                    }

                    List<String> diterimaJurusan = null;
                    if (detailProdiModel.getDiterima_jurusan() != null) {
                        diterimaJurusan = detailProdiModel.getDiterima_jurusan();
                    }
//                    Log.d("JURUSAN", String.valueOf(jurusan_pilihan));

                    postEvent(DetailProdiEvents.onGetDataSuccess, null, daerah, daya_tampung, kategori, prodi, link, jurusan_pilihan, tahun, pendaftar, diterima, sekolah, tahunJurusan, diterimaJurusan);

                }

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String errorMessage = e.getMessage();
                postEvent(DetailProdiEvents.onGetDataError, errorMessage);
            }
        });

    }

    public void postEvent(int type, String errorMessage, String daerah, String daya_tampung, String kategori, String prodi, String link, List<String> jurusan_pilihan, List<Integer> tahun, List<Integer> pendaftar, List<Integer> diterima, List<String> sekolah_jurusan, List<String> tahun_jurusan, List<String> diterima_jurusan) {
        DetailProdiEvents detailProdiEvents = new DetailProdiEvents();
        detailProdiEvents.setEventType(type);

        if (errorMessage != null) {
            detailProdiEvents.setError(errorMessage);
        }

        detailProdiEvents.setDataDaerah(daerah);
        detailProdiEvents.setDataDayaTampung(daya_tampung);
        detailProdiEvents.setDataKategori(kategori);
        detailProdiEvents.setDataProdi(prodi);
        detailProdiEvents.setLink(link);
        detailProdiEvents.setDataJurusanPemilih(jurusan_pilihan);
        detailProdiEvents.setDataTahun(tahun);
        detailProdiEvents.setDataPendaftar(pendaftar);
        detailProdiEvents.setDataDiterima(diterima);
        detailProdiEvents.setDataSekolahJurusan(sekolah_jurusan);
        detailProdiEvents.setDataTahunJurusan(tahun_jurusan);
        detailProdiEvents.setDataDiterimaJurusan(diterima_jurusan);

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(detailProdiEvents);
    }

    public void postEvent(int type, String errorMessage) {
        postEvent(type, errorMessage, null, null, null, null, null, null, null, null, null, null, null, null);
    }

}
