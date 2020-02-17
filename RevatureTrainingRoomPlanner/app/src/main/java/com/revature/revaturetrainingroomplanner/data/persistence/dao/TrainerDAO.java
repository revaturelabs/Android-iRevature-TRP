package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.revature.revaturetrainingroomplanner.data.model.Trainer;

import java.util.List;

@Dao
public interface TrainerDAO extends BaseDAO<Trainer> {

    @Query("SELECT * FROM batches")
    LiveData<List<Trainer>> getTrainers();

    @Query("SELECT * FROM  batches WHERE id = :id")
    LiveData<List<Trainer>> getTrainerById(int id);
    // @Query SELECT * FROM batches WHERE id LIKE :id
    // getTrainerById("1*")
}
