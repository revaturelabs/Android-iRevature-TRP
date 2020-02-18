package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BaseDAO<T> {

    @Insert
    long[] insert(T... objects);

    @Insert
    long[] insertAll(List<T> objects);

    LiveData<List<T>> getAll();

    @Update
    int update(T... objects);

    @Delete
    int delete(T... objects);

    int deleteAll();

}