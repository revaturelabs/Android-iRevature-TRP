package com.revature.revaturetrainingroomplanner.ui.adapter.viewholder;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.data.model.BatchModel;
import com.revature.revaturetrainingroomplanner.data.model.TrainerModel;
import com.revature.revaturetrainingroomplanner.databinding.BatchRowBinding;
import com.revature.revaturetrainingroomplanner.databinding.TrainerRowBinding;

public class TrainerViewHolder extends SortedListAdapter.ViewHolder<TrainerModel> {

    private final TrainerRowBinding mBinding;

    public TrainerViewHolder(TrainerRowBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    @Override
    protected void performBind(@NonNull TrainerModel item) {
        mBinding.setModel(item);
    }

}
