//package com.revature.revaturetrainingroomplanner.data.persistence.dao;
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Query;
//
//import com.revature.revaturetrainingroomplanner.data.model.Skill;
//
//import java.util.List;
//
//@Dao
//public interface SkillDAO extends BaseDAO<Skill> {
//
//    @Query("SELECT * FROM skills")
//    LiveData<List<Skill>> getSkills();
//
//    @Query("SELECT * FROM  skills WHERE s_id = :id")
//    LiveData<List<Skill>> getSkillById(int id);
//    // @Query SELECT * FROM skills WHERE id LIKE :id
//    // getSkillById("1*")
//}
