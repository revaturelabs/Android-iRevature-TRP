package com.revature.revaturetrainingroomplanner.data.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.BaseDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.CampusDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.repository.BuildingRepository;
import com.revature.revaturetrainingroomplanner.data.persistence.repository.TrainerRepository;

import java.util.List;
import java.util.Objects;

@Entity(tableName = "rooms")
public class Room implements SortedListAdapter.ViewModel, Parcelable {

    @ColumnInfo(name = "r_id")
    @PrimaryKey(autoGenerate = true)
    private long room_id;

    @ColumnInfo(name = "r_name")
    private String room_name;

    @ColumnInfo(name = "r_occupancy")
    private int occupancy;

    @ForeignKey(entity = Building.class, parentColumns = "bu_id", childColumns = "bu_id")
    private int building_id;

    @Ignore
    private List<BatchAssignment> batches_assigned;

    public Room() {
    }

    @Ignore
    public Room(String room_name) {
        this.room_name = room_name;
    }

    @Ignore
    public Room(long room_id, String room_name, int occupancy, int building_id, List<BatchAssignment> batchAssignments) {
        this.room_id = room_id;
        this.room_name = room_name;
        this.occupancy = occupancy;
        this.building_id = building_id;
        this.batches_assigned = batchAssignments;
    }

    protected Room(Parcel in) {
        room_id = in.readLong();
        room_name = in.readString();
        occupancy = in.readInt();
        building_id = in.readInt();
        batches_assigned = in.createTypedArrayList(BatchAssignment.CREATOR);
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getRoom_name() {
        return room_name;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public int getBuilding_id() {
        return building_id;
    }

    public String getBuilding_idString() {
        return Integer.toString(building_id);
    }


    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    public List<BatchAssignment> getBatches_assigned() {
        return batches_assigned;
    }

    public void setBatches_assigned(List<BatchAssignment> batches_assigned) {
        this.batches_assigned = batches_assigned;
    }

    public String getText() {
        return room_name;
    }

    @NonNull
    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + room_id +
                ", room_name='" + room_name + '\'' +
                ", occupancy=" + occupancy +
                ", building_id=" + building_id +
                ", batches_assigned=" + batches_assigned +
                '}';
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T model) {
        if (model instanceof Room) {
            final Room other = (Room) model;
            return other.room_id == room_id;
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof Room) {
            final Room other = (Room) model;
            return room_name != null ? room_name.equals(other.room_name) : other.room_name == null;
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(room_id);
        dest.writeString(room_name);
        dest.writeInt(occupancy);
        dest.writeInt(building_id);
        dest.writeTypedList(batches_assigned);
    }
}
