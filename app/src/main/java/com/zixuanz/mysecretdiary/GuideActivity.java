package com.zixuanz.mysecretdiary;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.zixuanz.mysecretdiary.Adapters.GuideFragPagerAdapter;
import com.zixuanz.mysecretdiary.Fragments.Guide.NameFrag;
import com.zixuanz.mysecretdiary.Fragments.Guide.PasswordFrag;
import com.zixuanz.mysecretdiary.Fragments.Guide.ShowFrag;
import com.zixuanz.mysecretdiary.Utils.SharedPrefUtil;

import static android.support.v4.view.ViewPager.SCROLL_STATE_DRAGGING;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, ShowFrag.EnterClickListener{

    public static final int PAGE_NAME = 0;
    public static final int PAGE_PW = 1;
    public static final int PAGE_SHOW = 2;

    private ViewPager viewPager;
    private GuideFragPagerAdapter gdFragPager;

    private ImageView[] dotImgViews;

    private int[] dotIDs = {R.id.iv_dot0, R.id.iv_dot1, R.id.iv_dot2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //dfdrequestWindowFeature(Window.FEATURE_NO_TITLE);
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
        int position = viewPager.getCurrentItem();
        if(state==SCROLL_STATE_DRAGGING){
            Log.d("GuideActivity:::", "PageScrollState--"+viewPager.getCurrentItem());
            switch (position){
                case PAGE_NAME:
                    ((NameFrag) gdFragPager.getItem(position)).saveInfo();
                    break;
                case PAGE_PW:
                    ((PasswordFrag) gdFragPager.getItem(position)).saveInfo();
                    break;
                default:
                    break;
            }
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
        /*
        Log.d("GduideActivity:::", "onPageScrolled--"+positionOffset);
        */
    }

    @Override
    public void onEnter() {
        SharedPrefUtil.setValue(SharedPrefUtil.PREF_NAME, SharedPrefUtil.VAL_FIRST, false, getApplicationContext());
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
