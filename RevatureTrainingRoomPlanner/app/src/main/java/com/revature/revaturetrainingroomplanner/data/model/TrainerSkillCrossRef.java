package com.revature.revaturetrainingroomplanner.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;

@Entity(indices = {@Index("t_id"), @Index("s_name")}, primaryKeys = {"t_id", "s_name"})
public class TrainerSkillCrossRef {

    private long t_id;

    private String s_name;

    public TrainerSkillCrossRef() {
    }

    @Ignore
    public TrainerSkillCrossRef(long t_id, String s_name) {
        this.t_id = t_id;
        this.s_name = s_name;
    }

    public long getT_id() {
        return t_id;
    }

    public void setT_id(long t_id) {
        this.t_id = t_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    @NonNull
    @Override
    public String toString() {
        return "TrainerSkillCrossRef{" +
                "t_id=" + t_id +
                ", s_name='" + s_name + '\'' +
                '}';
    }
}
