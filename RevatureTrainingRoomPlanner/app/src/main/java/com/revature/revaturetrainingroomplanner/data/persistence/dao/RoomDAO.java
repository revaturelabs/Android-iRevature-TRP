//package com.revature.revaturetrainingroomplanner.data.persistence.dao;
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Query;
//
//import com.revature.revaturetrainingroomplanner.data.model.Room;
//
//import java.util.List;
//
//@Dao
//public interface RoomDAO extends BaseDAO<Room> {
//
//    @Query("SELECT * FROM rooms")
//    LiveData<List<Room>> getRooms();
//
//    @Query("SELECT * FROM  rooms WHERE r_id = :id")
//    LiveData<List<Room>> getRoomById(int id);
//    // @Query SELECT * FROM rooms WHERE id LIKE :id
//    // getRoomById("1*")
//}
