package com.dexter.myandroidarchitecture.dagger.module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.dexter.myandroidarchitecture.dagger.qualifier.ChildFragmentManager;
import com.dexter.myandroidarchitecture.dagger.scopes.PerFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private final Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PerFragment
    @ChildFragmentManager
    FragmentManager provideChildFragmentManager() {
        return mFragment.getChildFragmentManager();
    }
}
