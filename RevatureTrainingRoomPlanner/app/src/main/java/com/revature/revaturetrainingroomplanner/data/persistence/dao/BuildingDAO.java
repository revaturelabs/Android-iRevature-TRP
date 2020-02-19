package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.revature.revaturetrainingroomplanner.data.model.Building;

import java.util.List;

@Dao
public interface BuildingDAO extends BaseDAO<Building> {

    @Override
    @Query("SELECT * FROM buildings")
    LiveData<List<Building>> getAll();

    @Query("SELECT * FROM buildings WHERE bu_id = :id")
    LiveData<Building> getByID(int id);

    @Override
    @Query("DELETE FROM buildings")
    int deleteAll();

    // @Query SELECT * FROM batches WHERE id LIKE :id
    // getBuildingById("1*")
}
