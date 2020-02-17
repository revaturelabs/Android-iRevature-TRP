package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.revature.revaturetrainingroomplanner.data.model.Skill;

import java.util.List;

@Dao
public interface SkillDAO extends BaseDAO<Skill> {

    @Override
    @Query("SELECT * FROM skills")
    LiveData<List<Skill>> getAll();

    @Override
    @Query("SELECT * FROM skills WHERE s_id = :id")
    LiveData<Skill> getByID(int id);

    @Override
    @Query("DELETE FROM skills")
    long deleteAll();
    
}
