package com.revature.revaturetrainingroomplanner.data.async;

import android.os.AsyncTask;
import android.util.Log;

import com.revature.revaturetrainingroomplanner.data.persistence.dao.BaseDAO;

public class DeleteAsyncTask<T, DAOT extends BaseDAO<T>> extends AsyncTask<T, Void, Void> {

    private static final String TAG = "DeleteAsyncTask";

    private DAOT mDAO;

    public DeleteAsyncTask(DAOT dao) {
        mDAO = dao;
    }

    @SafeVarargs
    @Override
    protected final Void doInBackground(T... objects) {
        Log.d(TAG, "doInBackground: thread: " + Thread.currentThread().getName());
        mDAO.delete(objects);
        return null;
    }

}
