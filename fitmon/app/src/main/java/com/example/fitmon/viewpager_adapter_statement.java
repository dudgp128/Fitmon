package com.example.fitmon;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class viewpager_adapter_statement extends FragmentPagerAdapter {

    private ArrayList<Fragment> items;
    private ArrayList<String> itext;

    public viewpager_adapter_statement(FragmentManager fm) {
        super(fm);
        items = new ArrayList<Fragment>();
        itext = new ArrayList<String>();

        items.add(new state_first());
        items.add(new state_stamp());

        itext.add("통계");
        itext.add("스탬프");
    }

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return itext.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
