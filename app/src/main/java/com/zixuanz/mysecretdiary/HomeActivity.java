package com.zixuanz.mysecretdiary;

import android.content.Intent;
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
import android.view.Window;

import com.zixuanz.mysecretdiary.Adapters.HomeRecyViewAdapter;
import com.zixuanz.mysecretdiary.DataStructures.Home.Diary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity{

    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private HomeRecyViewAdapter homeRecyViewAdapter;

    private FloatingActionButton newDiary;

    private List<Diary> diaries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initDataDiary();
        initView();
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
                startActivity(intent);
            }
        });
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

    private void initDataDiary() {
        diaries = new ArrayList<>(8);
        diaries.add(new Diary(new Date(), "Hello", "happy", "nicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenicenice"));
        diaries.add(new Diary(new Date(), "NULL", "NULL", "NULL"));
        diaries.add(new Diary(new Date(), "NULL", "NULL", "NULL"));
        diaries.add(new Diary(new Date(), "NULL", "NULL", "NULL"));
        diaries.add(new Diary(new Date(), "NULL", "NULL", "NULL"));
        diaries.add(new Diary(new Date(), "NULL", "NULL", "NULL"));
        diaries.add(new Diary(new Date(), "NULL", "NULL", "NULL"));
        diaries.add(new Diary(new Date(), "NULL", "NULL", "NULL"));
    }

}
