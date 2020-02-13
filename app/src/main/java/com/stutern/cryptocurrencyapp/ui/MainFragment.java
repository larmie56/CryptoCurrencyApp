package com.stutern.cryptocurrencyapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stutern.cryptocurrencyapp.CoinData;
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
        mRecyclerView = view.findViewById(R.id.recyclerView);
        CryptoCurrencyAdapter adapter = new CryptoCurrencyAdapter(getActivity().getLayoutInflater());
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

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
                        if (mRecyclerView.getAdapter() != null) {
                            ((CryptoCurrencyAdapter) mRecyclerView.getAdapter()).setCoinDataList(coinData);
                            mProgressBar.setVisibility(View.GONE);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDisposable.dispose();
    }
}
