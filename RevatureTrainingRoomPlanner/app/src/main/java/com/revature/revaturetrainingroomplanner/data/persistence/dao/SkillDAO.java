package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.revature.revaturetrainingroomplanner.data.model.Skill;

import java.util.List;

@Dao
public interface SkillDAO extends BaseDAO<Skill> {

    @Override
    @Transaction
    @Query("SELECT * FROM skills")
    LiveData<List<Skill>> getAll();

    @Override
    @Transaction
    @Query("DELETE FROM skills")
    int deleteAll();
    
}
