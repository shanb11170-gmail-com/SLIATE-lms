package com.shancreation.sliatelms.ui.Files.LMS;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.shancreation.sliatelms.Models.FileModels.DocumentFolderModel;
import com.shancreation.sliatelms.Models.FileModels.DocumentModel;
import com.shancreation.sliatelms.Models.FileModels.FolderModel;
import com.shancreation.sliatelms.NavActivity;
import com.shancreation.sliatelms.R;
import com.shancreation.sliatelms.WebActivity;

import java.util.ArrayList;

import static android.os.Environment.DIRECTORY_DOWNLOADS;


public class LMSFileFragment extends Fragment implements NavActivity.OnBackPressedListener {
    private StorageReference mStorageRef;
    private FirebaseFirestore fs;

    private TextView mdir;
    private    String path;
    ArrayList<DocumentFolderModel> dofList;
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

       // FolderModel fm = new FolderModel("AAAAA");

       // dofList.add(new DocumentFolderModel(fm));

        dir.add("Documents/IT/LMS");
        //NotifyData();



    }



    @Override
    public void onResume() {
        super.onResume();
        getfileDetails();

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
                    folList = new ArrayList<>();
                    docList = new ArrayList<>();
                    folList.clear();
                    docList.clear();
                    int i =0;
                    ArrayList<String> dirnames = new ArrayList<>();
                    for (StorageReference pre:listResult.getPrefixes()){
                        String fname = pre.getName();

                        FolderModel f = new FolderModel(fname);

                        folList.add(f);
                        //TempList.add(new DocumentFolderModel(f));




                    }

                    for (StorageReference item:listResult.getItems()){
                        Log.d("FNAME",item.toString());
                        item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.d("DURI",uri.toString());

                                item.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                                    @Override
                                    public void onSuccess(StorageMetadata smd) {

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
       // dofList = new ArrayList<>();
    dofList.clear();
    dofAdapter.notifyDataSetChanged();

    int i=1;
    while (i<=docList.size()||i<=folList.size())
    {
        if(i<=docList.size()){
            dofList.add(new DocumentFolderModel(docList.get(i-1)));
        }
        if(i<=folList.size()){
            dofList.add(new DocumentFolderModel(folList.get(i-1)));
            FolderModel f = folList.get(i-1);

        }
        dofAdapter.notifyDataSetChanged();
        i++;
    }
    Log.d("DOFSIZE",String.valueOf(dofList.size()));


}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((NavActivity) getActivity()).setOnBackPressedListener(this::doBack);
        View mView =inflater.inflate(R.layout.fragment_l_m_s_file, container, false);
        mlistView = mView.findViewById(R.id.lst_fileView);

        ///getfileDetails();
        getfileDetails();
        dofAdapter = new DocumentAndFolderAdapter(getActivity(),dofList);

        mdir =mView.findViewById(R.id.txt_dirs);

        mdir.setText(path);

        mlistView.setAdapter(dofAdapter);


        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                DocumentFolderModel ndof = dofList.get(position);

                if(ndof.getFolderModel()!=null){
                    FolderModel fm = ndof.getFolderModel();

                    dir.add(fm.getName());
                    getfileDetails();
                    dofAdapter.notifyDataSetChanged();

                }else if(ndof.getDocumentModel()!=null){

                  OpenDocument(position);



                }



            }
        });

        registerForContextMenu(mlistView);
        return mView;
    }
private void OpenDocument(int p){
    DocumentFolderModel ndof = dofList.get(p);

    DocumentModel doc = ndof.getDocumentModel();
    String extention = doc.getName().substring(doc.getName().lastIndexOf('.')+1);
    String fileName = doc.getName().substring(0,doc.getName().lastIndexOf('.'));
    String url = doc.getUrl().toString();
    Intent intent = new Intent(getActivity(), WebActivity.class);
    intent.putExtra("url",url);
    startActivity(intent);


}

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
       //  super.onCreateContextMenu(menu, v, menuInfo);

        if(v.getId()==R.id.lst_fileView){

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
          int p=  info.position;


            DocumentFolderModel dof = dofList.get(p);
            if(dof.getDocumentModel()!=null){
                menu.setHeaderTitle("Document Menu");
                menu.add(Menu.NONE,0,0,"Open");
                menu.add(Menu.NONE,1,1,"Download");
                // MenuInflater inflater = getActivity().getMenuInflater();

                // inflater.inflate(R.menu.document_menu,menu);
            }

        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        DocumentFolderModel dof = dofList.get(info.position);

        DocumentModel dm = dof.getDocumentModel();

        String url = dm.getUrl().toString();

        switch (item.getItemId()){
            case 0:
                    OpenDocument(info.position);
                return true;
            case 1:
                downloadFile(getContext(),dm.getName(),DIRECTORY_DOWNLOADS,url);
                return true;

            default:return true;
        }




        


    }

    public void downloadFile(Context context, String fileName, String Dest, String url ){

        Log.d("DOWNLOADFILE",url);
        DownloadManager downloadManager =(DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context,Dest,fileName);
        downloadManager.enqueue(request);
    }

    @Override
    public void doBack() {
        //Toast.makeText(getContext(),"OnBack Pressd",Toast.LENGTH_LONG).show();
        if(dir.size()>1){
            dir.remove(dir.size()-1);
            getfileDetails();
            dofAdapter.notifyDataSetChanged();
        }

    }
}