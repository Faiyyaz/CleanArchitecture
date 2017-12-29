package com.dexter.myandroidarchitecture.activities;

import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dexter.myandroidarchitecture.MyApplication;
import com.dexter.myandroidarchitecture.R;
import com.dexter.myandroidarchitecture.dagger.components.ActivityComponent;
import com.dexter.myandroidarchitecture.dagger.components.AppComponent;
import com.dexter.myandroidarchitecture.dagger.components.DaggerActivityComponent;
import com.dexter.myandroidarchitecture.dagger.module.ActivityModule;
import com.dexter.myandroidarchitecture.interfaces.BaseView;
import com.dexter.myandroidarchitecture.utils.Constants;
import com.dexter.myandroidarchitecture.utils.NetworkUtils;

import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import timber.log.Timber;

/**
 * Created by Admin on 29-12-2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private ActivityComponent mActivityComponent;

    public ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .appComponent(MyApplication.getAppComponent())
                    .build();
        }
        return mActivityComponent;
    }

    protected AppComponent applicationComponent() {
        return MyApplication.getAppComponent();
    }

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        //binding Butterknife here so as to avoid binding it in every view
        ButterKnife.bind(this);

        //adding tag to timber here
        Timber.tag(getTag());

        onActivityCreated();

        if (!isNetworkConnected()) {
            showMessage(getString(R.string.no_internet_available), Constants.error);
        }
    }

    /**
     * Pass the layout ID here
     *
     * @return the layout resource ID
     */
    public abstract
    @LayoutRes
    int getLayout();

    /**
     * Add the class name here
     */
    public abstract String getTag();

    public abstract void onActivityCreated();

    @Override
    public void showMessage(String message, String type) {
        switch (type) {
            case Constants.error:
                Toasty.error(this, message, Toast.LENGTH_SHORT, true).show();
                break;
            case Constants.success:
                Toasty.success(this, message, Toast.LENGTH_SHORT, true).show();
                break;
            case Constants.info:
                Toasty.info(this, message, Toast.LENGTH_SHORT, true).show();
                break;
            case Constants.warning:
                Toasty.warning(this, message, Toast.LENGTH_SHORT, true).show();
                break;
            case Constants.normal:
                Toasty.normal(this, message, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkAvailable(this);
    }
}
