package com.revature.revaturetrainingroomplanner.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.List;

@Entity(tableName = "trainers")
public class Trainer implements SortedListAdapter.ViewModel, Parcelable {

    @ColumnInfo(name = "t_id")
    @PrimaryKey(autoGenerate = true)
    private long trainer_id;

    @ColumnInfo(name = "t_name")
    private String trainer_name;

    @ColumnInfo(name = "t_email")
    private String trainer_email;

    @ColumnInfo(name = "t_location")
    private String trainer_location;

    @ColumnInfo(name = "t_pic_url")
    private String trainer_profile_picture_url;

    @Ignore
    private List<Skill> skills;

    @Ignore
    private List<String> trainer_skills;

    public Trainer() {
    }

    @Ignore
    public Trainer(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    @Ignore
    protected Trainer(Parcel in) {
        trainer_id = in.readLong();
        trainer_name = in.readString();
        trainer_email = in.readString();
        trainer_location = in.readString();
        trainer_profile_picture_url = in.readString();
        skills = in.createTypedArrayList(Skill.CREATOR);
        trainer_skills = in.createStringArrayList();
    }

    public static final Creator<Trainer> CREATOR = new Creator<Trainer>() {
        @Override
        public Trainer createFromParcel(Parcel in) {
            return new Trainer(in);
        }

        @Override
        public Trainer[] newArray(int size) {
            return new Trainer[size];
        }
    };

    public long getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(long trainer_id) {
        this.trainer_id = trainer_id;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public String getTrainer_email() {
        return trainer_email;
    }

    public void setTrainer_email(String trainer_email) {
        this.trainer_email = trainer_email;
    }

    public String getTrainer_location() {
        return trainer_location;
    }

    public void setTrainer_location(String trainer_location) {
        this.trainer_location = trainer_location;
    }

    public String getTrainer_profile_picture_url() {
        return trainer_profile_picture_url;
    }

    public void setTrainer_profile_picture_url(String trainer_profile_picture_url) {
        this.trainer_profile_picture_url = trainer_profile_picture_url;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<String> getTrainer_skills() {
        return trainer_skills;
    }

    public void setTrainer_skills(List<String> trainer_skills) {
        this.trainer_skills = trainer_skills;
    }

    public String getText() {
        return trainer_name;
    }

    @NonNull
    @Override
    public String toString() {
        return "Trainer{" +
                "trainer_id=" + trainer_id +
                ", trainer_name='" + trainer_name + '\'' +
                '}';
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T model) {
        if (model instanceof Trainer) {
            final Trainer other = (Trainer) model;
            return other.trainer_id == trainer_id;
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof Trainer) {
            final Trainer other = (Trainer) model;
            return trainer_name != null ? trainer_name.equals(other.trainer_name) : other.trainer_name == null;
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(trainer_id);
        dest.writeString(trainer_name);
        dest.writeString(trainer_email);
        dest.writeString(trainer_location);
        dest.writeString(trainer_profile_picture_url);
        dest.writeTypedList(skills);
        dest.writeStringList(trainer_skills);
    }
}
