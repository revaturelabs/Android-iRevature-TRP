package com.revature.revaturetrainingroomplanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.BuildingWithRooms;
import com.revature.revaturetrainingroomplanner.databinding.BuildingRowBinding;

import java.util.Comparator;

public class BuildingWithRoomsAdapter extends SortedListAdapter<BuildingWithRooms> {

    private Context mContext;
    private OnItemListener mOnItemListener;

    public BuildingWithRoomsAdapter(Context context, Comparator<BuildingWithRooms> comparator, OnItemListener onItemListener) {
        super(context, BuildingWithRooms.class, comparator);
        mContext = context;

        mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    protected ViewHolder<? extends BuildingWithRooms> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final BuildingRowBinding binding = BuildingRowBinding.inflate(inflater, parent, false);
        return new BuildingWithRoomsViewHolder(binding, mContext, mOnItemListener);
    }

    public static class BuildingWithRoomsViewHolder extends ViewHolder<BuildingWithRooms> implements View.OnClickListener {

        private final BuildingRowBinding mBinding;
        private OnItemListener mOnItemListener;

        BuildingWithRoomsViewHolder(BuildingRowBinding binding, Context context, OnItemListener onItemListener) {
            super(binding.getRoot());
            mBinding = binding;
            mOnItemListener = onItemListener;
            mBinding.getRoot().findViewById(R.id.constraintlayout_building_row).setOnClickListener(this);

            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            mBinding.recyclerviewBuildingRowRooms.setLayoutManager(layoutManager);
        }

        @Override
        protected void performBind(@NonNull BuildingWithRooms item) {
            mBinding.setModel(item.getBuilding());
        }

        @Override
        public void onClick(View v) {
            mOnItemListener.onBuildingClick(getCurrentItem());
        }
    }

    public interface OnItemListener {
        void onBuildingClick(BuildingWithRooms buildingClicked);
    }
}

