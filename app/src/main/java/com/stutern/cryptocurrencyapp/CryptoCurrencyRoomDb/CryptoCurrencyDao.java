package com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.stutern.cryptocurrencyapp.model.CoinData;

import java.util.List;

@Dao
public interface CryptoCurrencyDao {

    @Query("SELECT * FROM coin_data LIMIT 50")
    List<CoinDataEntity> getCryptoCurrencies();

    @Query("SELECT * FROM coin_data WHERE _symbol = :symbol LIMIT 1")
    CoinDataEntity getCryptoCurrency(String symbol);

    @Insert
    List<Long> insertCryptoCurrencies(List<CoinDataEntity> coinDataEntities);

    @Update
    int updateCryptoEntities(List<CoinDataEntity> coinDataEntities);

//    @Insert
//    long insertCryptoCurrency();

}
