package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.revature.revaturetrainingroomplanner.data.model.Building;
import com.revature.revaturetrainingroomplanner.data.model.BuildingWithRooms;

import java.util.List;

@Dao
public interface BuildingDAO extends BaseDAO<Building> {

    @Deprecated
    @Override
    @Transaction
    @Query("SELECT * FROM buildings")
    LiveData<List<Building>> getAll();

    @Transaction
    @Query("SELECT * FROM buildings")
    LiveData<List<BuildingWithRooms>> getAllBuildings();

    @Override
    @Transaction
    @Query("SELECT * FROM buildings WHERE bu_id = :id")
    Building getByID(long id);

    @Override
    @Transaction
    @Query("DELETE FROM buildings")
    int deleteAll();

    // @Query SELECT * FROM batches WHERE id LIKE :id
    // getBuildingById("1*")
}
