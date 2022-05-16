package com.example.practice_api.ui.wallpaper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WallpaperViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public WallpaperViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}