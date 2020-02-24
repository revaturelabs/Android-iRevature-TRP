package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.revature.revaturetrainingroomplanner.data.model.BatchSkillCrossRef;

import java.util.List;

@Dao
public interface BatchSkillCrossRefDAO extends BaseDAO<BatchSkillCrossRef> {

    @Override
    @Query("SELECT * FROM batchskillcrossref")
    LiveData<List<BatchSkillCrossRef>> getAll();

    @Override
    @Query("SELECT * FROM batchskillcrossref WHERE ba_id = :ID")
    BatchSkillCrossRef getByID(long ID);

    @Override
    @Query("DELETE FROM batchskillcrossref")
    int deleteAll();
}
