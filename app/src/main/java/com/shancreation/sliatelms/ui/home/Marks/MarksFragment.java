package com.shancreation.sliatelms.ui.home.Marks;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.shancreation.sliatelms.Adapters.MarksAdapter;
import com.shancreation.sliatelms.Models.Marks;
import com.shancreation.sliatelms.R;

import java.util.ArrayList;

public class MarksFragment extends Fragment {

    private FirebaseFirestore fs ;
    private ArrayList<Marks> YmList;
    private MarksAdapter ymAdaptor;
    private ListView mListView;
    private ProgressBar mprogressbar;
    private FirebaseAuth mAuth;
    FirebaseUser muser;

    public static MarksFragment newInstance() {
        return new MarksFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        YmList = new ArrayList<>();

        fs = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED).build();

        fs.setFirestoreSettings(settings);
        mAuth= FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.marks_fragment, container, false);

        mListView = view.findViewById(R.id.lst_marks);
        mprogressbar = view.findViewById(R.id.pb_marks);
        mprogressbar.setVisibility(View.INVISIBLE);
        getMarks();
if(muser==null){
    Log.d("USER","Not Loged");
}

        return view;

    }

    public void getMarks(){
        try{

            mprogressbar.setVisibility(View.VISIBLE);
            String uid = muser.getUid();
            fs.collection("/ATI/ANU/IT/2018/Student/"+uid+"/assignment/")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (QueryDocumentSnapshot doc:task.getResult()){

                                    Log.d("YOURMARKS",doc.getData().toString());
                                    String id = doc.getId();
                                    String marks =(String) doc.getData().get("marks");
                                    String Subject =(String) doc.getData().get("Subject");
                                    String assignment =(String) doc.getData().get("assignment");
                                    int mrk = Integer.parseInt(marks);
                                    Marks ym = new Marks(assignment,Subject,"null","2018","2018",mrk);
                                    YmList.add(ym);
                                    Log.d("SUB",Subject);

                                    ymAdaptor = new MarksAdapter(YmList,getActivity());
                                    mListView.setAdapter(ymAdaptor);
                                    ymAdaptor.notifyDataSetChanged();
                                }
                                mprogressbar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });

        }catch (Exception ex){

        }
    }

}