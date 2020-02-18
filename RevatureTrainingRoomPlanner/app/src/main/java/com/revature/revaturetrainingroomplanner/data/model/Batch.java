package com.revature.revaturetrainingroomplanner.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

@Entity(tableName = "batches")
public class Batch implements SortedListAdapter.ViewModel{

    @ColumnInfo(name = "b_id")
    @PrimaryKey(autoGenerate = true)
    private long batch_id;

    @ColumnInfo(name = "b_name")
    private String batch_name;

    @ColumnInfo(name = "b_start_date")
    private String start_date;

    @ColumnInfo(name = "b_end_date")
    private String end_date;

    @ColumnInfo(name = "b_is_assigned")
    private boolean is_assigned;

    @ColumnInfo(name = "b_associates")
    private int associates;

//    @ColumnInfo(name = "b_skills_required")
//    private List<Skill> skills_required;

    public Batch(String batch_name) {
        this.batch_name = batch_name;
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T model) {
        if (model instanceof Batch) {
            final Batch other = (Batch) model;
            return other.batch_id == batch_id;
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof Batch) {
            final Batch other = (Batch) model;
            return batch_name != null ? batch_name.equals(other.batch_name) : other.batch_name == null;
        }
        return false;
    }

    public long getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(long batch_id) {
        this.batch_id = batch_id;
    }

    public String getBatch_name() {
        return batch_name;
    }

    public void setBatch_name(String batch_name) {
        this.batch_name = batch_name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public boolean isIs_assigned() {
        return is_assigned;
    }

    public void setIs_assigned(boolean is_assigned) {
        this.is_assigned = is_assigned;
    }

    public int getAssociates() {
        return associates;
    }

    public void setAssociates(int associates) {
        this.associates = associates;
    }

//    public List<Skill> getSkills_required() {
//        return skills_required;
//    }
//
//    public void setSkills_required(List<Skill> skills_required) {
//        this.skills_required = skills_required;
//    }
}