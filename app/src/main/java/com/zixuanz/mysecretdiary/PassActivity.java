package com.zixuanz.mysecretdiary;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zixuanz.mysecretdiary.Fragments.ResetPasswordFragment;
import com.zixuanz.mysecretdiary.Utils.SharedPrefUtil;

public class PassActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editPassWord;
    private TextView textForget;
    private Button btnCancel;
    private Button btnEnter;

    //private ResetPasswordFragment resetFrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        initViews();
    }

    private void initViews(){
        editPassWord = (EditText) findViewById(R.id.et_act_pw);

        textForget = (TextView) findViewById(R.id.tv_act_pw_forget);
        textForget.setOnClickListener(this);

        btnCancel = (Button) findViewById(R.id.btn_no_act_pw);
        btnCancel.setOnClickListener(this);

        btnEnter = (Button) findViewById(R.id.btn_yes_act_pw);
        btnEnter.setOnClickListener(this);

        //resetFrag = new ResetPasswordFragment();

    }

    @Override
    public void onClick(View v) {

        if(v.equals(textForget)){
            Intent intent = new Intent(this, ResetPwActivity.class);
            startActivity(intent);
        }

        if(v.equals(btnEnter)){
            if(canEnter()){
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
            }else{
                new AlertDialog.Builder(this)
                        .setTitle(this.getString(R.string.wanning))
                        .setMessage(this.getString(R.string.dia_msg))
                        .setPositiveButton(this.getString(R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();

            }

        }

        if(v.equals(btnCancel)){
            Toast.makeText(this,this.getString(R.string.bye), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean canEnter(){
        String inPw = editPassWord.getText().toString();
        String savePw = SharedPrefUtil.getValue(SharedPrefUtil.GUIDE, SharedPrefUtil.VAL_PASS, "ERROR", this);

        return inPw.equals(savePw);
    }

}
