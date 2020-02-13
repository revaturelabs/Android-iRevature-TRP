package com.revature.revaturetrainingroomplanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.data.model.RoomModel;
import com.revature.revaturetrainingroomplanner.data.model.TrainerModel;
import com.revature.revaturetrainingroomplanner.databinding.RoomRowBinding;
import com.revature.revaturetrainingroomplanner.databinding.TrainerRowBinding;
import com.revature.revaturetrainingroomplanner.ui.adapter.viewholder.RoomViewHolder;
import com.revature.revaturetrainingroomplanner.ui.adapter.viewholder.TrainerViewHolder;

import java.util.Comparator;

public class TrainersAdapter extends SortedListAdapter<TrainerModel> {

    public TrainersAdapter(Context context, Comparator<TrainerModel> comparator) {
        super(context, TrainerModel.class, comparator);
    }

    @Override
    protected SortedListAdapter.ViewHolder<? extends TrainerModel> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final TrainerRowBinding binding = TrainerRowBinding.inflate(inflater, parent, false);
        return new TrainerViewHolder(binding);
    }

}
