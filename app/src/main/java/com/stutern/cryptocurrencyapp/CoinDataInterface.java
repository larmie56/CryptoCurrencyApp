package com.stutern.cryptocurrencyapp;

import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CoinDataEntity;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoinDataInterface {

    @GET("ticker/")
    Single<List<CoinDataEntity>> getCoinData(@Query("limit") String limit);
}
