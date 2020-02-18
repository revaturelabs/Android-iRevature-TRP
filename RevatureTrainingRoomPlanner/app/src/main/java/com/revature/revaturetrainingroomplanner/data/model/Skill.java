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
    @ColumnInfo(name = "s_name")
    @PrimaryKey
    private String s_name;

    public Skill() {
    }

    @Ignore
    public Skill(@NonNull String s_name) {
        this.s_name = s_name;
    }

    protected Skill(Parcel in) {
        s_name = Objects.requireNonNull(in.readString());
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
        return s_name;
    }

    @NonNull
    @Override
    public String toString() {
        return "Skill{" +
                "s_name='" + s_name + '\'' +
                '}';
    }

    public void setS_name(@NonNull String s_name) {
        this.s_name = s_name;
    }

    @NonNull
    public String getS_name() {
        return s_name;
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T model) {
        if (model instanceof Skill) {
            final Skill other = (Skill) model;
            return other.s_name.equals(s_name);
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof Skill) {
            final Skill other = (Skill) model;
            return Objects.equals(s_name, other.s_name);
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(s_name);
    }
}
