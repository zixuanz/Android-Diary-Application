package com.zixuanz.mysecretdiary.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.zixuanz.mysecretdiary.Fragments.Guide.NameFrag;
import com.zixuanz.mysecretdiary.Fragments.Guide.PasswordFrag;
import com.zixuanz.mysecretdiary.Fragments.Guide.ShowFrag;
import com.zixuanz.mysecretdiary.GuideActivity;

/**
 * Created by Zixuan Zhao on 2/17/17.
 */

public class GuideFragPagerAdapter extends FragmentPagerAdapter {

    private PasswordFrag passwordFrag;
    private NameFrag nameFrag;
    private ShowFrag showFrag;

    public GuideFragPagerAdapter(FragmentManager fm) {
        super(fm);
        passwordFrag = new PasswordFrag();
        nameFrag = new NameFrag();
        showFrag = new ShowFrag();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public long getItemId(int position) {

        return super.getItemId(position);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case GuideActivity.PAGE_NAME:
                fragment = nameFrag;
                break;
            case GuideActivity.PAGE_PW:
                fragment = passwordFrag;
                break;
            case GuideActivity.PAGE_SHOW:
                fragment = showFrag;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return GuideActivity.PAGE_COUNT;
    }
}
