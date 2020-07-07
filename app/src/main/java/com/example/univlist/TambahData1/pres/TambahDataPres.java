package com.example.univlist.TambahData1.pres;

import com.example.univlist.EventBus.EventBus;
import com.example.univlist.EventBus.GreenRobotEventBus;
import com.example.univlist.TambahData1.TambahDataEvent;
import com.example.univlist.TambahData1.repo.TambahDataRepo;
import com.example.univlist.TambahData1.repo.TambahDataRepoInt;
import com.example.univlist.TambahData1.view.TambahDataViewInt;

import org.greenrobot.eventbus.Subscribe;

import static com.example.univlist.TambahData1.TambahDataEvent.onGetError;
import static com.example.univlist.TambahData1.TambahDataEvent.onGetSuccess;

public class TambahDataPres implements TambahDataPresInt {

    private TambahDataViewInt tambahDataViewInt;
    private TambahDataRepoInt tambahDataRepoInt;
    private EventBus eventBus;

    public TambahDataPres(TambahDataViewInt tambahDataViewInt) {
        this.tambahDataViewInt = tambahDataViewInt;
        tambahDataRepoInt = new TambahDataRepo();
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Subscribe
    public void onEventMainThread(TambahDataEvent event){
        switch (event.getEventType()){
            case onGetSuccess:
                onGetDataSuccess(event.getUniv(), event.getKategori(), event.getProdi());
                break;
            case onGetError:
                onGetDataError(event.getErrorMessage());
                break;
        }
    }

    private void onGetDataError(String txt) {
        tambahDataViewInt.showMessage(txt);
    }

    private void onGetDataSuccess(String univ, String kategori, String prodi) {
        tambahDataViewInt.navigateToTambah2(univ, kategori, prodi);
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        tambahDataViewInt = null;
        eventBus.unregister(this);
    }

    public void validateData(String univ, String prodi, String kategori, String daerah, String daya, String link){
        tambahDataViewInt.shwoProgress();
        tambahDataViewInt.setInputs(false);

        tambahDataRepoInt.input(univ, prodi, kategori, daerah, daya, link);
    }
}
