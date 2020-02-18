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

//    @ColumnInfo(name = "b_start_date")
//    private String start_date;
//
//    @ColumnInfo(name = "b_end_date")
//    private String end_date;
//
//    @ColumnInfo(name = "b_is_assigned")
//    private boolean is_assigned;
//
//    @ColumnInfo(name = "b_associates")
//    private int associates;
//
//    @ColumnInfo(name = "b_skills_required")
//    private List<Skill> skills_required;

    public Batch(String name) {
        batch_name = name;
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T model) {
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
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
}