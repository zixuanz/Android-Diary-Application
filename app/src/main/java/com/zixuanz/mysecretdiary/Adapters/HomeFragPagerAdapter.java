package com.zixuanz.mysecretdiary.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zixuanz.mysecretdiary.Fragments.Home.CalendarViewFragment;
import com.zixuanz.mysecretdiary.Fragments.Home.CardViewFragment;
import com.zixuanz.mysecretdiary.HomeActivity;

/**
 * Created by Zixuan Zhao on 3/7/17.
 */

public class HomeFragPagerAdapter extends FragmentPagerAdapter {

    CardViewFragment cardViewFragment;
    CalendarViewFragment calendarViewFragment;

    public HomeFragPagerAdapter(FragmentManager fm){
        super(fm);

        cardViewFragment = new CardViewFragment();
        calendarViewFragment = new CalendarViewFragment();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case HomeActivity.PAGE_CARD:
                fragment = cardViewFragment;
                break;
            case HomeActivity.PAGE_CALENDAR:
                fragment = calendarViewFragment;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return HomeActivity.PAGE_COUNT;
    }

}
