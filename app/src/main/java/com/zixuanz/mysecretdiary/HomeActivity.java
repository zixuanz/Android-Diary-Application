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

import com.zixuanz.mysecretdiary.Adapters.HomeRecyViewAdapter;
import com.zixuanz.mysecretdiary.DataStructures.Home.Diary;
import com.zixuanz.mysecretdiary.Utils.DataBaseManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity{

    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private HomeRecyViewAdapter homeRecyViewAdapter;

    private FloatingActionButton newDiary;

    private DataBaseManager dbManager;
    private List<Diary> diaries;

    private boolean isNotWritten = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbManager = new DataBaseManager(getApplicationContext());
        diaries = new ArrayList<>();
        initView();
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

    private void initView() {

        initToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.rv_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        homeRecyViewAdapter = new HomeRecyViewAdapter(diaries, HomeActivity.this);
        recyclerView.setAdapter(homeRecyViewAdapter);

        newDiary = (FloatingActionButton) findViewById(R.id.fab_home_new);
        newDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, WritingActivity.class);
                intent.putExtra("isNotWritten", isNotWritten);
                startActivity(intent);
            }
        });
    }

    private void updateView(){
        homeRecyViewAdapter = new HomeRecyViewAdapter(diaries, HomeActivity.this);
        recyclerView.setAdapter(homeRecyViewAdapter);
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.tb_home);
        toolbar.inflateMenu(R.menu.menu_home_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_settings:
                        Log.d("HomeActivity:::", "Action_Setting");
                        break;
                }
                return true;
            }
        });
    }

    private void setDataDiary() {
        dbManager.queryAll(diaries);
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
