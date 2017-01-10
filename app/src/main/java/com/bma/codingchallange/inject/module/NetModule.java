package com.bma.codingchallange.inject.module;

import com.bma.codingchallange.database.RealmHelper;
import com.bma.codingchallange.log.LPLog;
import com.bma.codingchallange.network.LPNetworkManager;
import com.bma.codingchallange.network.LPServices;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;
import retrofit.converter.JacksonConverter;

/**
 * Created by balanescumadalin on 10/01/2017.
 */

@Module
public class NetModule {

    String mBaseUrl;

    public NetModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public LPServices apiService() {

        RestAdapter mGetSTATUSAdapter = new RestAdapter.Builder()
                .setEndpoint(mBaseUrl)
                .setConverter(new JacksonConverter())
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog(LPLog.LPLogTAG))
                .build();
        LPServices mService = mGetSTATUSAdapter.create(LPServices.class);

        return mService;

    }

    @Provides
    @Singleton
    public LPNetworkManager provideNetworkManager() {
        return new LPNetworkManager();
    }

}
