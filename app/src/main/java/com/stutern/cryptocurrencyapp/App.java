package com.stutern.cryptocurrencyapp;

import android.app.Application;

import androidx.room.Room;

import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CryptoCurrencyDao;
import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CryptoCurrencyDatabase;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CryptoCurrencyDatabase.DATABASE_NAME;

public class App extends Application {

    //private static final String BASE_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=235f3fb1-0370-40ff-8ea5-b2fb6501859e";

    private static final String BASE_URL = "https://pro-api.coinmarketcap.com/v1/";
    private CryptoCurrencyDao dao;
    private CoinDataInterface api;

    public CryptoCurrencyDao getDao() {
        if (dao == null) {
            CryptoCurrencyDatabase database =
                    Room.databaseBuilder(this, CryptoCurrencyDatabase.class, DATABASE_NAME).build();
            dao = database.getCryptoCurrencyDao();
        }
        return dao;
    }

    public CoinDataInterface getApi() {
        if (api == null)
            api = getRetrofit().create(CoinDataInterface.class);
        return api;
    }

    private Retrofit getRetrofit() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

