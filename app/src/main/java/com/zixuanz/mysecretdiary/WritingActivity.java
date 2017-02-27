package com.zixuanz.mysecretdiary;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.zixuanz.mysecretdiary.Utils.DataBaseManager;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class WritingActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private TextView weather;
    private TextView emotion;
    private TextView diaryText;
    private Toolbar toolbar;

    private Timer timer;
    private TimerTask autoSave;

    private DataBaseManager dbManager;

    private boolean isNotWritten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        Intent intent = new Intent();
        isNotWritten = intent.getBooleanExtra("isNotWritten", true);

        initViews();
        dbManager = new DataBaseManager(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("WrittingActivity:::", "onPause");
        timer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("WrittingActivity:::", "onResume");
        autoSaving();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("WrittingActivity:::", "onDestroy");
        timer.cancel();
        boolean flag = dbManager.closeDatabase();
        Log.d("WrittingActivity:::", "CLOSE DATABASE? "+flag);
    }

    /**
     * Initialize neccessary views.
     */
    private void initViews(){
        initToolbar();

        weather = (TextView) findViewById(R.id.et_wrt_weather);
        emotion = (TextView) findViewById(R.id.et_wrt_emot);
        diaryText = (TextView) findViewById(R.id.et_wrt_txt);
    }

    /**
     * Initialize toolbar view with corresponding views
     */
    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.tb_wrt);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Get neccessary data from view preparing to be saved
     * @return An array of String type.
     */
    private String[] getText(){
        String strEmotion = emotion.getText().toString();
        String strWeather = weather.getText().toString();
        String strContext = diaryText.getText().toString();
        String strLastDate = new Date().toString();

        String texts[] = {strLastDate, strWeather, strEmotion, strContext};
        return texts;
    }

    /**
     * Setup timer for autosaving.
     */
    private void autoSaving(){
        autoSave = new TimerTask() {
            @Override
            public void run() {
                String texts[] = getText();
                SavingDiary save = new SavingDiary();
                save.execute(texts);
            }
        };

        timer = new Timer();
        timer.schedule(autoSave, 5000, 15000);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    private class SavingDiary extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            dbManager.openWritableDatabase();
            dbManager.insert(new Date().toString(), "aa", new Date().toString(),params[1], params[2], params[3]);
            /*
            boolean flag = false;
            boolean flag2 = false;
            if(isNotWritten){
                flag = dbManager.insert("aaa", "aa", new Date().toString(),params[1], params[2], params[3]);
                isNotWritten = false;

            }else{
                flag2 = dbManager.update("aaa", "aaa", "aaa", "aaa", "aaa");
            }
            Log.d("WritingActivity:::", "ISNOTWRITTEN--"+isNotWritten);
//            Log.d("WrittingActivity:::", params[0]);

  //          Log.d("WrittingActivity:::", params[3]);
            Log.d("WrittingActivity:::", "INSERT--" + flag);
            Log.d("WrittingActivity:::", "INSERT--" + flag2);
*/
            return null;
        }
    }
}
