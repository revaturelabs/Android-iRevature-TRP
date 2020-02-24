package com.revature.revaturetrainingroomplanner.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.List;

public class RoomWithBatchAssignments implements SortedListAdapter.ViewModel, Parcelable {

    @Embedded
    private Room room;

    @Relation(
            parentColumn = "r_id",
            entityColumn = "r_id"
    )
    private List<BatchAssignment> batchAssignments;

    public RoomWithBatchAssignments() {
    }

    protected RoomWithBatchAssignments(Parcel in) {
        room = in.readParcelable(Room.class.getClassLoader());
        batchAssignments = in.createTypedArrayList(BatchAssignment.CREATOR);
    }

    public static final Creator<RoomWithBatchAssignments> CREATOR = new Creator<RoomWithBatchAssignments>() {
        @Override
        public RoomWithBatchAssignments createFromParcel(Parcel in) {
            return new RoomWithBatchAssignments(in);
        }

        @Override
        public RoomWithBatchAssignments[] newArray(int size) {
            return new RoomWithBatchAssignments[size];
        }
    };

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<BatchAssignment> getBatchAssignments() {
        return batchAssignments;
    }

    public void setBatchAssignments(List<BatchAssignment> batchAssignments) {
        this.batchAssignments = batchAssignments;
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T model) {
        if (model instanceof RoomWithBatchAssignments) {
            final RoomWithBatchAssignments other = (RoomWithBatchAssignments) model;
            return other.getRoom().getRoom_id() == room.getRoom_id();
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof RoomWithBatchAssignments) {
            final RoomWithBatchAssignments other = (RoomWithBatchAssignments) model;


            return (room.isContentTheSameAs(other.getRoom()) && batchAssignments.equals(other.getBatchAssignments()));
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(room, flags);
        dest.writeTypedList(batchAssignments);
    }
}
