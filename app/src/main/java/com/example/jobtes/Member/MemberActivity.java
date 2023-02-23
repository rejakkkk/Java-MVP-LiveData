package com.example.jobtes.Member;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.jobtes.Admin.PagerAdapter;
import com.example.jobtes.R;
import com.google.android.material.tabs.TabLayout;

public class MemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Change Password"));
        tabLayout.addTab(tabLayout.newTab().setText("View Data Member"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager pagerr = findViewById(R.id.pager);
        PagerAdapterMember adapter = new PagerAdapterMember(getSupportFragmentManager(), tabLayout.getTabCount());
        pagerr.setAdapter(adapter);

        pagerr.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pagerr.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
