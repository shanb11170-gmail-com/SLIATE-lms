package com.shancreation.sliatelms.Adapters.FileManageAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.shancreation.sliatelms.Models.FileModels.DocumentFolderModel;
import com.shancreation.sliatelms.Models.FileModels.DocumentModel;
import com.shancreation.sliatelms.Models.FileModels.FolderModel;
import com.shancreation.sliatelms.R;

import java.util.ArrayList;
import java.util.Collections;

public class DocumentAndFolderAdapter extends BaseAdapter {
    private static LayoutInflater inflater =null;
    private ArrayList<DocumentFolderModel> dofList;

    public DocumentAndFolderAdapter(FragmentActivity activity, ArrayList<DocumentFolderModel> List) {
       //this.dofList = new ArrayList<>();
       //dofList.clear();
        this.dofList = List;

        Log.d("DOF",String.valueOf(dofList.size()));
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return dofList.size();
    }

    @Override
    public Object getItem(int i) {
        return dofList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        DocumentFolderModel dof = dofList.get(i);

        if(dof.getFolderModel()!=null){
            itemView = (itemView==null)? inflater.inflate(R.layout.layout_folder,null):itemView;

            TextView mfolderName = itemView.findViewById(R.id.txt_FolderName);
            FolderModel folder = dof.getFolderModel();

            String foldername = "error in folder";

            if(folder.getName()!=null){
                foldername = folder.getName();
            }



                mfolderName.setText(foldername);






        }

        if(dof.getDocumentModel()!=null){

            itemView = (itemView==null)? inflater.inflate(R.layout.layout_document,null):itemView;

            TextView docName = itemView.findViewById(R.id.txt_documentName);
            TextView mdocSize = itemView.findViewById(R.id.txt_size);

    DocumentModel doc = dof.getDocumentModel();
    docName.setText(doc.getName());


            //mdocSize.setText(doc.getSizeMb());
        }

        return itemView;
    }
}
