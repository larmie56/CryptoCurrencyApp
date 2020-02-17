package com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "coin_data")
public class CoinDataEntity {

   /* @NonNull
    public int primaryKey;*/

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "_symbol")
    public String _symbol;

    @ColumnInfo(name = "_name")
    public String name;

    @ColumnInfo(name = "_priceUsd")
    public String priceUsd;

    @ColumnInfo(name = "_24hVolumeUsd")
    public String _24hVolumeUsd;

    @ColumnInfo(name = "_marketCapUsd")
    public String marketCapUsd;

    @ColumnInfo(name = "_availableSupply")
    public String availableSupply;

    @ColumnInfo(name = "_totalSupply")
    public String totalSupply;

    @ColumnInfo(name = "_percentChange1h")
    public String percentChange1h;

    @ColumnInfo(name = "_percentChange24h")
    public String percentChange24h;

    public CoinDataEntity(/*int primaryKey, */String _symbol, String name, String priceUsd, String _24hVolumeUsd, String marketCapUsd,
                          String availableSupply, String totalSupply, String percentChange1h, String percentChange24h) {
        this._symbol = _symbol;
        this.name = name;
        this.priceUsd = priceUsd;
        this._24hVolumeUsd = _24hVolumeUsd;
        this.marketCapUsd = marketCapUsd;
        this.availableSupply = availableSupply;
        this.totalSupply = totalSupply;
        this.percentChange1h = percentChange1h;
        this.percentChange24h = percentChange24h;
    }
}
