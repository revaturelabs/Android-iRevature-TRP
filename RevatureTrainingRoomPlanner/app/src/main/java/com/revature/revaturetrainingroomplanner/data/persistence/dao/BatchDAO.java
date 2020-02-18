package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.revature.revaturetrainingroomplanner.data.model.Batch;

import java.util.List;

@Dao
public interface BatchDAO extends BaseDAO<Batch> {

    @Override
    @Query("SELECT * FROM batches")
    LiveData<List<Batch>> getAll();

    @Query("SELECT * FROM batches WHERE ba_id = :id")
    LiveData<Batch> getByID(int id);

    @Override
    @Query("DELETE FROM batches")
    int deleteAll();

    // @Query SELECT * FROM batches WHERE id LIKE :id
    // getBatchById("1*")
}
