package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.revature.revaturetrainingroomplanner.data.model.Batch;
import com.revature.revaturetrainingroomplanner.data.model.BatchWithSkills;

import java.util.List;

@Dao
public interface BatchDAO extends BaseDAO<Batch> {

    @Deprecated
    @Override
    @Transaction
    @Query("SELECT * FROM batches")
    LiveData<List<Batch>> getAll();

    @Transaction
    @Query("SELECT * FROM batches")
    LiveData<List<BatchWithSkills>> getAllBatches();

    @Override
    @Transaction
    @Query("SELECT * FROM batches WHERE ba_id = :id")
    Batch getByID(long id);

    @Override
    @Transaction
    @Query("DELETE FROM batches")
    int deleteAll();

    // @Query SELECT * FROM batches WHERE id LIKE :id
    // getBatchById("1*")
}
