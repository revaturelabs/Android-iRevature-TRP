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

@Entity(tableName = "campuses")
public class Campus implements SortedListAdapter.ViewModel, Parcelable {

    @ColumnInfo(name = "c_id")
    @PrimaryKey(autoGenerate = true)
    private long campus_id;

    @ColumnInfo(name = "c_name")
    private String campus_name;

    @ColumnInfo(name = "c_state")
    private String state;

    @ColumnInfo(name = "c_city")
    private String city;

    @Ignore
    private List<Building> buildings;

    public Campus() {
    }

    @Ignore
    public Campus(String campus_name) {
        this.campus_name = campus_name;
    }

    @Ignore
    protected Campus(Parcel in) {
        campus_id = in.readLong();
        campus_name = in.readString();
        state = in.readString();
        city = in.readString();
        buildings = in.createTypedArrayList(Building.CREATOR);
    }

    public static final Creator<Campus> CREATOR = new Creator<Campus>() {
        @Override
        public Campus createFromParcel(Parcel in) {
            return new Campus(in);
        }

        @Override
        public Campus[] newArray(int size) {
            return new Campus[size];
        }
    };

    public long getCampus_id() {
        return campus_id;
    }

    public void setCampus_id(long campus_id) {
        this.campus_id = campus_id;
    }

    public void setCampus_name(String campus_name) {
        this.campus_name = campus_name;
    }

    public String getCampus_name() {
        return campus_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public String getText() {
        return campus_name;
    }

    @NonNull
    @Override
    public String toString() {
        return "Campus{" +
                "campus_id=" + campus_id +
                ", campus_name='" + campus_name + '\'' +
                '}';
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T model) {
        if (model instanceof Campus) {
            final Campus other = (Campus) model;
            return other.campus_id == campus_id;
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof Campus) {
            final Campus other = (Campus) model;
            return campus_name != null ? campus_name.equals(other.campus_name) : other.campus_name == null;
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(campus_id);
        dest.writeString(campus_name);
        dest.writeString(state);
        dest.writeString(city);
        dest.writeTypedList(buildings);
    }
}