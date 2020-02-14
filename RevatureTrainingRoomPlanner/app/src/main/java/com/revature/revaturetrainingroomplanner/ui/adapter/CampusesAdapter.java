package com.revature.revaturetrainingroomplanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.data.model.CampusModel;
import com.revature.revaturetrainingroomplanner.databinding.CampusRowBinding;

import java.util.Comparator;

import androidx.annotation.NonNull;

public class CampusesAdapter extends SortedListAdapter<CampusModel> {

    private OnItemListener mOnItemListener;

    public CampusesAdapter(Context context, Comparator<CampusModel> comparator, OnItemListener onItemListener) {
        super(context, CampusModel.class, comparator);
        mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    protected ViewHolder<? extends CampusModel> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final CampusRowBinding binding = CampusRowBinding.inflate(inflater, parent, false);
        return new CampusViewHolder(binding, mOnItemListener);
    }

    public static class CampusViewHolder extends ViewHolder<CampusModel> implements View.OnClickListener {

        private final CampusRowBinding mBinding;
        private OnItemListener mOnItemListener;

        CampusViewHolder(CampusRowBinding binding, OnItemListener onItemListener) {
            super(binding.getRoot());
            mBinding = binding;
            mOnItemListener = onItemListener;

            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        protected void performBind(@NonNull CampusModel item) {
            mBinding.setModel(item);
        }

        @Override
        public void onClick(View v) {
            mOnItemListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }
}

