package com.zixuanz.mysecretdiary;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.zixuanz.mysecretdiary.Adapters.GuideFragPagerAdapter;

import static android.support.v4.view.ViewPager.SCROLL_STATE_SETTLING;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    public static final int PAGE_NAME = 0;
    public static final int PAGE_PW = 1;

    private ViewPager viewPager;
    private GuideFragPagerAdapter gdFragPager;

    private ImageView[] dotImgViews;

    private int[] dotIDs = {R.id.iv_dot0, R.id.iv_dot1};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initViews();

    }

    private void initViews(){
        dotImgViews = new ImageView[dotIDs.length];
        for(int i=0; i<dotIDs.length; i++){
            dotImgViews[i] = (ImageView) findViewById(dotIDs[i]);
        }

        gdFragPager = new GuideFragPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager)findViewById(R.id.vp_wel_gd);
        viewPager.setAdapter(gdFragPager);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(state==SCROLL_STATE_SETTLING){

        }

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotImgViews.length; i++) {
            if (position == i) {
                dotImgViews[i].setImageResource(R.drawable.wel_gd_dot_selected);
            } else {
                dotImgViews[i].setImageResource(R.drawable.wel_gd_dot);
            }

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
}
