package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.revature.revaturetrainingroomplanner.data.model.BatchAssignment;

import java.util.List;

@Dao
public interface BatchAssignmentDAO extends BaseDAO<BatchAssignment> {

    @Override
    @Query("SELECT * FROM batch_assignments")
    LiveData<List<BatchAssignment>> getAll();

    @Override
    @Query("DELETE FROM batch_assignments")
    int deleteAll();

    // @Query SELECT * FROM batches WHERE id LIKE :id
    // getBatchAssignmentById("1*")
}
