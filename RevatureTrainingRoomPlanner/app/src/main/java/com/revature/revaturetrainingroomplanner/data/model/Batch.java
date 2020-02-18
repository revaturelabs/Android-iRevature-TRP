package com.revature.revaturetrainingroomplanner.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.List;

@Entity(tableName = "batches")
public class
Batch implements SortedListAdapter.ViewModel {

    @ColumnInfo(name = "ba_id")
    @PrimaryKey(autoGenerate = true)
    private long batch_id;

    @ColumnInfo(name = "ba_name")
    private String batch_name;

    @ColumnInfo(name = "ba_start_date")
    private String start_date;

    @ColumnInfo(name = "ba_end_date")
    private String end_date;

    @ColumnInfo(name = "ba_is_assigned")
    private boolean is_assigned;

    @ColumnInfo(name = "ba_associates")
    private int associates;

    @Ignore
    private List<Skill> skills;

    @Ignore
    private List<String> skills_required;

    public Batch() {
    }

    @Ignore
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

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<String> getSkills_required() {
        return skills_required;
    }

    public void setSkills_required(List<String> skills_required) {
        this.skills_required = skills_required;
    }

    @NonNull
    @Override
    public String toString() {
        return "Batch{" +
                "batch_id=" + batch_id +
                ", batch_name='" + batch_name + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", is_assigned=" + is_assigned +
                ", associates=" + associates +
                ", skills_required=" + skills_required +
                '}';
    }
}