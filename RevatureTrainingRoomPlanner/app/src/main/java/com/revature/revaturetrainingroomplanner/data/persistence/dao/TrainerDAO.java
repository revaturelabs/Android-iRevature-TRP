package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.revature.revaturetrainingroomplanner.data.model.Trainer;

import java.util.List;

@Dao
public interface TrainerDAO extends BaseDAO<Trainer> {

    @Override
    @Query("SELECT * FROM trainers")
    LiveData<List<Trainer>> getAll();

    @Query("SELECT * FROM trainers WHERE t_id = :id")
    LiveData<Trainer> getByID(int id);

    @Override
    @Query("DELETE FROM trainers")
    int deleteAll();
    
}
