package com.revature.revaturetrainingroomplanner.data.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.List;

public class CampusWithBatches implements SortedListAdapter.ViewModel {

    @Embedded
    private Campus campus;

    @Relation(
            parentColumn = "c_id",
            entityColumn = "c_id",
            entity = Batch.class
    )
    private List<BatchWithSkills> batchWithSkills;

    @Ignore
    private boolean batchesVisible;

    public CampusWithBatches() {
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T model) {
        if (model instanceof CampusWithBatches) {
            final CampusWithBatches other = (CampusWithBatches) model;
            return other.getCampus().getCampus_id() == campus.getCampus_id();
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T model) {
        if (model instanceof CampusWithBatches) {
            final CampusWithBatches other = (CampusWithBatches) model;


            return (campus.isContentTheSameAs(other.getCampus()) && batchWithSkills.equals(other.getBatchWithSkills()));
        }
        return false;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public List<BatchWithSkills> getBatchWithSkills() {
        return batchWithSkills;
    }

    public void setBatchWithSkills(List<BatchWithSkills> batchWithSkills) {
        this.batchWithSkills = batchWithSkills;
    }

    public boolean isBatchesVisible() {
        return batchesVisible;
    }

    public void setBatchesVisible(boolean batchesVisible) {
        this.batchesVisible = batchesVisible;
    }
}
