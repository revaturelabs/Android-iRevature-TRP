package com.revature.revaturetrainingroomplanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.Skill;
import com.revature.revaturetrainingroomplanner.data.model.TrainerWithSkills;
import com.revature.revaturetrainingroomplanner.databinding.TrainerRowBinding;

import java.util.Comparator;

public class TrainerWithSkillsAdapter extends SortedListAdapter<TrainerWithSkills> {

    private static final Comparator<Skill> ALPHABETICAL_COMPARATOR_SKILLS = (a, b) -> a.getText().compareTo(b.getText());

    private Context mContext;
    private OnItemListener mOnItemListener;

    public TrainerWithSkillsAdapter(Context context, Comparator<TrainerWithSkills> comparator, OnItemListener onItemListener) {
        super(context, TrainerWithSkills.class, comparator);
        mContext = context;

        mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    protected SortedListAdapter.ViewHolder<? extends TrainerWithSkills> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final TrainerRowBinding binding = TrainerRowBinding.inflate(inflater, parent, false);

        return new TrainerWithSkillsViewHolder(binding, mContext, mOnItemListener);
    }

    public static class TrainerWithSkillsViewHolder extends ViewHolder<TrainerWithSkills> implements View.OnClickListener {

        private final TrainerRowBinding mTrainerRowBinding;
        private OnItemListener mOnItemListener;

        TrainerWithSkillsViewHolder(TrainerRowBinding trainerRowBinding, Context context, OnItemListener onItemListener) {
            super(trainerRowBinding.getRoot());
            mTrainerRowBinding = trainerRowBinding;
            mTrainerRowBinding.recyclerviewTrainerRowSkills.setLayoutManager(new GridLayoutManager(context, 3));
            mOnItemListener = onItemListener;
            mTrainerRowBinding.getRoot().findViewById(R.id.framelayout_trainer_row).setOnClickListener(this);

            mTrainerRowBinding.getRoot().setOnClickListener(this);
            mTrainerRowBinding.recyclerviewTrainerRowSkills.setOnClickListener(this);
        }

        @Override
        protected void performBind(@NonNull TrainerWithSkills item) {
            mTrainerRowBinding.setModel(item.getTrainer());
            item.getTrainer().getSkillsAdapter().edit()
                    .replaceAll(item.getSkills())
                    .commit();
        }

        @Override
        public void onClick(View v) {
            mOnItemListener.onTrainerClick(getCurrentItem());
        }
    }

    public interface OnItemListener {
        void onTrainerClick(TrainerWithSkills trainerClicked);
    }
}
