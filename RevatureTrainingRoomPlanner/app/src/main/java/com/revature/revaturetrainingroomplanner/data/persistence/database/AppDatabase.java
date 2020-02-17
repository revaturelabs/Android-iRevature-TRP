package com.revature.revaturetrainingroomplanner.data.persistence.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.revature.revaturetrainingroomplanner.data.model.Batch;
import com.revature.revaturetrainingroomplanner.data.model.Campus;
import com.revature.revaturetrainingroomplanner.data.model.Skill;
import com.revature.revaturetrainingroomplanner.data.model.Trainer;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.BaseDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.BatchDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.CampusDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.RoomDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.SkillDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.TrainerDAO;

@Database(entities = {Batch.class, Campus.class, com.revature.revaturetrainingroomplanner.data.model.Room.class, Skill.class, Trainer.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "revature_training_room_planner_db";

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {

        if( instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    DATABASE_NAME
            ).build();
        }

        return instance;
    }

    public <T> BaseDAO getDAO(T t) {
        if (t instanceof Batch) {
            return getBatchDAO();
        } else if (t instanceof Campus) {
            return getCampusDAO();
        } else if (t instanceof Room) {
            return getRoomDAO();
        } else if (t instanceof Skill) {
            return getSkillDAO();
        } else if (t instanceof Trainer) {
            return getTrainerDAO();
        }
        return null;
    }

    abstract BatchDAO getBatchDAO();

    abstract CampusDAO getCampusDAO();

    abstract RoomDAO getRoomDAO();

    abstract SkillDAO getSkillDAO();

    abstract TrainerDAO getTrainerDAO();
}