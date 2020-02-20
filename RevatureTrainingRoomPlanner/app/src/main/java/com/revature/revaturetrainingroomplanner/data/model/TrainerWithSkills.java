package com.revature.revaturetrainingroomplanner.data.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.List;

public class TrainerWithSkills implements SortedListAdapter.ViewModel {

    @Embedded
    private Trainer trainer;

    @Relation(
            parentColumn = "t_id",
            entityColumn = "s_name",
            associateBy = @Junction(TrainerSkillCrossRef.class)
    )
    private List<Skill> skills;

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T model) {
        if (model instanceof TrainerWithSkills) {
            final TrainerWithSkills other = (TrainerWithSkills) model;
            return other.getTrainer().getTrainer_id() == trainer.getTrainer_id();
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof TrainerWithSkills) {
            final TrainerWithSkills other = (TrainerWithSkills) model;


            return (trainer.isContentTheSameAs(other.getTrainer()) && skills.equals(other.getSkills()));
        }
        return false;
    }
}
