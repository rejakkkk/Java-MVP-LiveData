package com.example.jobtes.Admin;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.jobtes.Member.FragmentChangePass;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int nameFragment;

    public PagerAdapter(@NonNull FragmentManager fm, int nameTab) {
        super(fm);
        nameFragment = nameTab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new FragmentInputMember();
            case 1 : return new FragmentViewMember();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return nameFragment;
    }
}
