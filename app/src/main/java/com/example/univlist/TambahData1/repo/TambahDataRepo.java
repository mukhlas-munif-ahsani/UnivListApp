package com.example.univlist.TambahData1.repo;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.univlist.EventBus.EventBus;
import com.example.univlist.EventBus.GreenRobotEventBus;
import com.example.univlist.TambahData1.TambahDataEvent;
import com.example.univlist.TambahData2.TambahData2Model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TambahDataRepo implements TambahDataRepoInt {

    private FirebaseFirestore firestore;

    public TambahDataRepo() {
        firestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void input(final String univ, final String prodi, final String kategori, String daerah, String daya, String link) {

        Log.d("univtestinput", "bisa");

        List<String> prodiList = Arrays.asList(prodi);
        List<String> univList = Arrays.asList(univ);

        final Map<String, Object> userMap1 = new HashMap<>();
        userMap1.put("daerah", daerah);
        userMap1.put("prodi", prodi);
        userMap1.put("kategori", kategori);
        userMap1.put("daya_tampung", daya);
        userMap1.put("link", link);

        UserMap2 userMap2 = new UserMap2(daerah, prodiList);
        UserMap3 userMap3 = new UserMap3(kategori, univList);

        firestore.collection(kategori).document(prodi).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();

                    if (documentSnapshot.get("univ") != null) {

                        Log.d("univtest", "exist");
                        updateUniv(univ, prodi, kategori, daerah, daya, link);

                    } else {
                        Log.d("univtest", "not exist");
                        setUniv(univ, prodi, kategori, daerah, daya, link);

                    }

                }
            }
        });

//        firestore.collection(kategori).document(prodi)
//                .update("daerah", daerah,
//                        "prodi", FieldValue.arrayUnion(prodi)).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//
//                    firestore.collection("UNIVERSITAS").document(univ)
//                            .update("daerah", daerah,
//                                    "prodi", FieldValue.arrayUnion(prodi)).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//
//                                firestore.collection("UNIVERSITAS").document(univ)
//                                        .collection(kategori).document(prodi)
//                                        .set(userMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if (task.isSuccessful()) {
//                                            postEvent(TambahDataEvent.onGetSuccess, univ, kategori, prodi);
//                                        } else {
//                                            String error = task.getException().getMessage();
//                                            postEvent(TambahDataEvent.onGetError, error);
//                                        }
//                                    }
//                                });
//
//                            } else {
//                                String error = task.getException().getMessage();
//                                postEvent(TambahDataEvent.onGetError, error);
//                            }
//                        }
//                    });
//
//                } else {
//                    String error = task.getException().getMessage();
//                    postEvent(TambahDataEvent.onGetError, error);
//                }
//            }
//        });
    }

    public void updateProdi(final String univ, final String prodi, final String kategori, String daerah, String daya, String link) {
        List<String> prodiList = Arrays.asList(prodi);
        List<String> univList = Arrays.asList(univ);

        final Map<String, Object> userMap1 = new HashMap<>();
        userMap1.put("daerah", daerah);
        userMap1.put("prodi", prodi);
        userMap1.put("kategori", kategori);
        userMap1.put("daya_tampung", daya);
        userMap1.put("link", link);

        UserMap2 userMap2 = new UserMap2(daerah, prodiList);
        UserMap3 userMap3 = new UserMap3(kategori, univList);

        firestore.collection("UNIVERSITAS").document(univ)
                .update("prodi", FieldValue.arrayUnion(prodi)).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    firestore.collection("UNIVERSITAS").document(univ).collection(kategori).document(prodi).get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot documentSnapshot = task.getResult();

                                        if (documentSnapshot.get("daerah") != null) {

                                            Log.d("univtest", "exist");
                                            firestore.collection("UNIVERSITAS").document(univ)
                                                    .collection(kategori).document(prodi)
                                                    .update(userMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        postEvent(TambahDataEvent.onGetSuccess, univ, kategori, prodi);
                                                    } else {
                                                        String error = task.getException().getMessage();
                                                        postEvent(TambahDataEvent.onGetError, error);
                                                    }
                                                }
                                            });

                                        } else {
                                            Log.d("univtest", "not exist");
                                            firestore.collection("UNIVERSITAS").document(univ)
                                                    .collection(kategori).document(prodi)
                                                    .set(userMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        postEvent(TambahDataEvent.onGetSuccess, univ, kategori, prodi);
                                                    } else {
                                                        String error = task.getException().getMessage();
                                                        postEvent(TambahDataEvent.onGetError, error);
                                                    }
                                                }
                                            });

                                        }

                                    }
                                }
                            });

                } else {
                    String error = task.getException().getMessage();
                    postEvent(TambahDataEvent.onGetError, error);
                }
            }
        });

    }

    public void updateUniv(final String univ, final String prodi, final String kategori, String daerah, String daya, String link) {
        List<String> prodiList = Arrays.asList(prodi);
        List<String> univList = Arrays.asList(univ);

        final Map<String, Object> userMap1 = new HashMap<>();
        userMap1.put("daerah", daerah);
        userMap1.put("prodi", prodi);
        userMap1.put("kategori", kategori);
        userMap1.put("daya_tampung", daya);
        userMap1.put("link", link);

        UserMap2 userMap2 = new UserMap2(daerah, prodiList);
        UserMap3 userMap3 = new UserMap3(kategori, univList);
        firestore.collection(kategori).document(prodi)
                .update("univ", FieldValue.arrayUnion(univ)).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    firestore.collection("UNIVERSITAS").document(univ).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot documentSnapshot1 = task.getResult();

                                if (documentSnapshot1.get("prodi") != null) {

                                    updateProdi(univ, prodi, kategori, daerah, daya, link);

                                } else {

                                    setProdi(univ, prodi, kategori, daerah, daya, link);

                                }

                            }
                        }
                    });

