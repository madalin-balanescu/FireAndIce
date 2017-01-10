package com.bma.codingchallange.application;

import android.app.Application;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.bma.codingchallange.inject.component.AppComponent;
import com.bma.codingchallange.inject.component.DaggerAppComponent;
import com.bma.codingchallange.inject.module.AppModule;
import com.bma.codingchallange.inject.module.NetModule;
import com.bma.codingchallange.network.ServerConstants;
import com.bma.codingchallange.utils.Utils;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by balanescumadalin on 10/01/2017.
 */

public class App extends Application {

    private AppComponent mComponent;

    private static Context mAppContext;

    public static Context getAppContext() {
        return mAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
        initRealmConfiguration();
    }

    @VisibleForTesting
    protected AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(ServerConstants.BASE_URL))
                .build();
    }

    private void initRealmConfiguration() {
        Realm.init(this);
        RealmConfiguration defaultConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded().build();

        if (Utils.isVersionCodeUpdated()) {
            Realm.deleteRealm(defaultConfiguration);
        }

        Realm.setDefaultConfiguration(defaultConfiguration);
    }


    public static AppComponent getAppComponent() {
        App app = (App) mAppContext.getApplicationContext();
        if (app.mComponent == null) {
            app.mComponent = app.createComponent();
        }
        return app.mComponent;
    }

}
