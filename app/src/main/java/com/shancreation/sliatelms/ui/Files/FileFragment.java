package com.shancreation.sliatelms.ui.Files;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.shancreation.sliatelms.R;

public class FileFragment extends Fragment {

private ViewPager mFilePager;
private TabLayout mFileTab;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View itemView =inflater.inflate(R.layout.file_fragment, container, false);

        mFilePager = itemView.findViewById(R.id.vp_file);
        mFileTab = itemView.findViewById(R.id.tl_File);

        FileSectionPager fs = new FileSectionPager(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        mFilePager.setAdapter(fs);

        mFileTab.setupWithViewPager(mFilePager);



        return itemView;

    }


}