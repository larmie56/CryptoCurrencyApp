package com.stutern.cryptocurrencyapp.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stutern.cryptocurrencyapp.CoinData;
import com.stutern.cryptocurrencyapp.IMainActivity;
import com.stutern.cryptocurrencyapp.R;
import com.stutern.cryptocurrencyapp.ui.CoinDetailsDialogFragment;
import com.stutern.cryptocurrencyapp.utilities.DisplayUtil;
import com.stutern.cryptocurrencyapp.utilities.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class CryptoCurrencyAdapter extends RecyclerView.Adapter<CryptoCurrencyAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<CoinData> mCoinDataList;
    private CoinDetailsDialogFragment mCoinDetailsDialogFragment = new CoinDetailsDialogFragment();

    public CryptoCurrencyAdapter(LayoutInflater inflater) {
        mInflater = inflater;
        mCoinDataList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view, mCoinDetailsDialogFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final CoinData coinData = mCoinDataList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(CoinData.TAG, coinData);
                mCoinDetailsDialogFragment.setArguments(bundle);
                ((IMainActivity) holder.itemView.getContext()).onItemClick(mCoinDetailsDialogFragment);
            }
        });
        holder.bind(coinData);

    }

    @Override
    public int getItemCount() {
        return mCoinDataList == null ? 0 : mCoinDataList.size();
    }

    public void setCoinDataList(List<CoinData> coinDataList) {
        mCoinDataList.clear();
        mCoinDataList.addAll(coinDataList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewSymbol;
        private TextView textViewName;
        private TextView textViewPrice;
        private TextView textViewMarketCap;
        private ImageView imageViewArrow;
        private CoinDetailsDialogFragment mCoinDetailsDialogFragment;

         ViewHolder(@NonNull final View itemView, CoinDetailsDialogFragment coinDetailsDialogFragment) {
            super(itemView);
            imageViewSymbol = itemView.findViewById(R.id.imageView_symbol);
            textViewName = itemView.findViewById(R.id.textView_name);
            textViewPrice = itemView.findViewById(R.id.textView_price);
            textViewMarketCap = itemView.findViewById(R.id.textView_marketCap);
            imageViewArrow = itemView.findViewById(R.id.imageView_arrow);
            mCoinDetailsDialogFragment = coinDetailsDialogFragment;
        }

        void bind(CoinData coinData) {
             textViewName.setText(StringUtil.nameAndSymbol(coinData.getName(), coinData.getSymbol()));
             textViewPrice.setText(StringUtil.appendDollarToPrice(coinData.getPriceUsd()));
             textViewMarketCap.setText(coinData.getMarketCapUsd());

            DisplayUtil.displayTextDrawable(imageViewSymbol, coinData.getSymbol());
            DisplayUtil.displayArrow(imageViewArrow, coinData.getPercentChange1h());
        }
    }
}