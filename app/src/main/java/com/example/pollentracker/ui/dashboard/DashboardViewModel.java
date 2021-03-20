package com.example.pollentracker.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pollentracker.R;
import com.mapbox.mapboxsdk.Mapbox;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is where users will take/upload photos");
    }

    public LiveData<String> getText() {
        return mText;
    }
}