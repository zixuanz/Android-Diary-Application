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

public class NameFrag extends Fragment {

    private EditText nameEditText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_guide_name, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Log.d("NameFrag:::", "onDestroyView");
        //saveInfo();
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
        //Log.d("NameFrag:::", "onDestroy");
        //saveInfo();
    }

    private void initViews(View view){
        nameEditText = (EditText) view.findViewById(R.id.et_frag_gd_name);
    }

    public void updateViews(){
        //nameEditText.setText(SharedPrefUtil.getValue(WelcomeActivity.PREF_NAME, WelcomeActivity.VAL_NAME, "", getContext()));
    }

    public boolean saveInfo(){
        String name = nameEditText.getText().toString();
        Log.d("NameFrag:::","saveInfo: name--"+name);

        if(name.equals(""))
            return true;
        return SharedPrefUtil.setValue(SharedPrefUtil.PREF_NAME, SharedPrefUtil.VAL_NAME, name, getContext());
    }

}
