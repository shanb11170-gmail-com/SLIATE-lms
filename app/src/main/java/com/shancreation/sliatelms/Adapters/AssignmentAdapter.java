package com.shancreation.sliatelms.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shancreation.sliatelms.Models.Assignment;
import com.shancreation.sliatelms.R;

import java.util.ArrayList;

public class AssignmentAdapter extends BaseAdapter {
    private LayoutInflater inflater =null;
    private Activity mActitvty;
    private ArrayList<Assignment> assignmentList;


    public AssignmentAdapter(Activity mActitvty, ArrayList<Assignment> assignmentList) {
        this.mActitvty = mActitvty;
        this.assignmentList = assignmentList;

        inflater = (LayoutInflater)  mActitvty.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return assignmentList.size();
    }

    @Override
    public Object getItem(int i) {
        return assignmentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        itemView = (itemView==null)? inflater.inflate(R.layout.layout_assignment,null):itemView;

        TextView mDateCount = itemView.findViewById(R.id.txt_due);
        TextView mAssignment = itemView.findViewById(R.id.txt_Assignment);
        TextView mSub = itemView.findViewById(R.id.txt_as_sub);

        Assignment assignment = assignmentList.get(i);

        mAssignment.setText(assignment.getTitle());
        mSub.setText(assignment.getSubject());



        return itemView;
    }
}
