package com.stutern.cryptocurrencyapp.mvp;

import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CoinDataEntity;

import java.util.List;

public interface CoinListContract {

    interface View extends BaseView {
        void showRefreshedData(List<CoinDataEntity> coinDataEntities);
    }

    interface Presenter extends BasePresenter<View>{
        void getData();
        void refreshingData();
    }
}

