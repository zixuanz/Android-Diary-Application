package com.zixuanz.mysecretdiary;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.zixuanz.mysecretdiary.Adapters.HomeRecyViewAdapter;
import com.zixuanz.mysecretdiary.DataStructures.Home.Diary;
import com.zixuanz.mysecretdiary.Utils.DataBaseManager;
import com.zixuanz.mysecretdiary.Utils.Tools;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity{

    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private HomeRecyViewAdapter homeRecyViewAdapter;


    private FloatingActionButton newDiary;

    private DataBaseManager dbManager;
    private List<Diary> diaries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbManager = new DataBaseManager(getApplicationContext());
        initViews();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("HomeActivity:::", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("HomeActivity:::", "onResume");

        AcquireDiary acquireDiary = new AcquireDiary();
        acquireDiary.execute();
        Log.d("HomeActivity:::", "diaries--"+diaries.size());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("HomeActivity:::", "onDestroy");
        dbManager.closeDatabase();
    }

    private void initViews() {

        initToolbar();

        diaries = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rv_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        homeRecyViewAdapter = new HomeRecyViewAdapter(diaries, HomeActivity.this);
        homeRecyViewAdapter.setClickListener(new HomeRecyViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                TextView date = (TextView) view.findViewById(R.id.tv_recy_hm_date);
                Log.d("HomeActivity:::", "" + date.getText().toString());
            }
        });
        recyclerView.setAdapter(homeRecyViewAdapter);

        newDiary = (FloatingActionButton) findViewById(R.id.fab_home_new);
        newDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, WritingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.tb_home);
        toolbar.inflateMenu(R.menu.menu_home_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_settings:
                        Log.d("HomeActivity:::", "home_Setting");
                        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        //setSupportActionBar(toolbar);
    }

    private void setDataDiary() {
        dbManager.queryAll(diaries);
    }


    private class CheckToday extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            dbManager.openReadableDatabase();
            return null;
        }
    }


    private class AcquireDiary extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            dbManager.openReadableDatabase();
            setDataDiary();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            homeRecyViewAdapter.notifyDataSetChanged();
        }
    }

}
