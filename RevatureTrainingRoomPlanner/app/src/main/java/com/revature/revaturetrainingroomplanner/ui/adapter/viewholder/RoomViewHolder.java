package com.revature.revaturetrainingroomplanner.ui.adapter.viewholder;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.data.model.BatchModel;
import com.revature.revaturetrainingroomplanner.data.model.RoomModel;
import com.revature.revaturetrainingroomplanner.databinding.BatchRowBinding;
import com.revature.revaturetrainingroomplanner.databinding.RoomRowBinding;

public class RoomViewHolder extends SortedListAdapter.ViewHolder<RoomModel> {

    private final RoomRowBinding mBinding;

    public RoomViewHolder(RoomRowBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    @Override
    protected void performBind(@NonNull RoomModel item) {
        mBinding.setModel(item);
    }
}
