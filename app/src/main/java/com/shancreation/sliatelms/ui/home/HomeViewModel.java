package com.shancreation.sliatelms.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;

import com.shancreation.sliatelms.Models.Assignment;


import java.util.List;

public class HomeViewModel extends ViewModel {


    public MutableLiveData<List<Assignment>> assignmentList = new MutableLiveData<List<Assignment>>();







    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }



    public LiveData<String> getText() {
        return mText;
    }
}