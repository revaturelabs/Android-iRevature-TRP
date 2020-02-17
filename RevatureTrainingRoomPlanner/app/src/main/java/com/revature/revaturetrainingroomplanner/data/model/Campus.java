package com.revature.revaturetrainingroomplanner.data.model;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

public class Campus implements SortedListAdapter.ViewModel {

    private final long mId;
    private final String mText;

    public Campus(long id, String text) {
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