package com.dexter.myandroidarchitecture.activities.main;

import com.dexter.myandroidarchitecture.api.ExampleResponse;
import com.dexter.myandroidarchitecture.api.RetrofitApiInterface;
import com.dexter.myandroidarchitecture.database.beans.ExampleBean;
import com.dexter.myandroidarchitecture.database.database.MyDatabase;
import com.dexter.myandroidarchitecture.utils.NetworkUtils;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Admin on 29-12-2017.
 */

public class MainApiCall {

    private RetrofitApiInterface retrofitApiInterface;
    private CompositeDisposable disposeBag;
    private Disposable disposable;

    public MainApiCall(RetrofitApiInterface retrofitApiInterface) {
        this.retrofitApiInterface = retrofitApiInterface;
        disposeBag = new CompositeDisposable();
    }

    public void getApiData(MyDatabase myDatabase, boolean isConnected, HashMap<String, String> map, final GetExampleBeanCallback callback) {
        if (isConnected) {
            getExampleBean(map, callback);
        } else {
            Flowable<List<ExampleBean>> exampleBean = myDatabase.exampleDao().getExample();
            getCacheExampleBean(exampleBean, callback);
        }
    }

    private Flowable<List<ExampleBean>> getExampleObservable(HashMap<String, String> map) {
        return retrofitApiInterface.getMovies(map).map(ExampleResponse::getSearch);
    }


    private void getExampleBean(HashMap<String, String> map, final GetExampleBeanCallback callback) {
        disposable = getExampleObservable(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onNext, throwable -> callback.onError(NetworkUtils.getStringError(throwable)), () -> callback.onComplete(disposable));
        disposeBag.add(disposable);
    }

    private void getCacheExampleBean(Flowable<List<ExampleBean>> exampleBean, GetExampleBeanCallback callback) {
        disposable = exampleBean
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onNext, throwable -> callback.onError(NetworkUtils.getStringError(throwable)), () -> callback.onComplete(disposable));
        disposeBag.add(disposable);
    }

    public interface GetExampleBeanCallback {
        void onNext(List<ExampleBean> exampleBeans);

        void onError(String networkError);

        void onComplete(Disposable disposable);
    }
}
