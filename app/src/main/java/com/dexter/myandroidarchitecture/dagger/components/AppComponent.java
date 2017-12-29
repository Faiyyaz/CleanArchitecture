package com.dexter.myandroidarchitecture.dagger.components;


import android.content.Context;
import android.content.res.Resources;

import com.dexter.myandroidarchitecture.activities.main.MainApiCall;
import com.dexter.myandroidarchitecture.dagger.module.AppModule;
import com.dexter.myandroidarchitecture.dagger.module.NetworkModule;
import com.dexter.myandroidarchitecture.dagger.qualifier.AppContext;
import com.dexter.myandroidarchitecture.dagger.scopes.PerApplication;
import com.dexter.myandroidarchitecture.database.database.MyDatabase;

import dagger.Component;

@PerApplication
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    @AppContext
    Context appContext();

    Resources resources();

    MyDatabase myDatabase();

    //TODO ADD ROOM & API METHODS
    MainApiCall apiCall();
}
