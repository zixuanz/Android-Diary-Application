package com.zixuanz.mysecretdiary;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zixuanz.mysecretdiary.Utils.SharedPrefUtil;

public class WelcomeActivity extends AppCompatActivity {

    private static final int ACT_GUIDE = 0;
    private static final int ACT_PASS = 1;
    private static final int ACT_HOME = 2;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case ACT_GUIDE:
                    selectGuide();
                    break;
                case ACT_PASS:
                    selectPass();
                    break;
                case ACT_HOME:
                    selectHome();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        checkStates();
    }

    private void checkStates(){
        boolean isFirstTime = SharedPrefUtil.getValue(SharedPrefUtil.GUIDE, SharedPrefUtil.VAL_FIRST, true, this);
        boolean needPw = !(SharedPrefUtil.getValue(SharedPrefUtil.GUIDE, SharedPrefUtil.VAL_PASS, "", this)).equals("");
        if(isFirstTime){
            handler.sendEmptyMessageDelayed(ACT_GUIDE, 2000);
        }else{
            if(needPw){
                handler.sendEmptyMessageDelayed(ACT_PASS, 2000);
            }else{
                handler.sendEmptyMessageDelayed(ACT_HOME, 2000);
            }


        }
    }

    private void selectGuide(){
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
        finish();
    }

    private void selectPass(){
        Intent intent = new Intent(this, PassActivity.class);
        startActivity(intent);
        finish();
    }

    private void selectHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
