package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BaseDAO<T> {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(T... object);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(T object);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<T> objects);

    LiveData<List<T>> getAll();

    @Transaction
    @Update
    int update(T... objects);

    @Transaction
    @Delete
    int delete(T... objects);

    int deleteAll();

}