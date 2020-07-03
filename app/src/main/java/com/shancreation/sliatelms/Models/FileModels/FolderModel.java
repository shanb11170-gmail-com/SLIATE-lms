package com.shancreation.sliatelms.Models.FileModels;

import android.util.Log;

public class FolderModel {
    String name="error";

    public FolderModel(String name) {
        Log.d("FM NAME",name);
        if(name!=null){
            this.name = name;
        }


    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
