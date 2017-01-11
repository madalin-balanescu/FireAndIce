package com.bma.codingchallange.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bma.codingchallange.R;
import com.bma.codingchallange.application.App;
import com.bma.codingchallange.database.RealmHelper;
import com.bma.codingchallange.log.LPLog;
import com.bma.codingchallange.manager.LPFragmentManager;
import com.bma.codingchallange.model.Book;
import com.bma.codingchallange.network.LPNetworkManager;
import com.bma.codingchallange.presenter.MainPresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    public RealmHelper realmHelper;
    @Inject
    public LPFragmentManager fragmentManager;
    @Inject
    public MainPresenter mainPresenter;
    @Inject
    public LPNetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager.setActivity(this);

        fragmentManager.displayBooks();

    }

    @Override
    protected void onDestroy() {
        realmHelper.release();
        super.onDestroy();
    }


}
