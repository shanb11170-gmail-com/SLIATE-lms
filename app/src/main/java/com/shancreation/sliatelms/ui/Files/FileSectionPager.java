package com.shancreation.sliatelms.ui.Files;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.shancreation.sliatelms.ui.Files.LMS.LMSFileFragment;
import com.shancreation.sliatelms.ui.Files.LMS.LMS_R_ragment;
import com.shancreation.sliatelms.ui.Files.LecthurerFiles.LecthureFileFragment;

public class FileSectionPager extends FragmentPagerAdapter {
    public FileSectionPager(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:

                LMSFileFragment lms = new LMSFileFragment();
                return lms;
            case 1:
                LecthureFileFragment lf = new LecthureFileFragment();
                return lf;
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
                return "LMS";
            case 1:
                return "Lecturer Files";
            default:
                return null;
        }


    }
}
