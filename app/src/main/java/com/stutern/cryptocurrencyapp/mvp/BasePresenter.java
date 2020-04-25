package com.stutern.cryptocurrencyapp.mvp;

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);
    void detachView();
}
