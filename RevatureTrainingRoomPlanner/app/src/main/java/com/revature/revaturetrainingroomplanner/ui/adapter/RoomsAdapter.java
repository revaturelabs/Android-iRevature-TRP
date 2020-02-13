package com.revature.revaturetrainingroomplanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.data.model.BatchModel;
import com.revature.revaturetrainingroomplanner.data.model.RoomModel;
import com.revature.revaturetrainingroomplanner.databinding.BatchRowBinding;
import com.revature.revaturetrainingroomplanner.databinding.RoomRowBinding;
import com.revature.revaturetrainingroomplanner.ui.adapter.viewholder.BatchViewHolder;
import com.revature.revaturetrainingroomplanner.ui.adapter.viewholder.RoomViewHolder;

import java.util.Comparator;

public class RoomsAdapter extends SortedListAdapter<RoomModel> {

    public RoomsAdapter(Context context, Comparator<RoomModel> comparator) {
        super(context, RoomModel.class, comparator);
    }

    @Override
    protected SortedListAdapter.ViewHolder<? extends RoomModel> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final RoomRowBinding binding = RoomRowBinding.inflate(inflater, parent, false);
        return new RoomViewHolder(binding);
    }

}