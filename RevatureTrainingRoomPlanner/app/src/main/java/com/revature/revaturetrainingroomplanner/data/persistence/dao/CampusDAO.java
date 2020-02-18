package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.revature.revaturetrainingroomplanner.data.model.Campus;

import java.util.List;

@Dao
public interface CampusDAO extends BaseDAO<Campus> {

    @Override
    @Query("SELECT * FROM campuses")
    LiveData<List<Campus>> getAll();

    @Query("SELECT * FROM campuses WHERE c_id = :id")
    LiveData<Campus> getByID(int id);

    @Override
    @Query("DELETE FROM campuses")
    int deleteAll();

}
