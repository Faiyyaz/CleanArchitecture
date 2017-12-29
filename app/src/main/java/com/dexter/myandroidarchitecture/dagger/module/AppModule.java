package com.dexter.myandroidarchitecture.dagger.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.res.Resources;

import com.dexter.myandroidarchitecture.dagger.qualifier.AppContext;
import com.dexter.myandroidarchitecture.dagger.scopes.PerApplication;
import com.dexter.myandroidarchitecture.database.database.MyDatabase;
import com.dexter.myandroidarchitecture.utils.Constants;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application mApp;
    private MyDatabase INSTANCE;
    private final Object sLock = new Object();


    public AppModule(Application mApp) {
        this.mApp = mApp;
    }

    @Provides
    @PerApplication
    @AppContext
    Context provideAppContext() {
        return mApp;
    }

    @Provides
    @PerApplication
    Resources provideResources() {
        return mApp.getResources();
    }

    @Provides
    @PerApplication
    MyDatabase providesDatabase() {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(provideAppContext(), MyDatabase.class, Constants.DATABASE_NAME).build();
            }
            return INSTANCE;
        }
    }
}
