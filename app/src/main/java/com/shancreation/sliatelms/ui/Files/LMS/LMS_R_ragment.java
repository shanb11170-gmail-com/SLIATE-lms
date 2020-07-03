package com.shancreation.sliatelms.ui.Files.LMS;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.shancreation.sliatelms.Adapters.FileManageAdapters.DocumentAndFolderAdapter;
import com.shancreation.sliatelms.Adapters.FileManageAdapters.FileRecycleAdaptor;
import com.shancreation.sliatelms.Models.FileModels.DocumentFolderModel;
import com.shancreation.sliatelms.Models.FileModels.DocumentModel;
import com.shancreation.sliatelms.Models.FileModels.FolderModel;
import com.shancreation.sliatelms.NavActivity;
import com.shancreation.sliatelms.R;

import java.util.ArrayList;
import java.util.List;


public class LMS_R_ragment extends Fragment implements NavActivity.OnBackPressedListener  {
private FileRecycleAdaptor mAdapter;
  RecyclerView mLMSView;
  private List<DocumentFolderModel> dofList;
    private StorageReference mStorageRef;
    private FirebaseFirestore fs;

    private TextView mdir;
    private    String path;
    ArrayList<DocumentFolderModel> TempList;
    private DocumentAndFolderAdapter dofAdapter;
    private ArrayList<String> dir;
    private ListView mlistView;

    private ArrayList<DocumentModel> docList;
    private ArrayList<FolderModel> folList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dofList = new ArrayList<>();
        docList = new ArrayList<>();
        folList =new ArrayList<>();
        TempList = new ArrayList<>();
        dir = new ArrayList<>();
        fs = FirebaseFirestore.getInstance();
        dir.add("Documents/IT/LMS");
        FolderModel f = new FolderModel("aaaaa");
        dofList.add(new DocumentFolderModel(f));

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_l_m_s__r_ragment, container, false);

        ((NavActivity) getActivity()).setOnBackPressedListener(this::doBack);
getfileDetails();
        mLMSView = view.findViewById(R.id.rv_lms);
        mAdapter = new FileRecycleAdaptor(dofList);
        mLMSView.setAdapter(mAdapter);


        return view;
    }

    private boolean getfileDetails() {

        TempList.clear();


        path=dir.get(0) ;

        for(int i=1; i< dir.size();i++)
        {

            path = path+"/"+dir.get(i);

        }


        Toast.makeText(getContext(),path,Toast.LENGTH_LONG).show();

        mStorageRef = FirebaseStorage.getInstance().getReference().child(path);

        try {
            mStorageRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                @Override
                public void onSuccess(ListResult listResult) {

                    folList.clear();
                    docList.clear();

                    for (StorageReference pre:listResult.getPrefixes()){
                        FolderModel f = new FolderModel(pre.getName());

                        folList.add(f);
                        TempList.add(new DocumentFolderModel(f));


                        Log.d("STORAGE",pre.getName());

                    }

                    for (StorageReference item:listResult.getItems()){

                        item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.d("DURI",uri.toString());

                                item.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                                    @Override
                                    public void onSuccess(StorageMetadata smd) {
                                        Log.d("DMNAME",smd.getName());
                                        Log.d("DownloadUri",uri.toString());
                                        DocumentModel dm = new DocumentModel(smd.getName(),uri,smd.getContentType(),smd.getSizeBytes());


                                        docList.add(dm);
                                        TempList.add(new DocumentFolderModel(dm));


                                    }
                                }).addOnCompleteListener(new OnCompleteListener<StorageMetadata>() {
                                    @Override
                                    public void onComplete(@NonNull Task<StorageMetadata> task) {
                                        NotifyChange();
                                    }
                                });


                            }
                        });




                    }

                }
            }).addOnCompleteListener(new OnCompleteListener<ListResult>() {
                @Override
                public void onComplete(@NonNull Task<ListResult> task) {
                    Log.d("ONCOMPLETE DOC",String.valueOf(docList.size()));
                    Log.d("ONCOMPLETE FOL",String.valueOf(folList.size()));
                    // Log.d("DOFSIZE",String.valueOf(dofList.size()));

                    NotifyChange();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                }
            });
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    private void NotifyChange(){
        dofList.clear();
       // dofAdapter.notifyDataSetChanged();
        int i=1;
        while (i<=docList.size()||i<=folList.size())
        {
            if(i<=docList.size()){
                dofList.add(new DocumentFolderModel(docList.get(i-1)));
            }
            if(i<=folList.size()){
                dofList.add(new DocumentFolderModel(folList.get(i-1)));
                FolderModel f = folList.get(i-1);
                Log.d("FOLDER NAME",f.getName());
            }
           // dofAdapter.notifyDataSetChanged();
            i++;
        }
        Log.d("DOFSIZE",String.valueOf(dofList.size()));


    }

    @Override
    public void doBack() {
        if(dir.size()>1){
            dir.remove(dir.size()-1);
            getfileDetails();
            dofAdapter.notifyDataSetChanged();
        }
    }
}