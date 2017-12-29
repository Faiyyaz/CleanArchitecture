package com.dexter.myandroidarchitecture.dagger.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.dexter.myandroidarchitecture.dagger.qualifier.ActivityContext;
import com.dexter.myandroidarchitecture.dagger.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    @PerActivity
    @ActivityContext
    Context provideActivityContext() {
        return mActivity;
    }
}
