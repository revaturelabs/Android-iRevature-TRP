package com.revature.revaturetrainingroomplanner.data.persistence.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.revature.revaturetrainingroomplanner.data.async.DeleteAllAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.DeleteAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.InsertAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.UpdateAsyncTask;
import com.revature.revaturetrainingroomplanner.data.model.BatchAssignment;
import com.revature.revaturetrainingroomplanner.data.model.Building;
import com.revature.revaturetrainingroomplanner.data.model.Campus;
import com.revature.revaturetrainingroomplanner.data.model.Room;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.CampusDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.database.AppDatabase;
import com.revature.revaturetrainingroomplanner.data.requests.ServiceGenerator;
import com.revature.revaturetrainingroomplanner.data.requests.TRPAPI;
import com.revature.revaturetrainingroomplanner.data.requests.responses.LocationsGETResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampusRepository {

    private static final String TAG = "CampusRepository";

    private AppDatabase mAppDatabase;
    private CampusDAO mDao;
    private Context mContext;

    public CampusRepository(Context context) {
        mContext = context;
        mAppDatabase = AppDatabase.getInstance(context);
        mDao = (CampusDAO) mAppDatabase.getDAO(Campus.class);
    }

    public void insertCampusTask(Campus... campuses) {
        Log.d(TAG, "insertCampusTask: inserting " + Arrays.toString(campuses));
        new InsertAsyncTask<>(mDao).execute(campuses);
    }

    public LiveData<Campus> retrieveByIDTask(int id) {
        return mDao.getByID(id);
    }

    public LiveData<List<Campus>> retrieveAllTask() {
        return mDao.getAll();
    }

    public void updateTask(Campus... campuses) {
        new UpdateAsyncTask<>(mDao).execute(campuses);
    }

    public void deleteTask(Campus... campuses) {
        new DeleteAsyncTask<>(mDao).execute(campuses);
    }

    public void deleteAllTask(Campus... campuses) {
        new DeleteAllAsyncTask<>(mDao).execute(campuses);
    }

    public void retrieveCampusesFromAPI() {
        TRPAPI api = ServiceGenerator.getAPI();

        Call<LocationsGETResponse> responseCall = api.getLocations();

        Log.d(TAG, "retrieveCampusesFromAPI: requesting campuses from API...");

        responseCall.enqueue(new Callback<LocationsGETResponse>() {
            @Override
            public void onResponse(@NonNull Call<LocationsGETResponse> call, @NonNull Response<LocationsGETResponse> response) {
                Log.d(TAG, "onResponse: server response: " + response.toString());
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: " + Objects.requireNonNull(response.body()).toString());

                    List<Campus> campuses = response.body().getLocations();

                    if (campuses != null) {
                        BuildingRepository buildingRepository = new BuildingRepository(mContext);
                        RoomRepository roomRepository = new RoomRepository(mContext);
                        BatchAssignmentRepository batchAssignmentRepository = new BatchAssignmentRepository(mContext);

                        List<Building> buildings;
                        List<Room> rooms;
                        List<BatchAssignment> batchAssignments;

                        for (Campus campus : campuses) {

                            buildings = campus.getBuildings();

                            if (buildings != null) {

                                for (Building building : buildings) {

                                    building.setCampus_id(campus.getCampus_id());
                                    rooms = building.getRooms();

                                    if (rooms != null) {

                                        for (Room room : rooms) {

                                            room.setBuilding_id(building.getBuilding_id());
                                            room.setCampus_id(campus.getCampus_id());
                                            batchAssignments = room.getBatches_assigned();

                                            if (batchAssignments != null) {

                                                for (BatchAssignment batchAssignment: batchAssignments) {
                                                    batchAssignment.setRoom_id(room.getRoom_id());
                                                }

                                                batchAssignmentRepository.insertBatchAssignmentTask(batchAssignments.toArray(new BatchAssignment[0]));
                                            }
                                        }

                                        roomRepository.insertRoomTask(rooms.toArray(new Room[0]));

                                    }
                                }

                                buildingRepository.insertBuildingTask(buildings.toArray(new Building[0]));
                            }
                        }

                        insertCampusTask(campuses.toArray(new Campus[0]));
                    } else {
                        try {
                            Log.d(TAG, "onResponse:  " + Objects.requireNonNull(response.errorBody()).string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<LocationsGETResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
    
}
