package com.zixuanz.mysecretdiary.Fragments.Guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.zixuanz.mysecretdiary.R;
import com.zixuanz.mysecretdiary.Utils.SharedPrefUtil;
import com.zixuanz.mysecretdiary.WelcomeActivity;

/**
 * Created by Zixuan Zhao on 2/17/17.
 */

public class PasswordFrag extends Fragment {

    private EditText pwEditText;
    private Spinner securtySpinner;
    private EditText answerEditText;

    private int spinnerPos = 0;

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
        securtySpinner = (Spinner) view.findViewById(R.id.sp_fg_gd_sl);
        answerEditText = (EditText) view.findViewById(R.id.et_fg_gd_ans);

        securtySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void updateViews(){
        //pwEditText.setText(SharedPrefUtil.getValue(WelcomeActivity.GUIDE, WelcomeActivity.VAL_PASS, "", getContext()));
    }

    public boolean saveInfo(){
        boolean flag1, flag2 = true;
        boolean flag3 = true;

        String str = pwEditText.getText().toString();
        Log.d("PasswordFrag:::", "saveInfo: pw--"+str);
        if(str.equals("")){
            return true;
        }
        flag1 = SharedPrefUtil.setValue(SharedPrefUtil.GUIDE, SharedPrefUtil.VAL_PASS, str, getContext());

        if(spinnerPos != 0){
            flag2 = SharedPrefUtil.setValue(SharedPrefUtil.GUIDE, SharedPrefUtil.VAL_QUE, spinnerPos, getContext());
            Log.d("PasswordFrag:::", "saveInfo: pos--"+spinnerPos);
            str = answerEditText.getText().toString();
            flag3 = SharedPrefUtil.setValue(SharedPrefUtil.GUIDE, SharedPrefUtil.VAL_ANSWER, str, getContext());
            Log.d("PasswordFrag:::", "saveInfo: answer--"+str);
        }

        return flag1 && flag2 && flag3;
    }
}
