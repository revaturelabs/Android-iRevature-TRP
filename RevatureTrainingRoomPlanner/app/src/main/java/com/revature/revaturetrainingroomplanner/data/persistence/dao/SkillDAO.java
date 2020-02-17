package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.revature.revaturetrainingroomplanner.data.model.Skill;

import java.util.List;

@Dao
public interface SkillDAO extends BaseDAO<Skill> {

    @Query("SELECT * FROM batches")
    LiveData<List<Skill>> getSkills();

    @Query("SELECT * FROM  batches WHERE id = :id")
    LiveData<List<Skill>> getSkillById(int id);
    // @Query SELECT * FROM batches WHERE id LIKE :id
    // getSkillById("1*")
}
