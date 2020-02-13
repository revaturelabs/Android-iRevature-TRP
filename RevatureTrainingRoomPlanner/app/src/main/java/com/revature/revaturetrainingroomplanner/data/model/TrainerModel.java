package com.revature.revaturetrainingroomplanner.data.model;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

public class TrainerModel implements SortedListAdapter.ViewModel {
    
    private final long mId;
    private final String mText;

    public TrainerModel(long id, String text) {
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
    public <T> boolean isSameModelAs(@NonNull T model) {
        if (model instanceof TrainerModel) {
            final TrainerModel other = (TrainerModel) model;
            return other.mId == mId;
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof TrainerModel) {
            final TrainerModel other = (TrainerModel) model;
            return mText != null ? mText.equals(other.mText) : other.mText == null;
        }
        return false;
    }
}
