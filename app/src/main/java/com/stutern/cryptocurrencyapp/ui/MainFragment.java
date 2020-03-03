package com.stutern.cryptocurrencyapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CoinDataEntity;
import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CryptoCurrencyDatabase;
import com.stutern.cryptocurrencyapp.CoinDataInterface;
import com.stutern.cryptocurrencyapp.CryptoCurrencyViewModel;
import com.stutern.cryptocurrencyapp.R;
import com.stutern.cryptocurrencyapp.PagingLib.CryptoCurrencyAdapter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragment extends Fragment {
    private Retrofit mRetrofit;
    private OkHttpClient mClient;
    private Disposable mDisposable;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private ViewModel mViewModel;
    private LiveData<PagedList<CoinDataEntity>> mLivePagedList;
    private CryptoCurrencyAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefresh;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        initRetrofit();
        makeNetworkRequest();
    }

    private void init(View view) {
        mViewModel = ViewModelProviders.of(getActivity()).get(CryptoCurrencyViewModel.class);
        mLivePagedList = new MutableLiveData<>();
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mAdapter = new CryptoCurrencyAdapter(getActivity().getLayoutInflater());
        mSwipeRefresh = view.findViewById(R.id.swipe_refresh);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makeNetworkRequest();
                mSwipeRefresh.setRefreshing(false);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mProgressBar = view.findViewById(R.id.progress_circular);
    }

    private void initRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.coinmarketcap.com/v1/")
                .client(mClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    private void makeNetworkRequest() {
        CoinDataInterface coinDataInterface = mRetrofit.create(CoinDataInterface.class);
        mDisposable = coinDataInterface.getCoinData("50")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CoinDataEntity>>() {
                    @Override
                    public void accept(List<CoinDataEntity> coinData) throws Exception {
                        writeToDatabase(coinData);
                        ((CryptoCurrencyViewModel) mViewModel).getLivePagedList().observe(getActivity(), new Observer<PagedList<CoinDataEntity>>() {
                            @Override
                            public void onChanged(PagedList<CoinDataEntity> coinDataEntities) {
                                if (mAdapter != null)
                                    mProgressBar.setVisibility(View.GONE);
                                    mAdapter.submitList(coinDataEntities);
                            }
                        });
                        }

                    private void writeToDatabase(final List<CoinDataEntity> coinDataEntities) {
                        Thread writeToDatabaseThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //Opening app in rapid succession causes crash --fix it!
                                if (coinDataEntities.size() != 0) {

                                    int updated = CryptoCurrencyDatabase.getDatabase(getActivity().getApplicationContext())
                                    .getCryptoCurrencyDao().updateCryptoEntities(coinDataEntities);
                                    if (updated != 50) {
                                        Log.e(CryptoCurrencyDatabase.class.getSimpleName(), "The database operation returned " +
                                                "without updating all the coinDataEntities");
                                    }

                                }
                                else {
                                    CryptoCurrencyDatabase.getDatabase(getActivity().getApplicationContext())
                                            .getCryptoCurrencyDao().insertCryptoCurrencies(coinDataEntities);
                                }
                            }
                        });
                        writeToDatabaseThread.start();
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ((CryptoCurrencyViewModel) mViewModel).getLivePagedList().observe(getActivity(), new Observer<PagedList<CoinDataEntity>>() {
                            @Override
                            public void onChanged(PagedList<CoinDataEntity> coinDataEntities) {
                                if (mAdapter != null)
                                    mAdapter.submitList(coinDataEntities);
                            }
                        });
                        mProgressBar.setVisibility(View.GONE);
                        Snackbar sb = Snackbar.make(mRecyclerView, "Failed to load data", Snackbar.LENGTH_INDEFINITE)
                                .setAction("RETRY", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        makeNetworkRequest();
                                    }
                                });
                        sb.show();
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (!(mDisposable == null && mDisposable.isDisposed()))
            mDisposable.dispose();
    }
}
