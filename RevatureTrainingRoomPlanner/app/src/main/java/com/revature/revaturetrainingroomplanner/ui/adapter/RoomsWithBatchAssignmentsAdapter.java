package com.revature.revaturetrainingroomplanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.RoomWithBatchAssignments;
import com.revature.revaturetrainingroomplanner.databinding.RoomRowBinding;

import java.util.Comparator;

public class RoomsWithBatchAssignmentsAdapter extends SortedListAdapter<RoomWithBatchAssignments> {

    private OnItemListener mOnItemListener;

    public RoomsWithBatchAssignmentsAdapter(Context context, Comparator<RoomWithBatchAssignments> comparator, OnItemListener onItemListener) {
        super(context, RoomWithBatchAssignments.class, comparator);
        mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    protected SortedListAdapter.ViewHolder<? extends RoomWithBatchAssignments> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final RoomRowBinding binding = RoomRowBinding.inflate(inflater, parent, false);
        return new RoomViewHolder(binding, mOnItemListener);
    }

    public static class RoomViewHolder extends ViewHolder<RoomWithBatchAssignments> implements View.OnClickListener {

        private final RoomRowBinding mBinding;
        private OnItemListener mOnItemListener;

        RoomViewHolder(RoomRowBinding binding, OnItemListener onItemListener) {
            super(binding.getRoot());
            mBinding = binding;
            mOnItemListener = onItemListener;
            mBinding.getRoot().findViewById(R.id.constraintLayout_room_row).setOnClickListener(this);

            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        protected void performBind(@NonNull RoomWithBatchAssignments item) {
            mBinding.setRoom(item);
        }

        @Override
        public void onClick(View v) {
            mOnItemListener.onRoomClick(getCurrentItem());
        }
    }

    public interface OnItemListener {
        void onRoomClick(RoomWithBatchAssignments roomClicked);
    }
}