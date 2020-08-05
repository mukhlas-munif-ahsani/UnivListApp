package com.example.univlist.PendidikanFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class PendidikanViewModel extends ViewModel implements PendidikanRepository.OnFirestoreTaskComplete {

    private MutableLiveData<List<PendidikanListModel>> pendidikanListModelData = new MutableLiveData<>();

    public LiveData<List<PendidikanListModel>> getPendidikanListModelData() {
        return pendidikanListModelData;
    }

    private PendidikanRepository pendidikanRepository = new PendidikanRepository(this);

    public PendidikanViewModel(){
        pendidikanRepository.getPendidikanData();
    }

    @Override
    public void pendidikanListDataAdded(List<PendidikanListModel> pendidikanListModelList) {
        pendidikanListModelData.setValue(pendidikanListModelList);
    }

    @Override
    public void onError(Exception e) {

    }
}
