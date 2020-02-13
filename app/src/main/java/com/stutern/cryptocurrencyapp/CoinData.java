package com.stutern.cryptocurrencyapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class CoinData implements Parcelable {

    public static  String TAG = CoinData.class.getSimpleName();

    public static Parcelable.Creator<CoinData> CREATOR = new Parcelable.Creator<CoinData>() {
        @Override
        public CoinData createFromParcel(Parcel parcel) {
            return new CoinData(parcel);
        }

        @Override
        public CoinData[] newArray(int i) {
            return new CoinData[0];
        }
    };

    public CoinData(Parcel parcel) {
        this.id = parcel.readString();
        this.name = parcel.readString();
        this.symbol = parcel.readString();
        this.rank = parcel.readString();
        this.priceUsd = parcel.readString();
        this.priceBtc = parcel.readString();
        this._24hVolumeUsd = parcel.readString();
        this.marketCapUsd = parcel.readString();
        this.availableSupply = parcel.readString();
        this.totalSupply = parcel.readString();
        this.percentChange1h = parcel.readString();
        this.percentChange24h = parcel.readString();
        this.percentChange7d = parcel.readString();
        this.lastUpdated = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.symbol);
        parcel.writeString(this.rank);
        parcel.writeString(this.priceUsd);
        parcel.writeString(this.priceBtc);
        parcel.writeString(this._24hVolumeUsd);
        parcel.writeString(this.marketCapUsd);
        parcel.writeString(this.availableSupply);
        parcel.writeString(this.totalSupply);
        parcel.writeString(this.percentChange1h);
        parcel.writeString(this.percentChange24h);
        parcel.writeString(this.percentChange7d);
        parcel.writeString(this.lastUpdated);
    }


        @SerializedName("id")
        private String id;
        @SerializedName("name")
        private String name;
        @SerializedName("symbol")
        private String symbol;
        @SerializedName("rank")
        private String rank;
        @SerializedName("price_usd")
        private String priceUsd;
        @SerializedName("price_btc")
        private String priceBtc;
        @SerializedName("24h_volume_usd")
        private String _24hVolumeUsd;
        @SerializedName("market_cap_usd")
        private String marketCapUsd;
        @SerializedName("available_supply")
        private String availableSupply;
        @SerializedName("total_supply")
        private String totalSupply;
        @SerializedName("percent_change_1h")
        private String percentChange1h;
        @SerializedName("percent_change_24h")
        private String percentChange24h;
        @SerializedName("percent_change_7d")
        private String percentChange7d;
        @SerializedName("last_updated")
        private String lastUpdated;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("symbol")
        public String getSymbol() {
            return symbol;
        }

        @JsonProperty("symbol")
        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        @JsonProperty("rank")
        public String getRank() {
            return rank;
        }

        @JsonProperty("rank")
        public void setRank(String rank) {
            this.rank = rank;
        }

        @JsonProperty("price_usd")
        public String getPriceUsd() {
            return priceUsd;
        }

        @JsonProperty("price_usd")
        public void setPriceUsd(String priceUsd) {
            this.priceUsd = priceUsd;
        }

        @JsonProperty("price_btc")
        public String getPriceBtc() {
            return priceBtc;
        }

        @JsonProperty("price_btc")
        public void setPriceBtc(String priceBtc) {
            this.priceBtc = priceBtc;
        }

        @JsonProperty("24h_volume_usd")
        public String get24hVolumeUsd() {
            return _24hVolumeUsd;
        }

        @JsonProperty("24h_volume_usd")
        public void set24hVolumeUsd(String _24hVolumeUsd) {
            this._24hVolumeUsd = _24hVolumeUsd;
        }

        @JsonProperty("market_cap_usd")
        public String getMarketCapUsd() {
            return marketCapUsd;
        }

        @JsonProperty("market_cap_usd")
        public void setMarketCapUsd(String marketCapUsd) {
            this.marketCapUsd = marketCapUsd;
        }

        @JsonProperty("available_supply")
        public String getAvailableSupply() {
            return availableSupply;
        }

        @JsonProperty("available_supply")
        public void setAvailableSupply(String availableSupply) {
            this.availableSupply = availableSupply;
        }

        @JsonProperty("total_supply")
        public String getTotalSupply() {
            return totalSupply;
        }

        @JsonProperty("total_supply")
        public void setTotalSupply(String totalSupply) {
            this.totalSupply = totalSupply;
        }

        @JsonProperty("percent_change_1h")
        public String getPercentChange1h() {
            return percentChange1h;
        }

        @JsonProperty("percent_change_1h")
        public void setPercentChange1h(String percentChange1h) {
            this.percentChange1h = percentChange1h;
        }

        @JsonProperty("percent_change_24h")
        public String getPercentChange24h() {
            return percentChange24h;
        }

        @JsonProperty("percent_change_24h")
        public void setPercentChange24h(String percentChange24h) {
            this.percentChange24h = percentChange24h;
        }

        @JsonProperty("percent_change_7d")
        public String getPercentChange7d() {
            return percentChange7d;
        }

        @JsonProperty("percent_change_7d")
        public void setPercentChange7d(String percentChange7d) {
            this.percentChange7d = percentChange7d;
        }

        @JsonProperty("last_updated")
        public String getLastUpdated() {
            return lastUpdated;
        }

        @JsonProperty("last_updated")
        public void setLastUpdated(String lastUpdated) {
            this.lastUpdated = lastUpdated;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

}
