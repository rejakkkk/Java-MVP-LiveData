package com.example.jobtes.Member;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.jobtes.Admin.FragmentInputMember;
import com.example.jobtes.Admin.FragmentViewMember;

public class PagerAdapterMember extends FragmentStatePagerAdapter {

    int nameFragment;

    public PagerAdapterMember(@NonNull FragmentManager fm, int nameTab) {
        super(fm);
        nameFragment = nameTab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new FragmentChangePass();
            case 1 : return new FragmentViewMember();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return nameFragment;
    }
}