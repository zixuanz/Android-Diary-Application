package com.zixuanz.mysecretdiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WritingActivity extends AppCompatActivity {

    private TextView weather;
    private TextView emotion;
    private TextView diaryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        initViews();
    }

    private void initViews(){
        weather = (TextView) findViewById(R.id.et_wrt_weather);
        emotion = (TextView) findViewById(R.id.et_wrt_emot);
        diaryText = (TextView) findViewById(R.id.et_wrt_txt);
    }
}