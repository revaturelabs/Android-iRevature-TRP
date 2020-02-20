package com.revature.revaturetrainingroomplanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.data.model.Skill;
import com.revature.revaturetrainingroomplanner.databinding.SkillRowBinding;

import java.util.Comparator;

public class SkillsAdapter extends SortedListAdapter<Skill> {

    public SkillsAdapter(Context context, Comparator<Skill> comparator) {
        super(context, Skill.class, comparator);
    }

    @NonNull
    @Override
    protected SortedListAdapter.ViewHolder<? extends Skill> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final SkillRowBinding binding = SkillRowBinding.inflate(inflater, parent, false);
        return new SkillViewHolder(binding);
    }

    public static class SkillViewHolder extends ViewHolder<Skill> {

        private final SkillRowBinding mBinding;

        SkillViewHolder(SkillRowBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

        }

        @Override
        protected void performBind(@NonNull Skill item) {
            mBinding.setModel(item);
        }

    }

}