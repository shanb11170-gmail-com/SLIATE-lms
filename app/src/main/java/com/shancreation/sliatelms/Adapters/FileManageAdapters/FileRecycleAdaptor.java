package com.shancreation.sliatelms.Adapters.FileManageAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shancreation.sliatelms.Models.FileModels.DocumentFolderModel;
import com.shancreation.sliatelms.Models.FileModels.DocumentModel;
import com.shancreation.sliatelms.Models.FileModels.FolderModel;
import com.shancreation.sliatelms.R;

import java.util.List;

public  class FileRecycleAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public List<DocumentFolderModel> dofList;
    private static final int ITEM_TYPE_FOLDER=1;
    private static final int ITEM_TYPE_DOCUMENT=2;

    public FileRecycleAdaptor(List<DocumentFolderModel> dofList) {
        this.dofList = dofList;
    }

    @Override
    public int getItemViewType(int position) {
        if(dofList.get(position).getFolderModel()!=null){
            return ITEM_TYPE_FOLDER;

        }else if(dofList.get(position).getDocumentModel()!=null){
            return ITEM_TYPE_DOCUMENT;
        }else {
            return 999;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view ;

        if(viewType == ITEM_TYPE_FOLDER){
            view =layoutInflater.inflate(R.layout.layout_folder,parent,false);
            return new FolderViewHolder(view);
        }else if(viewType ==ITEM_TYPE_DOCUMENT){
            view =layoutInflater.inflate(R.layout.layout_document,parent,false);
            return new DocumentViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        DocumentFolderModel dof = dofList.get(position);
        
        if(holder instanceof FolderViewHolder){
            ((FolderViewHolder) holder).bind((FolderModel)dof.getFolderModel());
        }else if(holder instanceof  DocumentViewHolder){
            ((DocumentViewHolder) holder).bind((DocumentModel)dof.getDocumentModel());
        }
        
    }

    @Override
    public int getItemCount() {
        return dofList.size();
    }

    private static class DocumentViewHolder extends RecyclerView.ViewHolder{

        private TextView mDocumentName,mDocumentSize;


        public DocumentViewHolder(@NonNull View itemView) {
            super(itemView);

            mDocumentName = itemView.findViewById(R.id.txt_documentName);
            mDocumentSize = itemView.findViewById(R.id.txt_size);

        }

        public void bind(DocumentModel documentModel) {
          mDocumentName.setText(documentModel.getName());
        }
    }

    public static class  FolderViewHolder extends RecyclerView.ViewHolder{

        private TextView mFolderName;
        public FolderViewHolder(@NonNull View itemView) {
            super(itemView);
            mFolderName = itemView.findViewById(R.id.txt_FolderName);

        }

        public void bind(FolderModel folderModel) {
            mFolderName.setText(folderModel.getName());
        }
    }

}