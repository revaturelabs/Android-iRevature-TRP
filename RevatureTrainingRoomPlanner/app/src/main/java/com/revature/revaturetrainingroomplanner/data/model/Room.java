package com.revature.revaturetrainingroomplanner.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.List;

@Entity(tableName = "rooms")
public class Room implements SortedListAdapter.ViewModel {

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
    private List<BatchAssignment> batchAssignments;

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
        this.batchAssignments = batchAssignments;
    }

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

    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    public List<BatchAssignment> getBatchAssignments() {
        return batchAssignments;
    }

    public void setBatchAssignments(List<BatchAssignment> batchAssignments) {
        this.batchAssignments = batchAssignments;
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
                ", batchAssignments=" + batchAssignments +
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

}
