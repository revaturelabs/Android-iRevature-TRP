package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.revature.revaturetrainingroomplanner.data.model.TrainerSkillCrossRef;

import java.util.List;

@Dao
public interface TrainerSkillCrossRefDAO extends BaseDAO<TrainerSkillCrossRef> {

    @Override
    @Query("SELECT * FROM trainerskillcrossref")
    LiveData<List<TrainerSkillCrossRef>> getAll();

    @Override
    @Query("DELETE FROM trainerskillcrossref")
    int deleteAll();
}
