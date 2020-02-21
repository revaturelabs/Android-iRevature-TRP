package com.revature.revaturetrainingroomplanner.data.async;

import android.os.AsyncTask;
import android.util.Log;

import com.revature.revaturetrainingroomplanner.data.persistence.dao.BaseDAO;

import java.util.Arrays;

public class InsertAsyncTask<T, DAOT extends BaseDAO<T>> extends AsyncTask<T, Void, Void> {

    private static final String TAG = "InsertAsyncTask";

    private DAOT mDAO;

    public InsertAsyncTask(DAOT dao) {
        mDAO = dao;
    }

    @SafeVarargs
    @Override
    protected final Void doInBackground(T... objects) {
        Log.d(TAG, "doInBackground: insert " + Arrays.toString(objects));
        mDAO.insert(objects);
        return null;
    }

}
