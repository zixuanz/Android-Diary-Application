package com.zixuanz.mysecretdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zixuanz.mysecretdiary.Utils.SharedPrefUtil;

public class WelcomeActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isFirstTime = SharedPrefUtil.getValue(SharedPrefUtil.GUIDE, SharedPrefUtil.VAL_FIRST, true, this);
        if(isFirstTime){
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            finish();
        }else {
            Intent intent = new Intent(this, PassActivity.class);
            startActivity(intent);
            finish();
            //setContentView(R.layout.activity_welcome);
        }
    }

}
