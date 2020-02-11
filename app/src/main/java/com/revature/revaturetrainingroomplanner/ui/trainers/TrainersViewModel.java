package com.revature.revaturetrainingroomplanner.ui.trainers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrainersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TrainersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}