package com.stutern.cryptocurrencyapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CoinDataEntity;
import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CryptoCurrencyDao;
import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CryptoCurrencyDatabase;

public class CryptoCurrencyViewModel extends AndroidViewModel {

    private CryptoCurrencyDao mDao;
    private LiveData<PagedList<CoinDataEntity>> mLivePagedList;


    public CryptoCurrencyViewModel(@NonNull Application application) {
        super(application);
        //mDao = CryptoCurrencyDatabase.getDatabase(this.getApplication()).getCryptoCurrencyDao();

        PagedList.Config config = new PagedList.Config.Builder().setPageSize(25).setEnablePlaceholders(false).build();

        //mLivePagedList = new LivePagedListBuilder<>(mDao.getCryptoCurrencies(), config).build();
    }


    public LiveData<PagedList<CoinDataEntity>> getLivePagedList() {
        return mLivePagedList;
    }
}
