package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.revature.revaturetrainingroomplanner.data.model.Room;

import java.util.List;

@Dao
public interface RoomDAO extends BaseDAO<Room> {

    @Override
    @Query("SELECT * FROM rooms")
    LiveData<List<Room>> getAll();

    @Override
    @Query("SELECT * FROM rooms WHERE r_id = :id")
    LiveData<Room> getByID(int id);

    @Override
    @Query("DELETE FROM rooms")
    int deleteAll();

}
