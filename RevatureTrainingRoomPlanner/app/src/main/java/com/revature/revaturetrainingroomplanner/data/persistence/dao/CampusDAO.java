package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.revature.revaturetrainingroomplanner.data.model.Campus;

import java.util.List;

@Dao
public interface CampusDAO extends BaseDAO<Campus> {

    @Override
    @Transaction
    @Query("SELECT * FROM campuses")
    LiveData<List<Campus>> getAll();

    @Transaction
    @Query("SELECT * FROM campuses WHERE c_id = :id")
    LiveData<Campus> getByID(int id);

    @Override
    @Transaction
    @Query("DELETE FROM campuses")
    int deleteAll();

}
