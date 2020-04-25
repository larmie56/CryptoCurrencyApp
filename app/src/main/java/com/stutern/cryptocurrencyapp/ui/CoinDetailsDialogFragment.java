package com.stutern.cryptocurrencyapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.stutern.cryptocurrencyapp.CryptoCurrencyRoomDb.CoinDataEntity;
import com.stutern.cryptocurrencyapp.model.CoinData;
//import com.stutern.cryptocurrencyapp.IDialogFragment;
import com.stutern.cryptocurrencyapp.R;
import com.stutern.cryptocurrencyapp.databinding.DialogFragmentCoinDetailsBinding;
import com.stutern.cryptocurrencyapp.utilities.DisplayUtil;
import com.stutern.cryptocurrencyapp.utilities.StringUtil;

public class CoinDetailsDialogFragment extends DialogFragment {
    DialogFragmentCoinDetailsBinding mBinding;
    private ImageView mImageViewSymbol;
    private TextView mTextViewName;
    private TextView mTextViewPrice;
    private TextView mTextViewMarketCap;
    private TextView mTextView24hVolume;
    private TextView mTextViewAvailableSupply;
    private TextView mTextViewTotalSupply;
    private TextView mTextViewPercentageChange24h;
    private ImageView mImageViewArrow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_coin_details, container, false);
        return inflater.inflate(R.layout.dialog_fragment_coin_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mImageViewSymbol = view.findViewById(R.id.imageView_symbol_dialog);
        mTextViewName = view.findViewById(R.id.textView_name_dialog);
        mTextViewPrice = view.findViewById(R.id.textView_price_dialog);
        mTextViewMarketCap = view.findViewById(R.id.textView_marketCap_dialog);
        mTextView24hVolume = view.findViewById(R.id.textView_24h_volume_dialog);
        mTextViewAvailableSupply = view.findViewById(R.id.textView_availableSupply_dialog);
        mTextViewTotalSupply = view.findViewById(R.id.textView_totalSupply_dialog);
        mTextViewPercentageChange24h = view.findViewById(R.id.textView_percentageChange24h_dialog);
        mImageViewArrow = view.findViewById(R.id.imageView_arrow_dialog);

        CoinDataEntity coinData = getArguments().getParcelable(CoinDataEntity.TAG);
        bindData(coinData);
    }

    private void bindData(CoinDataEntity coinData) {
        DisplayUtil.displayTextDrawable(mImageViewSymbol, coinData.getSymbol());
        mTextViewName.setText(StringUtil.nameAndSymbol(coinData.getName(), coinData.getSymbol()));
        mTextViewPrice.setText(coinData.getPriceUsd());
        mTextViewMarketCap.setText(coinData.getMarketCapUsd());
        mTextView24hVolume.setText(coinData.get24hVolumeUsd());
        mTextViewAvailableSupply.setText("Nothing here bro");
        mTextViewTotalSupply.setText(coinData.getTotalSupply());
        mTextViewPercentageChange24h.setText(coinData.getPercentChange24h());
        DisplayUtil.displayArrow(mImageViewArrow, coinData.getPercentChange24h());
    }
}
