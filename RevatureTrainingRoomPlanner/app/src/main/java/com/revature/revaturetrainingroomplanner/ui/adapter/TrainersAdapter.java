package com.revature.revaturetrainingroomplanner.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.Trainer;
import com.revature.revaturetrainingroomplanner.databinding.TrainerRowBinding;

import java.util.Comparator;
import java.util.List;

public class TrainersAdapter extends SortedListAdapter<Trainer> {

    private OnItemListener mOnItemListener;
    public static String currName;
    public static String currEmail;
    public static String currLocation;
    public static String currProfileURL;
    public static List<String> currSkills;

    public TrainersAdapter(Context context, Comparator<Trainer> comparator, OnItemListener onItemListener) {
        super(context, Trainer.class, comparator);
        mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    protected SortedListAdapter.ViewHolder<? extends Trainer> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final TrainerRowBinding binding = TrainerRowBinding.inflate(inflater, parent, false);
        return new TrainerViewHolder(binding, mOnItemListener);
    }

    public static class TrainerViewHolder extends ViewHolder<Trainer> implements View.OnClickListener {

        private final TrainerRowBinding mBinding;
        private OnItemListener mOnItemListener;

        TrainerViewHolder(TrainerRowBinding binding, OnItemListener onItemListener) {
            super(binding.getRoot());
            mBinding = binding;
            mOnItemListener = onItemListener;
            mBinding.getRoot().findViewById(R.id.frameLayout_trainer_row).setOnClickListener(this);

            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        protected void performBind(@NonNull Trainer item) {
            mBinding.setModel(item);
        }

        @Override
        public void onClick(View v) {

            currName = getCurrentItem().getTrainer_name();
            currEmail = getCurrentItem().getTrainer_email();
            currSkills = getCurrentItem().getTrainer_skills();
            currLocation = getCurrentItem().getTrainer_location();
            Log.d("trainer", getCurrentItem().toString());
            currProfileURL = getCurrentItem().getTrainer_profile_picture_url();
            mOnItemListener.onTrainerClick(getAdapterPosition());

        }
    }

    public interface OnItemListener {
        void onTrainerClick(int position);
    }
}