//                    firestore.collection("UNIVERSITAS").document(univ)
//                            .update("daerah", daerah,
//                                    "prodi", FieldValue.arrayUnion(prodi)).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//
//                                firestore.collection("UNIVERSITAS").document(univ)
//                                        .collection(kategori).document(prodi)
//                                        .set(userMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if (task.isSuccessful()) {
//                                            postEvent(TambahDataEvent.onGetSuccess, univ, kategori, prodi);
//                                        } else {
//                                            String error = task.getException().getMessage();
//                                            postEvent(TambahDataEvent.onGetError, error);
//                                        }
//                                    }
//                                });
//
//                            } else {
//                                String error = task.getException().getMessage();
//                                postEvent(TambahDataEvent.onGetError, error);
//                            }
//                        }
//                    });

                } else {
                    String error = task.getException().getMessage();
                    postEvent(TambahDataEvent.onGetError, error);
                }
            }
        });
    }

    public void setProdi(final String univ, final String prodi, final String kategori, String daerah, String daya, String link) {
        List<String> prodiList = Arrays.asList(prodi);
        List<String> univList = Arrays.asList(univ);

        final Map<String, Object> userMap1 = new HashMap<>();
        userMap1.put("daerah", daerah);
        userMap1.put("prodi", prodi);
        userMap1.put("kategori", kategori);
        userMap1.put("daya_tampung", daya);
        userMap1.put("link", link);

        UserMap2 userMap2 = new UserMap2(daerah, prodiList);
        UserMap3 userMap3 = new UserMap3(kategori, univList);

        firestore.collection("UNIVERSITAS").document(univ)
                .set(userMap2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    firestore.collection("UNIVERSITAS").document(univ).collection(kategori).document(prodi).get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot documentSnapshot = task.getResult();

                                        if (documentSnapshot.get("daerah") != null) {

                                            Log.d("univtest", "exist");
                                            firestore.collection("UNIVERSITAS").document(univ)
                                                    .collection(kategori).document(prodi)
                                                    .update(userMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        postEvent(TambahDataEvent.onGetSuccess, univ, kategori, prodi);
                                                    } else {
                                                        String error = task.getException().getMessage();
                                                        postEvent(TambahDataEvent.onGetError, error);
                                                    }
                                                }
                                            });

                                        } else {
                                            Log.d("univtest", "not exist");
                                            firestore.collection("UNIVERSITAS").document(univ)
                                                    .collection(kategori).document(prodi)
                                                    .set(userMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        postEvent(TambahDataEvent.onGetSuccess, univ, kategori, prodi);
                                                    } else {
                                                        String error = task.getException().getMessage();
                                                        postEvent(TambahDataEvent.onGetError, error);
                                                    }
                                                }
                                            });

                                        }

                                    }
                                }
                            });

                } else {
                    String error = task.getException().getMessage();
                    postEvent(TambahDataEvent.onGetError, error);
                }
            }
        });

    }

    public void setUniv(final String univ, final String prodi, final String kategori, String daerah, String daya, String link) {
        List<String> prodiList = Arrays.asList(prodi);
        List<String> univList = Arrays.asList(univ);

        final Map<String, Object> userMap1 = new HashMap<>();
        userMap1.put("daerah", daerah);
        userMap1.put("prodi", prodi);
        userMap1.put("kategori", kategori);
        userMap1.put("daya_tampung", daya);
        userMap1.put("link", link);

        UserMap2 userMap2 = new UserMap2(daerah, prodiList);
        UserMap3 userMap3 = new UserMap3(kategori, univList);

        firestore.collection(kategori).document(prodi)
                .set(userMap3).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    firestore.collection("UNIVERSITAS").document(univ).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot documentSnapshot1 = task.getResult();

                                if (documentSnapshot1.get("prodi") != null) {

                                    updateProdi(univ, prodi, kategori, daerah, daya, link);

                                } else {

                                    setProdi(univ, prodi, kategori, daerah, daya, link);

                                }

                            }
                        }
                    });

                } else {
                    String error = task.getException().getMessage();
                    postEvent(TambahDataEvent.onGetError, error);
                }
            }
        });
    }

    public void postEvent(int type, String errorMessage, String univ, String kategori, String prodi) {
        TambahDataEvent event = new TambahDataEvent();

        event.setEventType(type);

        if (errorMessage != null) {
            event.setErrorMessage(errorMessage);
        }
        event.setUniv(univ);
        event.setKategori(kategori);
        event.setProdi(prodi);

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);
    }

    public void postEvent(int type, String univ, String kategori, String prodi) {
        postEvent(type, null, univ, kategori, prodi);
    }

    public void postEvent(int type, String error) {
        postEvent(type, error, null, null, null);
    }
}
