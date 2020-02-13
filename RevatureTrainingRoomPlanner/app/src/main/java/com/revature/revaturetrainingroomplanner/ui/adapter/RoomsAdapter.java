package com.revature.revaturetrainingroomplanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.data.model.RoomModel;
import com.revature.revaturetrainingroomplanner.databinding.RoomRowBinding;

import java.util.Comparator;

public class RoomsAdapter extends SortedListAdapter<RoomModel> {

    OnItemListener mOnItemListener;

    public RoomsAdapter(Context context, Comparator<RoomModel> comparator, OnItemListener onItemListener) {
        super(context, RoomModel.class, comparator);
        mOnItemListener = onItemListener;
    }

    @Override
    protected SortedListAdapter.ViewHolder<? extends RoomModel> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final RoomRowBinding binding = RoomRowBinding.inflate(inflater, parent, false);
        return new RoomViewHolder(binding, mOnItemListener);
    }

    public static class RoomViewHolder extends ViewHolder<RoomModel> implements View.OnClickListener {

        private final RoomRowBinding mBinding;
        private OnItemListener mOnItemListener;

        public RoomViewHolder(RoomRowBinding binding, OnItemListener onItemListener) {
            super(binding.getRoot());
            mBinding = binding;
            mOnItemListener = onItemListener;

            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        protected void performBind(@NonNull RoomModel item) {
            mBinding.setModel(item);
        }

        @Override
        public void onClick(View v) {
            mOnItemListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }
}