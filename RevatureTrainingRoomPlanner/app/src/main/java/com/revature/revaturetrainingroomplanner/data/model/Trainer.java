package com.revature.revaturetrainingroomplanner.data.model;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

//@Entity(tableName = "trainers")
public class Trainer implements SortedListAdapter.ViewModel {

//    @ColumnInfo(name = "t_id")
//    @PrimaryKey(autoGenerate = true)
    private long mId;

//    @ColumnInfo(name = "t_name")
    private final String mText;

    public Trainer(String text) {
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
        if (model instanceof Trainer) {
            final Trainer other = (Trainer) model;
            return other.mId == mId;
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof Trainer) {
            final Trainer other = (Trainer) model;
            return mText != null ? mText.equals(other.mText) : other.mText == null;
        }
        return false;
    }
}
