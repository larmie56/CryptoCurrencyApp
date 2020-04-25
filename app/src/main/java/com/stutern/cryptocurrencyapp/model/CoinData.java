package com.stutern.cryptocurrencyapp.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class CoinData {

    public static  String TAG = CoinData.class.getSimpleName();

    @SerializedName("data")
    private List<Data> dataSet;

    public List<Data> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<Data> dataSet) {
        this.dataSet = dataSet;
    }

    public class Data {

        @SerializedName("quote")
        private Quote quote;
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;
        @SerializedName("symbol")
        private String symbol;
        @SerializedName("total_supply")
        private String totalSupply;

        @JsonProperty("total_supply")
        public String getTotalSupply() {
            return totalSupply;
        }

        @JsonProperty("total_supply")
        public void setTotalSupply(String totalSupply) {
            this.totalSupply = totalSupply;
        }

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

        public Quote getQuote() {
            return quote;
        }

        public void setQuote(Quote quote) {
            this.quote = quote;
        }
    }

    public class Quote {
        @SerializedName("USD")
        USD usd;

        public USD getUsd() {
            return usd;
        }

        public void setUsd(USD usd) {
            this.usd = usd;
        }
    }

    public class USD {
        @SerializedName("24h_volume_usd")
        private String _24hVolumeUsd;
        @SerializedName("market_cap_usd")
        private String marketCapUsd;
        @SerializedName("percent_change_1h")
        private String percentChange1h;
        @SerializedName("percent_change_24h")
        private String percentChange24h;
        @SerializedName("percent_change_7d")
        private String percentChange7d;
        @SerializedName("price")
        private String priceUsd;

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

        @JsonProperty("price_usd")
        public String getPriceUsd() {
            return priceUsd;
        }

        @JsonProperty("price_usd")
        public void setPriceUsd(String priceUsd) {
            this.priceUsd = priceUsd;
        }
    }

}
