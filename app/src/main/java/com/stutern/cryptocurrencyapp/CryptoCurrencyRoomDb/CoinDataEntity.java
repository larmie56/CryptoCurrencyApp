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

    @ColumnInfo(name = "_totalSupply")
    public String totalSupply;

    @ColumnInfo(name = "_availableSupply")
    @Nullable
    public String availableSupply;

    @ColumnInfo(name = "_percentChange1h")
    public String percentChange1h;

    @ColumnInfo(name = "_percentChange24h")
    public String percentChange24h;

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
        this.totalSupply = parcel.readString();
        this.percentChange1h = parcel.readString();
        this.percentChange24h = parcel.readString();
    }

    public CoinDataEntity(CoinData.Data data) {
        this._symbol = data.getSymbol();
        this.name = data.getName();
        this.priceUsd = data.getQuote().getUsd().getPriceUsd();
        this._24hVolumeUsd = data.getQuote().getUsd().get24hVolumeUsd();
        this.marketCapUsd = data.getQuote().getUsd().getMarketCapUsd();
        this.totalSupply = data.getTotalSupply();
        this.percentChange1h = data.getQuote().getUsd().getPercentChange1h();
        this.percentChange24h = data.getQuote().getUsd().getPercentChange24h();
    }

    public CoinDataEntity() {}

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
        parcel.writeString(this.totalSupply);
        parcel.writeString(this.percentChange1h);
        parcel.writeString(this.percentChange24h);
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
