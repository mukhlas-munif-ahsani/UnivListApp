package com.example.univlist.SoshumFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SoshumListViewModel extends ViewModel implements SoshumRepo.OnFirestoreTaskComplete {

    private MutableLiveData<List<SoshumListModel>> soshumListModelData = new MutableLiveData<>();

    public LiveData<List<SoshumListModel>> getSoshumListModelData(){
        return soshumListModelData;
    }

    private SoshumRepo soshumRepo = new SoshumRepo(this);

    public SoshumListViewModel(){
        soshumRepo.getSoshumData();
    }

    @Override
    public void soshumListDataAdded(List<SoshumListModel> soshumListModels) {
        soshumListModelData.setValue(soshumListModels);
    }

    @Override
    public void onError(Exception e) {

    }
}
