package com.stutern.cryptocurrencyapp;

import com.stutern.cryptocurrencyapp.model.CoinData;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoinDataInterface {

    @GET("cryptocurrency/listings/latest")
    Single<CoinData> getCoinData(@Query("CMC_PRO_API_KEY") String apiKe);
}