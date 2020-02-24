package com.revature.revaturetrainingroomplanner.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.revature.revaturetrainingroomplanner.data.model.Campus;

public class CampusSelectedViewModel extends ViewModel {

    private MutableLiveData<Campus> mCampusSelected = new MutableLiveData<>();

    public CampusSelectedViewModel() {
    }

    public void setCampusSelected(Campus campusSelected) {
        mCampusSelected.setValue(campusSelected);
    }

    public LiveData<Campus> getCampusSelected() {
        return mCampusSelected;
    }
}
