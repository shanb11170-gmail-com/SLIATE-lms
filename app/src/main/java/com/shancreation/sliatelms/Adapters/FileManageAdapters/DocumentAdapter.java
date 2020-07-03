package com.shancreation.sliatelms.Adapters.FileManageAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shancreation.sliatelms.Models.FileModels.DocumentModel;
import com.shancreation.sliatelms.R;

import java.util.ArrayList;

public class DocumentAdapter extends BaseAdapter {
    private static LayoutInflater inflater =null;
    private ArrayList<DocumentModel> documents ;

    public DocumentAdapter(Activity context, ArrayList<DocumentModel> documents) {
        this.documents = documents;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return documents.size();
    }

    @Override
    public Object getItem(int i) {
        return documents.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        itemView = (itemView==null)? inflater.inflate(R.layout.layout_document,null):itemView;

        TextView docName = itemView.findViewById(R.id.txt_documentName);
        TextView mdocSize = itemView.findViewById(R.id.txt_size);

        DocumentModel doc = documents.get(i);
        docName.setText(doc.getName());
        mdocSize.setText(doc.getSizeMb());

        return null;
    }
}
