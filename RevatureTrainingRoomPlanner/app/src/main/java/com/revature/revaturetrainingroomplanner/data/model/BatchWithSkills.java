package com.revature.revaturetrainingroomplanner.data.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.List;

public class BatchWithSkills implements SortedListAdapter.ViewModel {

    @Embedded
    private Batch batch;

    @Relation(
            parentColumn = "ba_id",
            entityColumn = "s_name",
            associateBy = @Junction(BatchSkillCrossRef.class)
    )
    private List<Skill> skills;

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T model) {
        if (model instanceof BatchWithSkills) {
            final BatchWithSkills other = (BatchWithSkills) model;
            return other.getBatch().getBatch_id() == batch.getBatch_id();
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof BatchWithSkills) {
            final BatchWithSkills other = (BatchWithSkills) model;


            return (batch.isContentTheSameAs(other.getBatch()) && skills.equals(other.getSkills()));
        }
        return false;
    }
}
