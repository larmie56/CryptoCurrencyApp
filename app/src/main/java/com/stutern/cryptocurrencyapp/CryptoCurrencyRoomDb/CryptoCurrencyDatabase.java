package com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CoinDataEntity.class}, version = 2, exportSchema = false)
public abstract class CryptoCurrencyDatabase extends RoomDatabase {

    private static String DATABASE_NAME = "CryptoCurrencyApp.db";
    private static CryptoCurrencyDatabase sInstance;

    public abstract CryptoCurrencyDao getCryptoCurrencyDao();

    public static CryptoCurrencyDatabase getDatabase(Context context) {
        if (sInstance == null)
            sInstance = Room.databaseBuilder(context, CryptoCurrencyDatabase.class, DATABASE_NAME)
                    .build();
        return sInstance;
    }
}