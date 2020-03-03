package com.stutern.cryptocurrencyapp;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CryptoCurrencyDao;
import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CryptoCurrencyDatabase;

import static com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CryptoCurrencyDatabase.DATABASE_NAME;

public class App extends Application {

    private static CryptoCurrencyDatabase sInstance;

  /*  public CryptoCurrencyDao getDao() {

        return getDatabase().getCryptoCurrencyDao();

    }*/




}

