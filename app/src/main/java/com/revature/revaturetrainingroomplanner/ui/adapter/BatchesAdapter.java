package com.revature.revaturetrainingroomplanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.BatchModel;
import com.revature.revaturetrainingroomplanner.databinding.BatchRowBinding;
import com.revature.revaturetrainingroomplanner.ui.adapter.viewholder.BatchViewHolder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BatchesAdapter extends SortedListAdapter<BatchModel> {

    public BatchesAdapter(Context context, Comparator<BatchModel> comparator) {
        super(context, BatchModel.class, comparator);
    }

    @Override
    protected ViewHolder<? extends BatchModel> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final BatchRowBinding binding = BatchRowBinding.inflate(inflater, parent, false);
        return new BatchViewHolder(binding);
    }
}

