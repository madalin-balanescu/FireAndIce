package com.bma.codingchallange.inject.module;

import android.app.Application;
import android.content.Context;

import com.bma.codingchallange.database.RealmHelper;
import com.bma.codingchallange.manager.LPFragmentManager;
import com.bma.codingchallange.presenter.MainPresenter;
import com.bma.codingchallange.ui.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by balanescumadalin on 10/01/2017.
 */

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Context providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    public RealmHelper provideDatabaseRealm() {
        return new RealmHelper();
    }


    @Provides
    @Singleton
    public LPFragmentManager provideFragmentManager() {
        return new LPFragmentManager();
    }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter(){
        return new MainPresenter();
    }


}
