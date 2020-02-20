package com.revature.revaturetrainingroomplanner.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.Objects;

@Entity(tableName = "skills")
public class Skill implements SortedListAdapter.ViewModel, Parcelable {

    @NonNull
    @ColumnInfo(name = "s_name", index = true)
    @PrimaryKey
    private String skill;

    public Skill() {
    }

    @Ignore
    public Skill(@NonNull String skill) {
        this.skill = skill;
    }

    protected Skill(Parcel in) {
        skill = Objects.requireNonNull(in.readString());
    }

    public static final Creator<Skill> CREATOR = new Creator<Skill>() {
        @Override
        public Skill createFromParcel(Parcel in) {
            return new Skill(in);
        }

        @Override
        public Skill[] newArray(int size) {
            return new Skill[size];
        }
    };

    public String getText() {
        return skill;
    }

    @NonNull
    @Override
    public String toString() {
        return "Skill{" +
                "skill='" + skill + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill1 = (Skill) o;
        return skill.equals(skill1.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skill);
    }

    public void setSkill(@NonNull String skill) {
        this.skill = skill;
    }

    @NonNull
    public String getSkill() {
        return skill;
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T model) {
        if (model instanceof Skill) {
            final Skill other = (Skill) model;
            return other.skill.equals(skill);
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof Skill) {
            final Skill other = (Skill) model;
            return Objects.equals(skill, other.skill);
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(skill);
    }
}
