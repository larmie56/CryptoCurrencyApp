package com.stutern.cryptocurrencyapp.mapper;

import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CoinDataEntity;
import com.stutern.cryptocurrencyapp.model.CoinData;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    public static List<CoinDataEntity> modelToEntity(CoinData coinData) {
        List<CoinDataEntity> coinDataEntities = new ArrayList<>();

            List<CoinData.Data> dataList = coinData.getDataSet();

            for (CoinData.Data data : dataList)
            coinDataEntities.add(new CoinDataEntity(data));

        return coinDataEntities;
    }

  /*  public List<CoinData> entityToModel(List<CoinDataEntity> coinDataEntities) {
        List<CoinData> coinDataList = new ArrayList<>();

        for (CoinDataEntity coinDataEntity : coinDataEntities) {
            coinDataList.add(new CoinData(coinDataEntity.name, coinDataEntity._symbol, coinDataEntity.priceUsd,
                        coinDataEntity._24hVolumeUsd, coinDataEntity.marketCapUsd, coinDataEntity.availableSupply,
                        coinDataEntity.totalSupply, coinDataEntity.percentChange1h, coinDataEntity.percentChange24h));
            }

        return coinDataList;
    }*/
}
