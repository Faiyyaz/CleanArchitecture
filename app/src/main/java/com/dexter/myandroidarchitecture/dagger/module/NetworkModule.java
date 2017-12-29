package com.dexter.myandroidarchitecture.dagger.module;

import com.dexter.myandroidarchitecture.BuildConfig;
import com.dexter.myandroidarchitecture.activities.main.MainApiCall;
import com.dexter.myandroidarchitecture.api.RetrofitApiInterface;
import com.dexter.myandroidarchitecture.dagger.scopes.PerApplication;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    //TODO CHANGE YOUR BASE URL HERE
    private static final String BASE_URL = "http://www.omdbapi.com";

    @Provides
    @PerApplication
    Retrofit provideCall() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        httpClient.addInterceptor(loggingInterceptor);
        OkHttpClient okHttpClient = httpClient.build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    //TODO ADD API SERVICE & METHODS HERE

    @Provides
    @PerApplication
    public RetrofitApiInterface providesretrofitApiInterface(
            Retrofit retrofit) {
        return retrofit.create(RetrofitApiInterface.class);
    }

    @Provides
    @PerApplication
    public MainApiCall providesmainApiCall(
            RetrofitApiInterface retrofitApiInterface) {
        return new MainApiCall(retrofitApiInterface);
    }
}
