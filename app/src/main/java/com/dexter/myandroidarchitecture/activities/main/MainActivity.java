package com.dexter.myandroidarchitecture.activities.main;

import com.dexter.myandroidarchitecture.R;
import com.dexter.myandroidarchitecture.activities.BaseActivity;
import com.dexter.myandroidarchitecture.database.beans.ExampleBean;
import com.dexter.myandroidarchitecture.database.database.MyDatabase;
import com.dexter.myandroidarchitecture.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Admin on 29-12-2017.
 */

public class MainActivity extends BaseActivity implements MainView {

    private static final String TAG = "MainActivity";
    @Inject
    MainApiCall mainApiCall;
    @Inject
    MyDatabase myDatabase;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public void onActivityCreated() {
        activityComponent().inject(this);
        initCall();
    }

    private void initCall() {
        MainPresenter presenter = new MainPresenter();
        presenter.setMainCall(mainApiCall);
        presenter.setMyDatabase(myDatabase);
        presenter.setConnected(isNetworkConnected());
        presenter.setView(this);
        presenter.setSearchTerm("Galaxy");
        presenter.getSearchMovies();
    }

    @Override
    public void showWait() {

    }

    @Override
    public void removeWait() {

    }

    @Override
    public void onFailure(String error) {
        showMessage(Constants.error, error);
    }

    @Override
    public void getMovieList(List<ExampleBean> exampleResponse) {
        Timber.d(String.valueOf(exampleResponse.size()));
    }
}
