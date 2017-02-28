package com.zixuanz.mysecretdiary.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.TextView;

import com.zixuanz.mysecretdiary.R;

/**
 * Created by Zixuan Zhao on 2/27/17.
 */

public class ResetPasswordFragment extends BottomSheetDialogFragment {

    private BottomSheetBehavior behavior;
    private BottomSheetDialog dialog;

    private TextView que1, que2;

    private final String[] questions = {"aaa", "bbb"};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.dialog_reset_pw, null);
        dialog.setContentView(view);
        initViews(view);
        behavior = BottomSheetBehavior.from((View) view.getParent());
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void initViews(View view){
        que1 = (TextView) view.findViewById(R.id.tv_act_pw_q1);
        que2 = (TextView) view.findViewById(R.id.tv_act_pw_q2);
        que1.setText(questions[0]);
        que2.setText(questions[1]);

    }

}
