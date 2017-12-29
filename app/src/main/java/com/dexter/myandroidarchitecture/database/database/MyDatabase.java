package com.dexter.myandroidarchitecture.database.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.dexter.myandroidarchitecture.database.beans.ExampleBean;
import com.dexter.myandroidarchitecture.database.dao.ExampleDao;

/**
 * Created by Admin on 29-12-2017.
 */


@Database(entities = ExampleBean.class,version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract ExampleDao exampleDao();
}
