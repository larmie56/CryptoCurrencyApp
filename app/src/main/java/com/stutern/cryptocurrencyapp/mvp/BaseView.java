package com.stutern.cryptocurrencyapp.mvp;

import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CoinDataEntity;

import java.util.List;

public interface BaseView {
    void showLoading();
    void hideLoading();
    void showError(Throwable throwable);
    void showData(List<CoinDataEntity> coinDataEntities);
}
