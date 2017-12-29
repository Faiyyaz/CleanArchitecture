package com.dexter.myandroidarchitecture.dagger.components;

import android.content.Context;


import com.dexter.myandroidarchitecture.activities.main.MainActivity;
import com.dexter.myandroidarchitecture.dagger.module.ActivityModule;
import com.dexter.myandroidarchitecture.dagger.qualifier.ActivityContext;
import com.dexter.myandroidarchitecture.dagger.scopes.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent extends AppComponent {

    @ActivityContext
    Context activityContext();

    //TODO CREATE INJECT METHOD FOR ACTIVITY HERE
    //create inject method for all of your activities

    void inject(MainActivity mainActivity);
}
