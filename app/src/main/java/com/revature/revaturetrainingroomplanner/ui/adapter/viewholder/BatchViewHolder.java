package com.revature.revaturetrainingroomplanner.ui.adapter.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedListAdapterCallback;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.data.model.BatchModel;
import com.revature.revaturetrainingroomplanner.databinding.BatchRowBinding;

public class BatchViewHolder extends SortedListAdapter.ViewHolder<BatchModel> {

    private final BatchRowBinding mBinding;

    public BatchViewHolder(BatchRowBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    @Override
    protected void performBind(@NonNull BatchModel item) {
        mBinding.setModel(item);
    }
}