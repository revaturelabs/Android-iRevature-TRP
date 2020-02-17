package com.revature.revaturetrainingroomplanner.data.model;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

//@Entity(tableName = "campuses")
public class Campus implements SortedListAdapter.ViewModel {

//    @ColumnInfo(name = "c_id")
//    @PrimaryKey(autoGenerate = true)
    private long mId;

//    @ColumnInfo(name = "c_name")
    private final String mText;

    public Campus(String text) {
        mText = text;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public long getId() {
        return mId;
    }

    public String getText() {
        return mText;
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T model) {
        if (model instanceof Campus) {
            final Campus other = (Campus) model;
            return other.mId == mId;
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof Campus) {
            final Campus other = (Campus) model;
            return mText != null ? mText.equals(other.mText) : other.mText == null;
        }
        return false;
    }

}