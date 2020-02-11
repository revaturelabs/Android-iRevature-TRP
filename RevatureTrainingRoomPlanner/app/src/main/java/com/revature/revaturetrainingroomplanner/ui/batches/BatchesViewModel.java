package com.revature.revaturetrainingroomplanner.ui.batches;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BatchesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BatchesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}