package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.revature.revaturetrainingroomplanner.data.model.Trainer;
import com.revature.revaturetrainingroomplanner.data.model.TrainerWithSkills;

import java.util.List;

@Dao
public interface TrainerDAO extends BaseDAO<Trainer> {

    @Deprecated
    @Override
    @Transaction
    @Query("SELECT * FROM trainers ")
    LiveData<List<Trainer>> getAll();

    @Transaction
    @Query("SELECT * FROM trainers")
    LiveData<List<TrainerWithSkills>> getAllTrainers();

    @Override
    @Transaction
    @Query("SELECT * FROM trainers WHERE t_id = :id")
    Trainer getByID(long id);

    @Transaction
    @Override
    @Query("DELETE FROM trainers")
    int deleteAll();
    
}
