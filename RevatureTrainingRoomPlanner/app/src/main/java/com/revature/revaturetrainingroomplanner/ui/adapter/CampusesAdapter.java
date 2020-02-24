package com.revature.revaturetrainingroomplanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.Campus;
import com.revature.revaturetrainingroomplanner.databinding.CampusRowBinding;

import java.util.Comparator;

public class CampusesAdapter extends SortedListAdapter<Campus> {

    private OnItemListener mOnItemListener;
    final static String USF_CAMPUS = "USF";
    final static String UTA_CAMPUS = "UTA";
    final static String WVU_CAMPUS = "WVU";
    final static String DC_CAMPUS = "DC";

    public CampusesAdapter(Context context, Comparator<Campus> comparator, OnItemListener onItemListener) {
        super(context, Campus.class, comparator);
        mOnItemListener = onItemListener;

    }

    @NonNull
    @Override
    protected ViewHolder<? extends Campus> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final CampusRowBinding binding = CampusRowBinding.inflate(inflater, parent, false);

        return new CampusViewHolder(binding, mOnItemListener);
    }

    public static class CampusViewHolder extends ViewHolder<Campus> implements View.OnClickListener {

        private final CampusRowBinding mBinding;
        private OnItemListener mOnItemListener;
        private NavController mNavController;

        CampusViewHolder(CampusRowBinding binding, OnItemListener onItemListener) {
            super(binding.getRoot());
            mBinding = binding;
            mOnItemListener = onItemListener;
            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        protected void performBind(@NonNull Campus item) {
            mBinding.setModel(item);
            setCampusImage(item.getCampus_name());

        }

        private void setCampusImage(String campus_name) {
            switch (campus_name){
                case USF_CAMPUS:
                    mBinding.imgCampusRowIcon.setImageResource(R.drawable.tampa);
                    break;
                case UTA_CAMPUS:
                    mBinding.imgCampusRowIcon.setImageResource(R.drawable.dallas);
                    break;
                case WVU_CAMPUS:
                    mBinding.imgCampusRowIcon.setImageResource(R.drawable.morgantown);
                    break;
                case DC_CAMPUS:
                    mBinding.imgCampusRowIcon.setImageResource(R.drawable.reston);
                    break;
            }
        }


        @Override
        public void onClick(View v) {
            mOnItemListener.onCampusClick(getCurrentItem());
        }
    }

    public interface OnItemListener {
        void onCampusClick(Campus campusClicked);
    }

}

