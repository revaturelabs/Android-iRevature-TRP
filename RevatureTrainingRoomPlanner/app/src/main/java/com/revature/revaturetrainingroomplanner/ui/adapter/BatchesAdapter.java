package com.revature.revaturetrainingroomplanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.Batch;
import com.revature.revaturetrainingroomplanner.databinding.BatchRowBinding;

import java.util.Comparator;

public class BatchesAdapter extends SortedListAdapter<Batch> {

    private OnItemListener mOnItemListener;

    public BatchesAdapter(Context context, Comparator<Batch> comparator, OnItemListener onItemListener) {
        super(context, Batch.class, comparator);
        mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    protected ViewHolder<? extends Batch> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final BatchRowBinding binding = BatchRowBinding.inflate(inflater, parent, false);
        return new BatchViewHolder(binding, mOnItemListener);
    }

    public static class BatchViewHolder extends ViewHolder<Batch> implements View.OnClickListener {

        private final BatchRowBinding mBinding;
        private OnItemListener mOnItemListener;

        BatchViewHolder(BatchRowBinding binding, OnItemListener onItemListener) {
            super(binding.getRoot());
            mBinding = binding;
            mOnItemListener = onItemListener;
            mBinding.getRoot().findViewById(R.id.constraintlayout_batchrow).setOnClickListener(this);
        }

        @Override
        protected void performBind(@NonNull Batch item) {
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

