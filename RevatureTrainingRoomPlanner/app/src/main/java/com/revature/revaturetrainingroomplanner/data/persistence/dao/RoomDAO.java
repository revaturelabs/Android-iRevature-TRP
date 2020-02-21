package com.revature.revaturetrainingroomplanner.data.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.revature.revaturetrainingroomplanner.data.model.Room;
import com.revature.revaturetrainingroomplanner.data.model.RoomWithBatchAssignments;

import java.util.List;

@Dao
public interface RoomDAO extends BaseDAO<Room> {

    @Deprecated
    @Override
    @Transaction
    @Query("SELECT * FROM rooms")
    LiveData<List<Room>> getAll();

    @Transaction
    @Query("SELECT * FROM rooms")
    LiveData<List<RoomWithBatchAssignments>> getAllRooms();

    @Transaction
    @Query("SELECT * FROM rooms WHERE r_id = :id")
    LiveData<Room> getByID(int id);

    @Override
    @Transaction
    @Query("DELETE FROM rooms")
    int deleteAll();

}
