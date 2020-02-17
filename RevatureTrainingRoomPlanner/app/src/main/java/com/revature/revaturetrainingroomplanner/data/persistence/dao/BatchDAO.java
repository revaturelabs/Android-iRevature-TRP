//package com.revature.revaturetrainingroomplanner.data.persistence.dao;
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Query;
//
//import com.revature.revaturetrainingroomplanner.data.model.Batch;
//
//import java.util.List;
//
//@Dao
//public interface BatchDAO extends BaseDAO<Batch> {
//
//    @Query("SELECT * FROM batches")
//    LiveData<List<Batch>> getBatches();
//
//    @Query("SELECT * FROM  batches WHERE b_id = :id")
//    LiveData<List<Batch>> getBatchById(int id);
//    // @Query SELECT * FROM batches WHERE id LIKE :id
//    // getBatchById("1*")
//}
