package com.stutern.cryptocurrencyapp.mvp;

import android.app.Application;

import com.stutern.cryptocurrencyapp.App;
import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CoinDataEntity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CoinListPresenter implements CoinListContract.Presenter {
    private CoinListContract.View mView;
    private CryptoCurrencyRepo mCryptoCurrencyRepo;
    private Disposable mDisposable;

    public CoinListPresenter(Application app) {
        mCryptoCurrencyRepo = new CryptoCurrencyRepo(((App)app).getApi(), ((App)app).getDao()); // ~ might be a better option here --find it
    }

    @Override
    public void getData() {
        mView.showLoading();
        mDisposable = mCryptoCurrencyRepo.getCryptoCurrencies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                }).subscribe(new Consumer<List<CoinDataEntity>>() {
                    @Override
                    public void accept(List<CoinDataEntity> coinDataEntities) throws Exception {
                        mView.showData(coinDataEntities);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable);
                    }
                });
    }

    @Override
    public void refreshingData() {
        mDisposable = mCryptoCurrencyRepo.getCryptoCurrenciesRemote().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CoinDataEntity>>() {
                    @Override
                    public void accept(List<CoinDataEntity> coinDataEntities) throws Exception {
                        mView.showRefreshedData(coinDataEntities);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable);
                    }
                });
    }

    @Override
    public void attachView(CoinListContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        if (mDisposable != null && !mDisposable.isDisposed())
            mDisposable.dispose();
    }
}