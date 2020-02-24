package com.revature.revaturetrainingroomplanner.data.persistence.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.revature.revaturetrainingroomplanner.data.async.DeleteAllAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.DeleteAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.InsertAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.UpdateAsyncTask;
import com.revature.revaturetrainingroomplanner.data.model.Room;
import com.revature.revaturetrainingroomplanner.data.model.RoomWithBatchAssignments;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.RoomDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.database.AppDatabase;

import java.util.Arrays;
import java.util.List;

public class RoomRepository {

    private static final String TAG = "RoomRepository";

    private AppDatabase mAppDatabase;
    private RoomDAO mDao;

    public RoomRepository(Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        mDao = (RoomDAO) mAppDatabase.getDAO(Room.class);
    }

    public void insertRoomTask(Room... rooms) {
        Log.d(TAG, "insertCampusTask: inserting " + Arrays.toString(rooms));
        new InsertAsyncTask<>(mDao).execute(rooms);
    }

    public Room retrieveByIDTask(int id) {
        return mDao.getByID(id);
    }

    public LiveData<List<RoomWithBatchAssignments>> retrieveAllTask() {
        return mDao.getAllRooms();
    }

    public void updateTask(Room... rooms) {
        new UpdateAsyncTask<>(mDao).execute(rooms);
    }

    public void deleteTask(Room... rooms) {
        new DeleteAsyncTask<>(mDao).execute(rooms);
    }

    public void deleteAllTask(Room... rooms) {
        new DeleteAllAsyncTask<>(mDao).execute(rooms);
    }

}
