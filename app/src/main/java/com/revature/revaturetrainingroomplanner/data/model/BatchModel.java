package com.revature.revaturetrainingroomplanner.data.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.SortedList;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.Objects;

public class BatchModel implements SortedListAdapter.ViewModel {

    private final long mId;
    private final String mText;

    public BatchModel(long id, String text) {
        mId = id;
        mText = text;
    }

    public long getId() {
        return mId;
    }

    public String getText() {
        return mText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatchModel that = (BatchModel) o;
        return mId == that.mId &&
                Objects.equals(mText, that.mText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mText);
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T model) {
        if (model instanceof BatchModel) {
            final BatchModel other = (BatchModel) model;
            return other.mId == mId;
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof BatchModel) {
            final BatchModel other = (BatchModel) model;
            return mText != null ? mText.equals(other.mText) : other.mText == null;
        }
        return false;
    }
}