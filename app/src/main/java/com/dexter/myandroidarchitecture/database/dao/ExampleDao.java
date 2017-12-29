package com.dexter.myandroidarchitecture.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.dexter.myandroidarchitecture.database.beans.ExampleBean;
import com.dexter.myandroidarchitecture.utils.Constants;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by Admin on 29-12-2017.
 */

@Dao
public interface ExampleDao {
    @Query("SELECT * FROM " + Constants.TABLE_NAME)
    Flowable<List<ExampleBean>> getExample();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ExampleBean> exampleBean);

    @Query("DELETE FROM " + Constants.TABLE_NAME)
    void deleteAll();
}
