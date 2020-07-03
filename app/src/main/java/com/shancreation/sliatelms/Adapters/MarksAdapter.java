package com.shancreation.sliatelms.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.shancreation.sliatelms.Models.Marks;
import com.shancreation.sliatelms.R;

import java.util.ArrayList;

public class MarksAdapter extends BaseAdapter {
    private ArrayList<Marks> ymList;
    private LayoutInflater inflater =null;
    private Activity mActitvty;

    public MarksAdapter(ArrayList<Marks> ymList, Activity mActitvty) {
        this.ymList = ymList;
        this.mActitvty = mActitvty;

        inflater = (LayoutInflater) mActitvty.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ymList.size();
    }

    @Override
    public Object getItem(int i) {
        return ymList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;

        itemView = (itemView==null)?inflater.inflate(R.layout.layout_marks,null):itemView;

        TextView mMarks = itemView.findViewById(R.id.txt_marks);
        TextView mSub = itemView.findViewById(R.id.txt_subject);
        TextView mAssignment = itemView.findViewById(R.id.txt_txtAssignment);
        ProgressBar mProgress = itemView.findViewById(R.id.pbMarks);


        Marks marks = ymList.get(i);
        Log.d("Adapter",marks.getSubject());


       mSub.setText(marks.getSubject());
       mAssignment.setText(marks.getTitle());
        mMarks.setText(String.valueOf(marks.getMarks())+"%");
        int mark = marks.getMarks();

       mProgress.setProgress(mark);
        if(mark>75){
            mProgress.getProgressDrawable().setColorFilter(ContextCompat.getColor(mActitvty,R.color.Green_dark), PorterDuff.Mode.SRC_IN);
        }else if(mark>50){
            mProgress.getProgressDrawable().setColorFilter(ContextCompat.getColor(mActitvty,R.color.yellow_dark),PorterDuff.Mode.SRC_IN);
        }
        else if(mark>25){
            mProgress.getProgressDrawable().setColorFilter(ContextCompat.getColor(mActitvty,R.color.red),PorterDuff.Mode.SRC_IN);
        }else {

        }


        return itemView;
    }
}
