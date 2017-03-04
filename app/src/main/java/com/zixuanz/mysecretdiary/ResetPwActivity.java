package com.zixuanz.mysecretdiary;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zixuanz.mysecretdiary.Utils.DataBaseManager;
import com.zixuanz.mysecretdiary.Utils.SharedPrefUtil;

import java.util.Random;

public class ResetPwActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner securitySpinner;
    private EditText ans0, ans1, ans2, pw;
    private TextView que1, que2;
    private Button btnCancel;
    private Button btnEnter;

    private int spinnerPos, q1, q2;
    private final String questions[] = {
            getResources().getString(R.string.reset_q1),
            getResources().getString(R.string.reset_q2),
            getResources().getString(R.string.reset_q3),
            getResources().getString(R.string.reset_q4)
    };

    private DataBaseManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pw);

        initViews();

    }

    public void initViews(){

        ans0 = (EditText)findViewById(R.id.et_act_rpw_ans);
        ans1 = (EditText)findViewById(R.id.et_act_rpw_ans1);
        ans2 = (EditText)findViewById(R.id.et_act_rpw_ans2);
        pw = (EditText)findViewById(R.id.et_act_rpw_reset);

        que1 = (TextView)findViewById(R.id.tv_act_rpw_q1);
        que2 = (TextView)findViewById(R.id.tv_act_rpw_q2);

        securitySpinner = (Spinner)findViewById(R.id.sp_act_rpw_sl);
        securitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCancel = (Button) findViewById(R.id.btn_no_act_reset);
        btnCancel.setOnClickListener(this);

        btnEnter = (Button) findViewById(R.id.btn_yes_act_reset);
        btnEnter.setOnClickListener(this);

        setQuestion();
    }

    private void setQuestion(){
        Random gen = new Random();
        q1 = gen.nextInt(questions.length);
        do{
            q2 = gen.nextInt(questions.length);         //may have repeated numbers
        }while(q1 == q2);

        que1.setText(questions[q1]);
        que2.setText(questions[q2]);
    }

    private boolean correctAnswer(int num, String ans){
        boolean flag = false;
        switch (num){
            case 0:
                flag = true;
                break;
            case 1:
                flag = false;
                break;
            case 2:
                flag = true;
                break;
            case 3:
                String str = SharedPrefUtil.getValue(SharedPrefUtil.GUIDE, SharedPrefUtil.VAL_NAME, "N//A", getApplicationContext());
                if(str.equalsIgnoreCase(ans)){
                    flag = true;
                }else{
                    flag = false;
                }
                break;
            case 4:
                break;

            default:;
                break;
        }
        return flag;
    }

    private boolean isCorrect(){

        boolean f0, f1, f2, f3, f4;

        String a0 = ans0.getText().toString();
        String a1 = ans1.getText().toString();
        String a2 = ans2.getText().toString();

        f0 = true;
        f1 = correctAnswer(q1, a1);
        f2 = correctAnswer(q2, a2);


        return f0&&f1&&f2;
    }

    private void resetPassword(){
        if(isCorrect()){
            String str = pw.getText().toString();
            SharedPrefUtil.setValue(SharedPrefUtil.GUIDE, SharedPrefUtil.VAL_PASS, str, getApplicationContext());
            Toast.makeText(getApplicationContext(), getResources().getText(R.string.succ_reset), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), getResources().getText(R.string.fali_reset), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

        if(v.equals(btnEnter)){
            resetPassword();
        }

        if(v.equals(btnCancel)){
            finish();
        }
    }

}
