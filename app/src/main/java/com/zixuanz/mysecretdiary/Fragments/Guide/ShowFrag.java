package com.zixuanz.mysecretdiary.Fragments.Guide;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zixuanz.mysecretdiary.R;
import com.zixuanz.mysecretdiary.Utils.SharedPrefUtil;
import com.zixuanz.mysecretdiary.WelcomeActivity;

/**
 * Created by Zixuan Zhao on 2/20/17.
 */

public class ShowFrag extends Fragment{

    private TextView showTextView;
    private Button enterButton;

    private EnterClickListener callBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_guide_show, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBack = (EnterClickListener) context;
    }

    private void initViews(View view){
        showTextView = (TextView) view.findViewById(R.id.tv_fg_show);
        showTextView.setText(getShowText());

        enterButton = (Button) view.findViewById(R.id.btn_fg_show_enter);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onEnter();
            }
        });
    }

    private String getShowText(){
        String str = "";
        String name = SharedPrefUtil.getValue(SharedPrefUtil.GUIDE, SharedPrefUtil.VAL_NAME, "", getContext());
        String pw = SharedPrefUtil.getValue(SharedPrefUtil.GUIDE, SharedPrefUtil.VAL_PASS, "", getContext());

        if(name.equals("")){
            str += this.getString(R.string.tv_fg_gd_show_nm_no) + "/n";
        }else{
            str += this.getString(R.string.tv_fg_gd_show_nm_yes) + name +"/n";
        }

        if(pw.equals("")){
            str += this.getString(R.string.tv_fg_gd_show_pw_no);
        }else{
            str += this.getString(R.string.tv_fg_gd_show_pw_yes);
        }
        return str;
    }

    public interface EnterClickListener{
        public void onEnter();

    }

}
