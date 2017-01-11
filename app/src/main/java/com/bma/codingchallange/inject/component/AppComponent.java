package com.bma.codingchallange.inject.component;

import com.bma.codingchallange.database.RealmHelper;
import com.bma.codingchallange.inject.module.AppModule;
import com.bma.codingchallange.inject.module.NetModule;
import com.bma.codingchallange.manager.LPFragmentManager;
import com.bma.codingchallange.network.LPNetworkManager;
import com.bma.codingchallange.presenter.MainPresenter;
import com.bma.codingchallange.ui.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by balanescumadalin on 10/01/2017.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    MainActivity inject(MainActivity mainActivity);

    MainPresenter inject(MainPresenter mainPresenter);


    LPNetworkManager inject(LPNetworkManager networkManager);

    LPFragmentManager inject(LPFragmentManager fragmentManager);



    RealmHelper inject(RealmHelper realmHelper);


}
