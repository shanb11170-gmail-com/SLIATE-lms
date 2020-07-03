package com.shancreation.sliatelms.ui.home.Assignment;

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
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.shancreation.sliatelms.Adapters.AssignmentAdapter;
import com.shancreation.sliatelms.Models.Assignment;
import com.shancreation.sliatelms.R;

import java.util.ArrayList;
import java.util.List;

public class AssignmentFragment extends Fragment {

    private AssignmentViewModel mViewModel;
    private ProgressBar mprogress;
    private ListView mListView;
    private ArrayList<Assignment> mAssignmentList;
    private FirebaseFirestore fs;
    private AssignmentAdapter mAssignmentAdapter;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAssignmentList = new ArrayList<>();
        fs = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.assignment_fragment, container, false);
        mprogress = view.findViewById(R.id.pbAssignment);
        mListView = view.findViewById(R.id.lstAssignment);
        mprogress.setVisibility(View.INVISIBLE);
        GetAssignment();

        return view;

    }
    private  void GetAssignment(){
        try {
            mprogress.setVisibility(View.VISIBLE);

            fs.collection("/ATI/ANU/IT/2018/Assignment/")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for(QueryDocumentSnapshot doc:task.getResult())
                                {
                                    Log.d("ASSIGNMENTS",doc.getData().toString());
                                    Timestamp timestamp = (Timestamp) doc.getData().get("Due");
                                    String title = (String) doc.getData().get("Assignment_Title");
                                    String subject = (String) doc.getData().get("Subject");
                                    String content = (String) doc.getData().get("content");
                                    String owner =(String) doc.getData().get("by");
                                    //String links[] = (String[]) doc.getData().get("links");
                                    List<String> links = (List<String>) doc.getData().get("links");

                                    Log.d("LINKLIST", links.get(0));


                                    Assignment ntd = new Assignment(timestamp,title,subject,content,links);

                                    mAssignmentList.add(ntd);
                                    mprogress.setVisibility(View.INVISIBLE);
                                    mAssignmentAdapter = new AssignmentAdapter(getActivity(),mAssignmentList);
                                    mListView.setAdapter(mAssignmentAdapter);
                                    mAssignmentAdapter.notifyDataSetChanged();

                                }

                            }
                        }
                    });
        }catch (Exception e){

        }

    }


}