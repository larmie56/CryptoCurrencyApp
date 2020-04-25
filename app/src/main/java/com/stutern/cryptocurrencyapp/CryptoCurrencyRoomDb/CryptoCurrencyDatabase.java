package com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CoinDataEntity.class}, version = 2, exportSchema = false)
public abstract class CryptoCurrencyDatabase extends RoomDatabase {

    public static String DATABASE_NAME = "CryptoCurrencyApp.db";

    public abstract CryptoCurrencyDao getCryptoCurrencyDao();

}