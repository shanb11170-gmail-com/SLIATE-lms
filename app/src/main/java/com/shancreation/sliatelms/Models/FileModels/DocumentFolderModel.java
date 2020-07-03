package com.shancreation.sliatelms.Models.FileModels;

public class DocumentFolderModel {
    DocumentModel documentModel=null;
    FolderModel folderModel=null;

    public DocumentFolderModel(DocumentModel documentModel) {

        this.documentModel = documentModel;
        this.folderModel = null;
    }

    public DocumentFolderModel(FolderModel folderModel) {

        this.folderModel = folderModel;
        this.documentModel=null;
    }

    public DocumentModel getDocumentModel() {
        return documentModel;
    }


    public FolderModel getFolderModel() {
        return folderModel;
    }


}
