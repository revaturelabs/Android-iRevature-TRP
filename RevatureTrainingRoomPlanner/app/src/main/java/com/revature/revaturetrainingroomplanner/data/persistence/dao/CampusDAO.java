//package com.revature.revaturetrainingroomplanner.data.persistence.dao;
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Query;
//
//import com.revature.revaturetrainingroomplanner.data.model.Campus;
//
//import java.util.List;
//
//@Dao
//public interface CampusDAO extends BaseDAO<Campus> {
//
//    @Query("SELECT * FROM campuses")
//    LiveData<List<Campus>> getCampuses();
//
//    @Query("SELECT * FROM  campuses WHERE c_id = :id")
//    LiveData<List<Campus>> getCampusById(int id);
//    // @Query SELECT * FROM campuses WHERE id LIKE :id
//    // getCampusById("1*")
//}
