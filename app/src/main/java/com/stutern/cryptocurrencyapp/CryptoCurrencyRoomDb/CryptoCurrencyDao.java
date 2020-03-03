package com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb;

import android.database.Observable;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.stutern.cryptocurrencyapp.model.CoinData;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CryptoCurrencyDao {

    @Query("SELECT * FROM coin_data LIMIT 50")
    DataSource.Factory<Integer, CoinDataEntity> getCryptoCurrencies();

    @Query("SELECT * FROM coin_data WHERE _symbol = :symbol LIMIT 1")
    Single<CoinDataEntity> getCryptoCurrency(String symbol);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertCryptoCurrencies(List<CoinDataEntity> coinDataEntities);

    @Update
    int updateCryptoEntities(List<CoinDataEntity> coinDataEntities);

//    @Insert
//    long insertCryptoCurrency();

}
