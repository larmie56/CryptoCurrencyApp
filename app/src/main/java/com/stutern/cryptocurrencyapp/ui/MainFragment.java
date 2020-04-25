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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CoinDataEntity;
import com.stutern.cryptocurrencyapp.R;
import com.stutern.cryptocurrencyapp.PagingLib.CryptoCurrencyAdapter;
import com.stutern.cryptocurrencyapp.mvp.CoinListContract;
import com.stutern.cryptocurrencyapp.mvp.CoinListPresenter;

import java.util.List;

public class MainFragment extends Fragment implements CoinListContract.View {
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    //private ViewModel mViewModel;
    //private LiveData<PagedList<CoinDataEntity>> mLivePagedList;
    private CryptoCurrencyAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefresh;
    private CoinListContract.Presenter mPresenter;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        mPresenter.getData();
    }

    private void init(View view) {
        mView = view;
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mSwipeRefresh = view.findViewById(R.id.swipe_refresh);
        mProgressBar = view.findViewById(R.id.progress_circular);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        mPresenter = new CoinListPresenter(getActivity().getApplication());

        mRecyclerView.setLayoutManager(layoutManager);

        mPresenter.attachView(this);

        refreshData();
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));


        //mViewModel = ViewModelProviders.of(getActivity()).get(CryptoCurrencyViewModel.class);
        //mLivePagedList = new MutableLiveData<>();
    }

    public void refreshData() {
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshingData();
                mSwipeRefresh.setRefreshing(false);
            }
        });
    }

    //https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=235f3fb1-0370-40ff-8ea5-b2fb6501859e

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(), "Loading....", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Loading done...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(Throwable throwable) {
        Snackbar.make(mView, throwable.toString(), Snackbar.LENGTH_INDEFINITE).show();
        Toast.makeText(getActivity(), "Error bro", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(List<CoinDataEntity> coinDataEntities) {
        mAdapter = new CryptoCurrencyAdapter(getActivity().getLayoutInflater(), coinDataEntities);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showRefreshedData(List<CoinDataEntity> coinDataEntities) {
        mAdapter.submitData(coinDataEntities);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }
}