package com.revature.revaturetrainingroomplanner.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.RoomsWithBatchAssignmentsAdapter;

import java.util.List;

@Entity(tableName = "buildings")
public class Building implements SortedListAdapter.ViewModel, Parcelable {

    @ColumnInfo(name = "bu_id")
    @PrimaryKey
    private long building_id;

    @ColumnInfo(name = "bu_name")
    private String building_name;

    @ForeignKey(entity = Campus.class, parentColumns = "c_id", childColumns = "c_id")
    private long campus_id;

    @Ignore
    private List<Room> rooms;

    @Ignore
    private RoomsWithBatchAssignmentsAdapter roomsWithBatchAssignmentsAdapter;

    public Building() {
    }

    @Ignore
    protected Building(Parcel in) {
        building_id = in.readLong();
        building_name = in.readString();
        campus_id = in.readInt();
    }

    public static final Creator<Building> CREATOR = new Creator<Building>() {
        @Override
        public Building createFromParcel(Parcel in) {
            return new Building(in);
        }

        @Override
        public Building[] newArray(int size) {
            return new Building[size];
        }
    };

    public long getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(long building_id) {
        this.building_id = building_id;
    }

    public String getBuilding_name() {
        if(!building_name.isEmpty())
            return building_name;
        else
            return "idk";
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public long getCampus_id() {
        return campus_id;
    }

    public void setCampus_id(long campus_id) {
        this.campus_id = campus_id;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public RoomsWithBatchAssignmentsAdapter getRoomsWithBatchAssignmentsAdapter() {
        return roomsWithBatchAssignmentsAdapter;
    }

    public void setRoomsWithBatchAssignmentsAdapter(RoomsWithBatchAssignmentsAdapter roomsWithBatchAssignmentsAdapter) {
        this.roomsWithBatchAssignmentsAdapter = roomsWithBatchAssignmentsAdapter;
    }

    @NonNull
    @Override
    public String toString() {
        return "Building{" +
                "building_id=" + building_id +
                ", building_name='" + building_name + '\'' +
                ", campus_id=" + campus_id +
                ", rooms=" + rooms +
                '}';
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T model) {
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(building_id);
        dest.writeString(building_name);
        dest.writeLong(campus_id);
    }
}
