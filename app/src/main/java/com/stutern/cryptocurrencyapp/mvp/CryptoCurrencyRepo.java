package com.stutern.cryptocurrencyapp.mvp;

import com.stutern.cryptocurrencyapp.CoinDataInterface;
import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CoinDataEntity;
import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CryptoCurrencyDao;
import com.stutern.cryptocurrencyapp.mapper.ObjectMapper;
import com.stutern.cryptocurrencyapp.model.CoinData;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

class CryptoCurrencyRepo {
    private RepoImpl mRepoImpl;
    private static final String API_KEY = "235f3fb1-0370-40ff-8ea5-b2fb6501859e";

    CryptoCurrencyRepo(CoinDataInterface api, CryptoCurrencyDao dao) {
        mRepoImpl = new RepoImpl(api, dao); // ~ there might be a better option here
    }

   Single<List<CoinDataEntity>> getCryptoCurrencies() {
        return mRepoImpl.getCryptoCurrenciesLocal()
                .onErrorResumeNext(mRepoImpl.getCryptoCurrenciesRemote(API_KEY).map(new Function<CoinData, List<CoinDataEntity>>() {
                    @Override
                    public List<CoinDataEntity> apply(CoinData coinData) throws Exception {
                        return ObjectMapper.modelToEntity(coinData);
                    }
                }))
                .doAfterSuccess(new Consumer<List<CoinDataEntity>>() {
                    @Override
                    public void accept(List<CoinDataEntity> coinDataEntities) throws Exception {
                        mRepoImpl.insertCryptoCurrencies(coinDataEntities);
                    }
                });
    }

    Single<List<CoinDataEntity>> getCryptoCurrenciesRemote() {
        return mRepoImpl.getCryptoCurrenciesRemote(API_KEY).map(new Function<CoinData, List<CoinDataEntity>>() {
            @Override
            public List<CoinDataEntity> apply(CoinData coinData) throws Exception {
                return ObjectMapper.modelToEntity(coinData);
            }
        }).onErrorResumeNext(mRepoImpl.getCryptoCurrenciesLocal())
                .doAfterSuccess(new Consumer<List<CoinDataEntity>>() {
                    @Override
                    public void accept(List<CoinDataEntity> coinDataEntities) throws Exception {
                        mRepoImpl.insertCryptoCurrencies(coinDataEntities);
                    }
                });
    }
}
