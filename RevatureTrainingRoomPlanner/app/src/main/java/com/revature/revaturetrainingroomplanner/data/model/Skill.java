package com.revature.revaturetrainingroomplanner.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

@Entity(tableName = "skills")
public class Skill implements SortedListAdapter.ViewModel {

    @ColumnInfo(name = "s_id")
    @PrimaryKey(autoGenerate = true)
    private long mId;

    @ColumnInfo(name = "s_name")
    private final String mText;

    public Skill(String text) {
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

    @NonNull
    @Override
    public String toString() {
        return "Skill{" +
                "mId=" + mId +
                ", mText='" + mText + '\'' +
                '}';
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T model) {
        if (model instanceof Skill) {
            final Skill other = (Skill) model;
            return other.mId == mId;
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof Skill) {
            final Skill other = (Skill) model;
            return mText != null ? mText.equals(other.mText) : other.mText == null;
        }
        return false;
    }

}
