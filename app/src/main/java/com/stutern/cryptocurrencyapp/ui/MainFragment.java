package com.stutern.cryptocurrencyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CoinDataEntity;
import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CryptoCurrencyDatabase;
import com.stutern.cryptocurrencyapp.mapper.ObjectMapper;
import com.stutern.cryptocurrencyapp.model.CoinData;
import com.stutern.cryptocurrencyapp.CoinDataInterface;
import com.stutern.cryptocurrencyapp.R;
import com.stutern.cryptocurrencyapp.adapter.CryptoCurrencyAdapter;

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
    private CryptoCurrencyDatabase mCryptoCurrencyDatabase;

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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mCryptoCurrencyDatabase = CryptoCurrencyDatabase.getDatabase(context.getApplicationContext());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mCryptoCurrencyDatabase.isOpen())
            mCryptoCurrencyDatabase.close();
    }

    private void init(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
        CryptoCurrencyAdapter adapter = new CryptoCurrencyAdapter(getActivity().getLayoutInflater());
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
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
                .subscribe(new Consumer<List<CoinData>>() {
                    @Override
                    public void accept(List<CoinData> coinData) throws Exception {
                        writeToDatabase(coinData);
                        if (mRecyclerView.getAdapter() != null) {
                            ((CryptoCurrencyAdapter) mRecyclerView.getAdapter()).setCoinDataList(coinData);
                            mProgressBar.setVisibility(View.GONE);
                        }

                    }

                    private void writeToDatabase(final List<CoinData> coinData) {
                        Thread writeToDatabaseThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                ObjectMapper mapper = new ObjectMapper();
                                List<CoinDataEntity> coinDataEntities = mapper.modelToEntity(coinData, getActivity());
                                //Opening app in rapid succession causes crash --fix it!
                                if (mCryptoCurrencyDatabase.getCryptoCurrencyDao().getCryptoCurrencies().size() != 0) {
                                    int updated = mCryptoCurrencyDatabase.getCryptoCurrencyDao()
                                            .updateCryptoEntities(coinDataEntities);
                                    if (updated != 50) {
                                        Log.e(CryptoCurrencyDatabase.class.getSimpleName(), "The database operation returned " +
                                                "without updating all the coinData");
                                    }

                                }
                                else {
                                    mCryptoCurrencyDatabase.getCryptoCurrencyDao().insertCryptoCurrencies(coinDataEntities);
                                }
                            }
                        });
                        writeToDatabaseThread.start();
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (mCryptoCurrencyDatabase != null) {
                            readFromDatabase();
                        }
                        //Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                        mProgressBar.setVisibility(View.GONE);
                        Snackbar.make(mRecyclerView, throwable.getMessage(), Snackbar.LENGTH_INDEFINITE).show();
                    }

                    private void readFromDatabase() {
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                List<CoinDataEntity> coinDataEntities = mCryptoCurrencyDatabase.getCryptoCurrencyDao()
                                        .getCryptoCurrencies();
                                if (coinDataEntities.size() != 0) {
                                    ObjectMapper mapper = new ObjectMapper();
                                    final List<CoinData> coinDataList = mapper.entityToModel(coinDataEntities);
                                    Handler handler = new Handler(Looper.getMainLooper());
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((CryptoCurrencyAdapter) mRecyclerView.getAdapter()).setCoinDataList(coinDataList);
                                        }
                                    });
                                }
                            }
                        });
                        thread.start();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDisposable.dispose();
    }
}
