package com.shancreation.sliatelms.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.shancreation.sliatelms.ui.home.Assignment.AssignmentFragment;
import com.shancreation.sliatelms.ui.home.Marks.MarksFragment;

public class HomeSectionPager extends FragmentPagerAdapter {


    public HomeSectionPager(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                MarksFragment mf = new MarksFragment();
                return mf;
            case 1:
                AssignmentFragment Af = new AssignmentFragment();
                return Af;
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Marks";
            case 1:
                return "Assignment";
            default:
                return null;

        }
    }
}
