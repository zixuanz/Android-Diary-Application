package com.zixuanz.mysecretdiary.Fragments.Guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.zixuanz.mysecretdiary.R;
import com.zixuanz.mysecretdiary.Utils.SharedPrefUtil;
import com.zixuanz.mysecretdiary.WelcomeActivity;

/**
 * Created by Zixuan Zhao on 2/17/17.
 */

public class PasswordFrag extends Fragment {

    private EditText pwEditText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_guide_passw, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Log.d("PasswordFrag:::", "onDestroy");
        //saveInfo();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Log.d("PasswordFrag:::", "onDestroyView");
        //saveInfo();
    }

    private void initViews(View view){
        pwEditText = (EditText) view.findViewById(R.id.et_frag_gd_pw);
    }

    public void updateViews(){
        //pwEditText.setText(SharedPrefUtil.getValue(WelcomeActivity.GUIDE, WelcomeActivity.VAL_PASS, "", getContext()));
    }

    public boolean saveInfo(){
        String pw = pwEditText.getText().toString();
        Log.d("PasswordFrag:::", "saveInfo: pw--"+pw);
        if(pw.equals("")){
            return true;
        }
        return SharedPrefUtil.setValue(SharedPrefUtil.GUIDE, SharedPrefUtil.VAL_PASS, pw, getContext());
    }
}
