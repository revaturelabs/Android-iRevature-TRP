package com.revature.revaturetrainingroomplanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.BatchWithSkills;
import com.revature.revaturetrainingroomplanner.databinding.BatchRowBinding;

import java.util.Comparator;

public class BatchWithSkillsAdapter extends SortedListAdapter<BatchWithSkills> {

    private Context mContext;
    private OnItemListener mOnItemListener;

    public BatchWithSkillsAdapter(Context context, Comparator<BatchWithSkills> comparator, OnItemListener onItemListener) {
        super(context, BatchWithSkills.class, comparator);
        mContext = context;

        mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    protected ViewHolder<? extends BatchWithSkills> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final BatchRowBinding binding = BatchRowBinding.inflate(inflater, parent, false);
        return new BatchWithSkillsViewHolder(binding, mContext, mOnItemListener);
    }

    public static class BatchWithSkillsViewHolder extends ViewHolder<BatchWithSkills> implements View.OnClickListener {

        private final BatchRowBinding mBinding;
        private OnItemListener mOnItemListener;

        BatchWithSkillsViewHolder(BatchRowBinding binding, Context context, OnItemListener onItemListener) {
            super(binding.getRoot());
            mBinding = binding;
            mOnItemListener = onItemListener;
            mBinding.getRoot().findViewById(R.id.constraintlayout_batchrow).setOnClickListener(this);

            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mBinding.recyclerviewBatchRowSkills.setLayoutManager(layoutManager);
        }

        @Override
        protected void performBind(@NonNull BatchWithSkills item) {
            mBinding.setModel(item.getBatch());
            item.getBatch().getSkillsAdapter().edit()
                    .replaceAll(item.getSkills())
                    .commit();
        }

        @Override
        public void onClick(View v) {
            mOnItemListener.onBatchClick(getCurrentItem());
        }
    }

    public interface OnItemListener {
        void onBatchClick(BatchWithSkills batchClicked);
    }
}

