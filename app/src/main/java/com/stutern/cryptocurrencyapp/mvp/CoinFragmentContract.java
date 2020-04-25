package com.stutern.cryptocurrencyapp.mvp;

public interface CoinFragmentContract {

    interface View extends BaseView{
        void getCoinArgument();
    }

    interface Presenter extends BasePresenter<View> {

    }
}
