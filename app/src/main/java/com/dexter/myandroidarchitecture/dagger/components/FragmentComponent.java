package com.dexter.myandroidarchitecture.dagger.components;

import com.dexter.myandroidarchitecture.dagger.module.FragmentModule;
import com.dexter.myandroidarchitecture.dagger.scopes.PerFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ActivityComponent.class, modules = {FragmentModule.class})
public interface FragmentComponent {

    //TODO CREATE INJECT METHOD FOR FRAGMENTS HERE
    // create inject methods for your Fragment here
}
