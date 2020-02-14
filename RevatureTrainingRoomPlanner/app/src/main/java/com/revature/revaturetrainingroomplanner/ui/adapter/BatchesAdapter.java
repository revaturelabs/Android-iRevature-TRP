package com.revature.revaturetrainingroomplanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.data.model.BatchModel;
import com.revature.revaturetrainingroomplanner.databinding.BatchRowBinding;

import java.util.Comparator;

public class BatchesAdapter extends SortedListAdapter<BatchModel> {

    private OnItemListener mOnItemListener;

    public BatchesAdapter(Context context, Comparator<BatchModel> comparator, OnItemListener onItemListener) {
        super(context, BatchModel.class, comparator);
        mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    protected ViewHolder<? extends BatchModel> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final BatchRowBinding binding = BatchRowBinding.inflate(inflater, parent, false);
        return new BatchViewHolder(binding, mOnItemListener);
    }

    public static class BatchViewHolder extends ViewHolder<BatchModel> implements View.OnClickListener {

        private final BatchRowBinding mBinding;
        private OnItemListener mOnItemListener;

        BatchViewHolder(BatchRowBinding binding, OnItemListener onItemListener) {
            super(binding.getRoot());
            mBinding = binding;
            mOnItemListener = onItemListener;

            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        protected void performBind(@NonNull BatchModel item) {
            mBinding.setModel(item);
        }

        @Override
        public void onClick(View v) {
            mOnItemListener.onBatchClick(getAdapterPosition());
        }
    }

    public interface OnItemListener {
        void onBatchClick(int position);
    }
}

