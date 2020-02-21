package com.revature.revaturetrainingroomplanner.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;

@Entity(indices = {@Index("ba_id"), @Index("s_name")}, primaryKeys = {"ba_id", "s_name"})
public class
BatchSkillCrossRef {

    private long ba_id;

     @NonNull
    private String s_name;


    public BatchSkillCrossRef() {
    }

    @Ignore
    public BatchSkillCrossRef(long t_id, @NonNull String s_name) {
        this.ba_id = t_id;
        this.s_name = s_name;
    }

    public long getBa_id() {
        return ba_id;
    }

    public void setBa_id(long ba_id) {
        this.ba_id = ba_id;
    }

    @NonNull
    public String getS_name() {
        return s_name;
    }

    public void setS_name(@NonNull String s_name) {
        this.s_name = s_name;
    }

    @NonNull
    @Override
    public String toString() {
        return "BatchSkillCrossRef{" +
                "ba_id=" + ba_id +
                ", s_name='" + s_name + '\'' +
                '}';
    }
}
