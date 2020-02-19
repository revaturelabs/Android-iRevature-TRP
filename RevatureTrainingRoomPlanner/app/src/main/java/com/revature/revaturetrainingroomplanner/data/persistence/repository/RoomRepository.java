package com.revature.revaturetrainingroomplanner.data.persistence.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.revature.revaturetrainingroomplanner.data.async.DeleteAllAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.DeleteAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.InsertAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.UpdateAsyncTask;
import com.revature.revaturetrainingroomplanner.data.model.Room;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.BaseDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.RoomDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.database.AppDatabase;

import java.util.List;

public class RoomRepository {

    private static final String TAG = "RoomRepository";

    private AppDatabase mAppDatabase;
    private BaseDAO<Room> mDao;

    public RoomRepository(Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        mDao = mAppDatabase.getDAO(Room.class);
    }

    public void insertRoomTask(Room... rooms) {
        Log.d(TAG, "insertCampusTask: inserting " + rooms.toString());
        new InsertAsyncTask(mDao).execute(rooms);
    }

    public LiveData<Room> retrieveByIDTask(int id) {
        return ((RoomDAO)mDao).getByID(id);
    }

    public LiveData<List<Room>> retrieveAllTask() {
        return mDao.getAll();
    }

    public void updateTask(Room... rooms) {
        new UpdateAsyncTask(mDao).execute(rooms);
    }

    public void deleteTask(Room... rooms) {
        new DeleteAsyncTask(mDao).execute(rooms);
    }

    public void deleteAllTask(Room... rooms) {
        new DeleteAllAsyncTask(mDao).execute(rooms);
    }

}
