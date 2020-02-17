package com.stutern.cryptocurrencyapp;

import com.stutern.cryptocurrencyapp.model.CoinData;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoinDataInterface {

    @GET("ticker/")
    Single<List<CoinData>> getCoinData(@Query("limit") String limit);
}
