package com.revature.revaturetrainingroomplanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.CampusWithBatches;
import com.revature.revaturetrainingroomplanner.databinding.CampusWithBatchesRowBinding;

import java.util.Comparator;

public class CampusWithBatchesAdapter extends SortedListAdapter<CampusWithBatches> {

    private static final long USF_ID = 1;
    private static final long UTA_ID = 2;
    private static final long WVU_ID = 3;
    private static final long Reston_ID = 4;

    private Context mContext;
    private OnItemListener mOnItemListener;

    public CampusWithBatchesAdapter(Context context, Comparator<CampusWithBatches> comparator, OnItemListener onItemListener) {
        super(context, CampusWithBatches.class, comparator);
        mContext = context;

        mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    protected ViewHolder<? extends CampusWithBatches> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final CampusWithBatchesRowBinding binding = CampusWithBatchesRowBinding.inflate(inflater, parent, false);
        return new CampusWithBatchesViewHolder(binding, mContext, mOnItemListener);
    }

    public static class CampusWithBatchesViewHolder extends ViewHolder<CampusWithBatches> implements View.OnClickListener {

        private final CampusWithBatchesRowBinding mBinding;
        private OnItemListener mOnItemListener;
        private ImageView mCampusImageView;

        CampusWithBatchesViewHolder(CampusWithBatchesRowBinding binding, Context context, OnItemListener onItemListener) {
            super(binding.getRoot());
            mBinding = binding;
            mOnItemListener = onItemListener;
            mBinding.getRoot().findViewById(R.id.constraintlayout_campus_row).setOnClickListener(this);
            mCampusImageView = mBinding.imgCampusRowIcon;

            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            mBinding.recyclerviewCampusWithBatchesRowBatches.setLayoutManager(layoutManager);
        }

        @Override
        protected void performBind(@NonNull CampusWithBatches item) {
            mBinding.setModel(item);
            long campusID = item.getCampus().getCampus_id();
            if (campusID == USF_ID) {
                mCampusImageView.setImageResource(R.drawable.tampa);
            } else if (campusID == UTA_ID) {
                mCampusImageView.setImageResource(R.drawable.dallas);
            } else if (campusID == Reston_ID) {
                mCampusImageView.setImageResource(R.drawable.reston);
            } else if (campusID == WVU_ID) {
                mCampusImageView.setImageResource(R.drawable.morgantown);
            }
        }

        @Override
        public void onClick(View v) {
            mOnItemListener.onCampusClick(getCurrentItem());
        }
    }

    public interface OnItemListener {
        void onCampusClick(CampusWithBatches campusClicked);
    }
}

