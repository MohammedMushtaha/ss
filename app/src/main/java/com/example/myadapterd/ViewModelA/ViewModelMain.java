package com.example.myadapterd.ViewModelA;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelMain extends ViewModel {

    private final MutableLiveData<String> mText;

    public ViewModelMain() {
        mText = new MutableLiveData<>();
        mText.setValue("Mohamed");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
