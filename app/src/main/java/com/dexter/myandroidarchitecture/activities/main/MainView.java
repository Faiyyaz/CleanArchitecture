package com.dexter.myandroidarchitecture.activities.main;

import com.dexter.myandroidarchitecture.database.beans.ExampleBean;

import java.util.List;

public interface MainView {
    void showWait();

    void removeWait();

    void onFailure(String error);

    void getMovieList(List<ExampleBean> exampleResponse);
}
