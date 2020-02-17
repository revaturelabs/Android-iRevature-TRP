package com.revature.revaturetrainingroomplanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.data.model.TrainerModel;
import com.revature.revaturetrainingroomplanner.databinding.TrainerRowBinding;

import java.util.Comparator;

public class TrainersAdapter extends SortedListAdapter<TrainerModel> {

    private OnItemListener mOnItemListener;

    public TrainersAdapter(Context context, Comparator<TrainerModel> comparator, OnItemListener onItemListener) {
        super(context, TrainerModel.class, comparator);
        mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    protected SortedListAdapter.ViewHolder<? extends TrainerModel> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final TrainerRowBinding binding = TrainerRowBinding.inflate(inflater, parent, false);
        return new TrainerViewHolder(binding, mOnItemListener);
    }

    public static class TrainerViewHolder extends ViewHolder<TrainerModel> implements View.OnClickListener {

        private final TrainerRowBinding mBinding;
        private OnItemListener mOnItemListener;

        TrainerViewHolder(TrainerRowBinding binding, OnItemListener onItemListener) {
            super(binding.getRoot());
            mBinding = binding;
            mOnItemListener = onItemListener;

            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        protected void performBind(@NonNull TrainerModel item) {
            mBinding.setModel(item);
        }

        @Override
        public void onClick(View v) {
            mOnItemListener.onTrainerClick(getAdapterPosition());
        }
    }

    public interface OnItemListener {
        void onTrainerClick(int position);
    }
}
