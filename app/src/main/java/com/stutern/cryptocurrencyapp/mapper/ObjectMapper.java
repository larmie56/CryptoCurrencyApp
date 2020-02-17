package com.stutern.cryptocurrencyapp.mapper;

import android.content.Context;

import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CoinDataEntity;
import com.stutern.cryptocurrencyapp.model.CoinData;
import com.stutern.cryptocurrencyapp.utilities.PrimaryKeyGenerator;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    public List<CoinDataEntity> modelToEntity(List<CoinData> coinDataList, Context context) {
        List<CoinDataEntity> coinDataEntities = new ArrayList<>();
        PrimaryKeyGenerator primaryKeyGenerator = new PrimaryKeyGenerator();

        for (CoinData coindata : coinDataList) {
            int primaryKey = primaryKeyGenerator.getPrimaryKey(context);
            coinDataEntities.add(new CoinDataEntity(coindata.getSymbol(), coindata.getName(),
                    coindata.getPriceUsd(), coindata.get24hVolumeUsd(), coindata.getMarketCapUsd(),
                    coindata.getAvailableSupply(), coindata.getTotalSupply(),
                    coindata.getPercentChange1h(), coindata.getPercentChange24h()));
        }

        return coinDataEntities;
    }

    public List<CoinData> entityToModel(List<CoinDataEntity> coinDataEntities) {
        List<CoinData> coinDataList = new ArrayList<>();

        for (CoinDataEntity coinDataEntity : coinDataEntities) {
            coinDataList.add(new CoinData(coinDataEntity.name, coinDataEntity._symbol, coinDataEntity.priceUsd,
                        coinDataEntity._24hVolumeUsd, coinDataEntity.marketCapUsd, coinDataEntity.availableSupply,
                        coinDataEntity.totalSupply, coinDataEntity.percentChange1h, coinDataEntity.percentChange24h));
            }

        return coinDataList;
    }
}
