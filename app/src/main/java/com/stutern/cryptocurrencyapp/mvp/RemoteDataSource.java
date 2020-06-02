package com.stutern.cryptocurrencyapp.mvp;

import com.stutern.cryptocurrencyapp.model.CoinData;

import io.reactivex.Single;

public interface RemoteDataSource {

    Single<CoinData> getCryptoCurrenciesRemote(String apiKey);
}
