package com.stutern.cryptocurrencyapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.stutern.cryptocurrencyapp.IMainActivity;
import com.stutern.cryptocurrencyapp.R;

public class MainActivity extends AppCompatActivity implements IMainActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {
        MainFragment mainFragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, mainFragment, "Main Fragment");
        transaction.commit();
    }

    @Override
    public void onItemClick(CoinDetailsDialogFragment dialogFragment) {
        dialogFragment.show(getSupportFragmentManager(), "CoinDetailsDialogFragment");
    }

}
