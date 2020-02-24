package com.revature.revaturetrainingroomplanner.data.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.List;

public class BuildingWithRooms implements SortedListAdapter.ViewModel {

    @Embedded
    private Building building;

    @Relation(
            parentColumn = "bu_id",
            entityColumn = "bu_id",
            entity = Room.class
    )
    private List<RoomWithBatchAssignments> rooms;

    @Ignore
    private boolean roomsVisible;

    public BuildingWithRooms() {
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T model) {
        if (model instanceof BuildingWithRooms) {
            final BuildingWithRooms other = (BuildingWithRooms) model;
            return other.getBuilding().getBuilding_id() == building.getBuilding_id();
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof BuildingWithRooms) {
            final BuildingWithRooms other = (BuildingWithRooms) model;


            return (building.isContentTheSameAs(other.getBuilding()) && rooms.equals(other.getRooms()));
        }
        return false;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<RoomWithBatchAssignments> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomWithBatchAssignments> rooms) {
        this.rooms = rooms;
    }

    public boolean isRoomsVisible() {
        return roomsVisible;
    }

    public void setRoomsVisible(boolean roomsVisible) {
        this.roomsVisible = roomsVisible;
    }
}
