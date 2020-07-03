package com.shancreation.sliatelms.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.shancreation.sliatelms.Models.Assignment;
import com.shancreation.sliatelms.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private ViewPager mHomePager;
    private TabLayout mHomeTab;



    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.fragment_home, container, false);


        mHomePager = root.findViewById(R.id.vp_home);
        mHomeTab = root.findViewById(R.id.tl_home);

        HomeSectionPager homeSectionPager = new HomeSectionPager(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        mHomePager.setAdapter(homeSectionPager);
        mHomeTab.setupWithViewPager(mHomePager);






        return root;
    }
}