package com.dexter.myandroidarchitecture;

import android.app.Application;
import android.content.res.Resources;

import com.dexter.myandroidarchitecture.dagger.components.AppComponent;
import com.dexter.myandroidarchitecture.dagger.components.DaggerAppComponent;
import com.dexter.myandroidarchitecture.dagger.module.AppModule;
import com.dexter.myandroidarchitecture.dagger.module.NetworkModule;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

public class MyApplication extends Application {

    private static MyApplication sInstance = null;

    private static AppComponent sAppComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        sAppComponent = DaggerAppComponent.builder()
                .networkModule(new NetworkModule())
                .appModule(new AppModule(this))
                .build();

        initTimber();

        initLeakCanary();
    }

    public static MyApplication getInstance() {
        return sInstance;
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    public static Resources getRes() {
        return sInstance.getResources();
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
