package com.dexter.myandroidarchitecture.activities.main;

import android.os.AsyncTask;

import com.dexter.myandroidarchitecture.database.beans.ExampleBean;
import com.dexter.myandroidarchitecture.database.database.MyDatabase;

import java.util.HashMap;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class MainPresenter {
    private MainApiCall mainCall;
    private MainView view;
    private String searchTerm;
    private boolean isConnected;
    private MyDatabase myDatabase;

    public void setMainCall(MainApiCall mainCall) {
        this.mainCall = mainCall;
    }

    public void setMyDatabase(MyDatabase myDatabase) {
        this.myDatabase = myDatabase;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void setView(MainView view) {
        this.view = view;
    }

    public void getSearchMovies() {
        view.showWait();
        HashMap<String, String> map = new HashMap<>();
        map.put("apikey", "3940588f");
        map.put("s", searchTerm);

        mainCall.getApiData(myDatabase, isConnected, map, new MainApiCall.GetExampleBeanCallback() {
            @Override
            public void onNext(List<ExampleBean> exampleBean) {
                if (isConnected) {
                    insertMovie(exampleBean);
                }
                view.removeWait();
                view.getMovieList(exampleBean);
            }

            @Override
            public void onError(String networkError) {
                view.removeWait();
                view.onFailure(networkError);
            }

            @Override
            public void onComplete(Disposable disposable) {
                disposable.dispose();
            }
        });
    }

    private void insertMovie(List<ExampleBean> exampleBean) {
        new InsertTask(myDatabase, exampleBean).execute();
    }

    public static class InsertTask extends AsyncTask<String, Void, String> {

        private final MyDatabase myDatabase;
        private final List<ExampleBean> exampleBean;

        InsertTask(MyDatabase myDatabase, List<ExampleBean> exampleBean) {
            this.myDatabase = myDatabase;
            this.exampleBean = exampleBean;
        }

        @Override
        protected String doInBackground(String... strings) {
            myDatabase.exampleDao().insert(exampleBean);
            return null;
        }
    }
}
