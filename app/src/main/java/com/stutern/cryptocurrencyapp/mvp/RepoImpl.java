package com.stutern.cryptocurrencyapp.mvp;

import com.stutern.cryptocurrencyapp.CoinDataInterface;
import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CoinDataEntity;
import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CryptoCurrencyDao;
import com.stutern.cryptocurrencyapp.model.CoinData;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

 class RepoImpl implements LocalDataSource, RemoteDataSource {
     private CoinDataInterface api;
     private CryptoCurrencyDao dao;

     RepoImpl(CoinDataInterface api, CryptoCurrencyDao dao) {
         this.api = api;
         this.dao = dao;
     }

    @Override
    public Single<List<CoinDataEntity>> getCryptoCurrenciesLocal() {
        return dao.getCryptoCurrencies();
    }

    @Override
    public List<Long> insertCryptoCurrencies(List<CoinDataEntity> coinDataEntities) {
        return dao.insertCryptoCurrencies(coinDataEntities);
    }

    @Override
    public int updateCryptoEntities(List<CoinDataEntity> coinDataEntities) {
        return dao.updateCryptoEntities(coinDataEntities);
    }

    @Override
    public Single<CoinData> getCryptoCurrenciesRemote(String apiKey) {
        return api.getCoinData(apiKey);
    }
}
