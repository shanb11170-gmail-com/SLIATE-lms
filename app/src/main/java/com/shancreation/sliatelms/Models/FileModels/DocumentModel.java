package com.shancreation.sliatelms.Models.FileModels;

import android.net.Uri;

public class DocumentModel {
    String name,type;
    Uri url;
    long size =0;

    public DocumentModel(String name,  Uri url, String type, long size) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  Uri getUrl() {
        return url;
    }

    public void setUrl( Uri url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
    public String getSizeMb(){
        long mb = this.size/1024;

        return String.valueOf(mb)+"KB";

    }
}
