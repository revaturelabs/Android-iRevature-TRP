package com.revature.revaturetrainingroomplanner.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

@Entity(tableName = "rooms")
public class Room implements SortedListAdapter.ViewModel {

    @ColumnInfo(name = "r_id")
    @PrimaryKey(autoGenerate = true)
    private long mId;

    @ColumnInfo(name = "r_name")
    private final String mText;

    public Room(String text) {
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
        if (model instanceof Room) {
            final Room other = (Room) model;
            return other.mId == mId;
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof Room) {
            final Room other = (Room) model;
            return mText != null ? mText.equals(other.mText) : other.mText == null;
        }
        return false;
    }

}
