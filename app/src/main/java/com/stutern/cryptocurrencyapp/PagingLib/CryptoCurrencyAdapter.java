package com.stutern.cryptocurrencyapp.PagingLib;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CoinDataEntity;
import com.stutern.cryptocurrencyapp.IMainActivity;
import com.stutern.cryptocurrencyapp.R;
import com.stutern.cryptocurrencyapp.ui.CoinDetailsDialogFragment;
import com.stutern.cryptocurrencyapp.utilities.DisplayUtil;
import com.stutern.cryptocurrencyapp.utilities.StringUtil;

public class CryptoCurrencyAdapter extends PagedListAdapter<CoinDataEntity, CryptoCurrencyAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private CoinDetailsDialogFragment mCoinDetailsDialogFragment = new CoinDetailsDialogFragment();

    public CryptoCurrencyAdapter(LayoutInflater inflater) {
        super(CoinDataEntity.DIFF_UTIL);
        mInflater = inflater;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final CoinDataEntity coinData = getItem(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(CoinDataEntity.TAG, coinData);
                mCoinDetailsDialogFragment.setArguments(bundle);
                ((IMainActivity) holder.itemView.getContext()).onItemClick(mCoinDetailsDialogFragment);
            }
        });
        holder.bind(coinData);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewSymbol;
        private TextView textViewName;
        private TextView textViewPrice;
        private TextView textViewMarketCap;
        private ImageView imageViewArrow;

         ViewHolder(@NonNull final View itemView) {
            super(itemView);
                 imageViewSymbol = itemView.findViewById(R.id.imageView_symbol);
                 textViewName = itemView.findViewById(R.id.textView_name);
                 textViewPrice = itemView.findViewById(R.id.textView_price);
                 textViewMarketCap = itemView.findViewById(R.id.textView_marketCap);
                 imageViewArrow = itemView.findViewById(R.id.imageView_arrow);
        }

        void bind(CoinDataEntity coinDataEntity) {
                 textViewName.setText(StringUtil.nameAndSymbol(coinDataEntity.getName(), coinDataEntity.getSymbol()));
                 textViewPrice.setText(StringUtil.appendDollarToPrice(coinDataEntity.getPriceUsd()));
                 textViewMarketCap.setText(coinDataEntity.getMarketCapUsd());

                 DisplayUtil.displayTextDrawable(imageViewSymbol, coinDataEntity.getSymbol());
                 DisplayUtil.displayArrow(imageViewArrow, coinDataEntity.getPercentChange1h());
        }
    }
}