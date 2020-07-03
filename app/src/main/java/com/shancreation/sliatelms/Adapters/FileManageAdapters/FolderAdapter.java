package com.shancreation.sliatelms.Adapters.FileManageAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shancreation.sliatelms.Models.FileModels.FolderModel;
import com.shancreation.sliatelms.R;

import java.util.ArrayList;

public class FolderAdapter extends BaseAdapter {
    private static LayoutInflater inflater =null;
    private ArrayList<FolderModel> folders ;

    public FolderAdapter(Activity context,ArrayList<FolderModel> folders) {
        this.folders = folders;

        inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return folders.size();
    }

    @Override
    public Object getItem(int i) {
        return folders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        itemView = (itemView==null)? inflater.inflate(R.layout.layout_folder,null):itemView;

        TextView mFolderName = itemView.findViewById(R.id.txt_FolderName);

        FolderModel fm = folders.get(i);

        mFolderName.setText(fm.getName());


        return itemView;
    }
}
