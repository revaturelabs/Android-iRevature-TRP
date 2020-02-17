//package com.revature.revaturetrainingroomplanner.data.persistence.repository;
//
//import android.content.Context;
//
//import androidx.lifecycle.LiveData;
//
//import com.revature.revaturetrainingroomplanner.data.async.InsertAsyncTask;
//import com.revature.revaturetrainingroomplanner.data.model.Batch;
//import com.revature.revaturetrainingroomplanner.data.persistence.dao.BaseDAO;
//import com.revature.revaturetrainingroomplanner.data.persistence.database.AppDatabase;
//
//import java.util.List;
//
//public class AppRepository<T> {
//
//    private AppDatabase mAppDatabase;
//
//    public AppRepository(Context context) {
//        mAppDatabase = AppDatabase.getInstance(context);
//    }
//
//    public void insertTask(T... objects) {
//        BaseDAO dao = mAppDatabase.getDAO(objects);
//        new InsertAsyncTask<T, BaseDAO<T>>(dao).execute(objects);
//    }
//
//    public LiveData<List<T>> retrieveTask() {
////        return mAppDatabase.getBatchDAO().getBatches();
//        return null;
//    }
//
//    public void updateTask(Batch batch) {
//
//    }
//
//    public void deleteTask() {
//
//    }
//}
