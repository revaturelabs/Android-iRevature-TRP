package com.revature.revaturetrainingroomplanner.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

@Entity(tableName = "batch_assignments")
public class BatchAssignment implements SortedListAdapter.ViewModel, Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int batch_assignment_id;

    @ForeignKey(entity = Batch.class, parentColumns = "b_id", childColumns = "b_id")
    private
    int batch_id;

    @ForeignKey(entity = Trainer.class, parentColumns = "t_id", childColumns = "t_id")
    private
    int trainer_id;

    @ForeignKey(entity = Room.class, parentColumns = "r_id", childColumns = "r_id")
    private int room_id;

    @Ignore
    private String start_date;

    @Ignore
    private String end_date;

    public BatchAssignment() {
    }

    @Ignore
    public BatchAssignment(int batch_assignment_id, int batch_id, int trainer_id) {
        this.batch_assignment_id = batch_assignment_id;
        this.batch_id = batch_id;
        this.trainer_id = trainer_id;
    }

    @Ignore
    protected BatchAssignment(Parcel in) {
        batch_assignment_id = in.readInt();
        batch_id = in.readInt();
        trainer_id = in.readInt();
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T model) {
        if (model instanceof BatchAssignment) {
            final BatchAssignment other = (BatchAssignment) model;
            return other.batch_assignment_id == batch_assignment_id;
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof BatchAssignment) {
            final BatchAssignment other = (BatchAssignment) model;
            return ((other.batch_id == batch_id) && (other.trainer_id == trainer_id) && (other.room_id == room_id));
        }
        return false;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(batch_assignment_id);
        dest.writeInt(batch_id);
        dest.writeInt(trainer_id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BatchAssignment> CREATOR = new Creator<BatchAssignment>() {
        @Override
        public BatchAssignment createFromParcel(Parcel in) {
            return new BatchAssignment(in);
        }

        @Override
        public BatchAssignment[] newArray(int size) {
            return new BatchAssignment[size];
        }
    };

    public int getBatch_assignment_id() {
        return batch_assignment_id;
    }

    public void setBatch_assignment_id(int batch_assignment_id) {
        this.batch_assignment_id = batch_assignment_id;
    }

    public int getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(int batch_id) {
        this.batch_id = batch_id;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    @NonNull
    @Override
    public String toString() {
        return "BatchAssignment{" +
                "batch_assignment_id=" + batch_assignment_id +
                ", batch_id=" + batch_id +
                ", trainer_id=" + trainer_id +
                '}';
    }

}
