package com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.stutern.cryptocurrencyapp.model.CoinData;

@Entity(tableName = "coin_data")
public class CoinDataEntity implements Parcelable {

    public static  String TAG = CoinDataEntity.class.getSimpleName();

    public static Parcelable.Creator<CoinDataEntity> CREATOR = new Parcelable.Creator<CoinDataEntity>() {
        @Override
        public CoinDataEntity createFromParcel(Parcel parcel) {
            return new CoinDataEntity(parcel);
        }

        @Override
        public CoinDataEntity[] newArray(int i) {
            return new CoinDataEntity[0];
        }
    };

    public CoinDataEntity(Parcel parcel) {
        this.name = parcel.readString();
        this._symbol = parcel.readString();
        this.priceUsd = parcel.readString();
        this._24hVolumeUsd = parcel.readString();
        this.marketCapUsd = parcel.readString();
        this.availableSupply = parcel.readString();
        this.totalSupply = parcel.readString();
        this.percentChange1h = parcel.readString();
        this.percentChange24h = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this._symbol);
        parcel.writeString(this.priceUsd);
        parcel.writeString(this._24hVolumeUsd);
        parcel.writeString(this.marketCapUsd);
        parcel.writeString(this.availableSupply);
        parcel.writeString(this.totalSupply);
        parcel.writeString(this.percentChange1h);
        parcel.writeString(this.percentChange24h);
    }

    @PrimaryKey
    @NonNull
    @SerializedName("symbol")
    @ColumnInfo(name = "_symbol")
    public String _symbol;

    @SerializedName("name")
    @ColumnInfo(name = "_name")
    public String name;

    @SerializedName("price_usd")
    @ColumnInfo(name = "_priceUsd")
    public String priceUsd;

    @SerializedName("24h_volume_usd")
    @ColumnInfo(name = "_24hVolumeUsd")
    public String _24hVolumeUsd;

    @SerializedName("market_cap_usd")
    @ColumnInfo(name = "_marketCapUsd")
    public String marketCapUsd;

    @SerializedName("available_supply")
    @ColumnInfo(name = "_availableSupply")
    public String availableSupply;

    @SerializedName("total_supply")
    @ColumnInfo(name = "_totalSupply")
    public String totalSupply;

    @SerializedName("percent_change_1h")
    @ColumnInfo(name = "_percentChange1h")
    public String percentChange1h;

    @SerializedName("percent_change_24h")
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("symbol")
    public String getSymbol() {
        return _symbol;
    }

    @JsonProperty("price_usd")
    public String getPriceUsd() {
        return priceUsd;
    }

    @JsonProperty("24h_volume_usd")
    public String get24hVolumeUsd() {
        return _24hVolumeUsd;
    }

    @JsonProperty("market_cap_usd")
    public String getMarketCapUsd() {
        return marketCapUsd;
    }

    @JsonProperty("available_supply")
    public String getAvailableSupply() {
        return availableSupply;
    }

    @JsonProperty("total_supply")
    public String getTotalSupply() {
        return totalSupply;
    }

    @JsonProperty("percent_change_1h")
    public String getPercentChange1h() {
        return percentChange1h;
    }

    @JsonProperty("percent_change_24h")
    public String getPercentChange24h() {
        return percentChange24h;
    }


    public static DiffUtil.ItemCallback<CoinDataEntity> DIFF_UTIL = new DiffUtil.ItemCallback<CoinDataEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull CoinDataEntity oldItem, @NonNull CoinDataEntity newItem) {
            return oldItem._symbol == newItem._symbol;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CoinDataEntity oldItem, @NonNull CoinDataEntity newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this)
            return true;

        CoinDataEntity coinDataEntity = (CoinDataEntity) obj;
        return coinDataEntity._symbol == this._symbol;
    }
}
