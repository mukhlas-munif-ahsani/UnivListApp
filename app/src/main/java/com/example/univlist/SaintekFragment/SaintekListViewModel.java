package com.example.univlist.SaintekFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SaintekListViewModel extends ViewModel implements SaintekRepository.OnFirestoreTaskComplete {

    private MutableLiveData<List<SaintekListModel>> saintekListModelData = new MutableLiveData<>();

    public LiveData<List<SaintekListModel>> getSaintekListModelData() {
        return saintekListModelData;
    }

    private SaintekRepository saintekRepository = new SaintekRepository(this);

    public SaintekListViewModel(){
        saintekRepository.getSaintekData();
    }

    @Override
    public void sanitekListDataAdded(List<SaintekListModel> saintekListModelList) {
        saintekListModelData.setValue(saintekListModelList);
    }

    @Override
    public void onError(Exception e) {

    }
}
