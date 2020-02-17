package com.revature.revaturetrainingroomplanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.data.model.Room;
import com.revature.revaturetrainingroomplanner.databinding.RoomRowBinding;

import java.util.Comparator;

public class RoomsAdapter extends SortedListAdapter<Room> {

    private OnItemListener mOnItemListener;

    public RoomsAdapter(Context context, Comparator<Room> comparator, OnItemListener onItemListener) {
        super(context, Room.class, comparator);
        mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    protected SortedListAdapter.ViewHolder<? extends Room> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final RoomRowBinding binding = RoomRowBinding.inflate(inflater, parent, false);
        return new RoomViewHolder(binding, mOnItemListener);
    }

    public static class RoomViewHolder extends ViewHolder<Room> implements View.OnClickListener {

        private final RoomRowBinding mBinding;
        private OnItemListener mOnItemListener;

        RoomViewHolder(RoomRowBinding binding, OnItemListener onItemListener) {
            super(binding.getRoot());
            mBinding = binding;
            mOnItemListener = onItemListener;

            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        protected void performBind(@NonNull Room item) {
            mBinding.setModel(item);
        }

        @Override
        public void onClick(View v) {
            mOnItemListener.onRoomClick(getAdapterPosition());
        }
    }

    public interface OnItemListener {
        void onRoomClick(int position);
    }
}