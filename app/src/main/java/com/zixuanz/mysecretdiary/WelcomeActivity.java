package com.zixuanz.mysecretdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zixuanz.mysecretdiary.Utils.SharedPrefUtil;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editPassWord;
    private Button btnNo;
    private Button btnYes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isFirstTime = SharedPrefUtil.getValue(SharedPrefUtil.PREF_NAME, SharedPrefUtil.VAL_FIRST, true, this);
        if(isFirstTime){
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            finish();
        }else {
            setContentView(R.layout.activity_welcome);
            initViews();
        }
    }

    private void initViews(){
        editPassWord = (EditText) findViewById(R.id.et_act_wel);
        btnNo = (Button) findViewById(R.id.bt_no_act_wel);
        btnYes = (Button) findViewById(R.id.bt_yes_act_wel);
        btnNo.setOnClickListener(this);
        btnYes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnYes)){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        if(v.equals(btnNo)){
            finish();
        }
    }

}
