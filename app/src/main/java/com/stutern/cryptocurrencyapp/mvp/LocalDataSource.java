package com.stutern.cryptocurrencyapp.mvp;

import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CoinDataEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

interface LocalDataSource {

    Single<List<CoinDataEntity>> getCryptoCurrenciesLocal();
    List<Long> insertCryptoCurrencies(List<CoinDataEntity> coinDataEntities);
    int updateCryptoEntities(List<CoinDataEntity> coinDataEntities);
}
