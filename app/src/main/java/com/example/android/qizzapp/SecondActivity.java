package com.example.android.qizzapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    static final String key1 = "totalCheckedAnd", key2 = "Focus";
    private int totalCorrectAnd = 0, totalCheckedAnd = 0;
    protected EditText ans4And;
    protected RadioButton a1And, a2And;
    protected Button btnToFirst, solutionBtn;
    protected View focusedChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        a1And = (RadioButton) findViewById(R.id.q1_a2_android);
        a2And = (RadioButton) findViewById(R.id.q2_a1_android);
        ans4And = (EditText) findViewById(R.id.q4_a4_android);

        btnToFirst = (Button) findViewById(R.id.btnFirstScreen1);
        solutionBtn = (Button) findViewById(R.id.btnSubmita);

        btnToFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        solutionBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                checkAnswer();
                Resources res = getResources();
                String summary = res.getString(R.string.dialog, totalCorrectAnd);
                customDialog("Solution", summary);
                totalCorrectAnd = 0;
            }
        });
    }


    /**
     * This method is right before we change orientation, to preserve values
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putInt(key1, totalCheckedAnd);
        focusedChild = (ScrollView) findViewById(R.id.rootView1);
        focusedChild.requestFocus();
        outState.putInt(key2, focusedChild.getId());

    }

    /**
     * This method is called to display values in the UI which were saved
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        totalCheckedAnd = savedInstanceState.getInt(key1);
        int focusID = savedInstanceState.getInt(key2, View.NO_ID);
        focusedChild = findViewById(focusID);
        focusedChild.requestFocus();
    }

    /**
     * This method is used to check answers
     */
    public void checkAnswer() {

        boolean checked1 = a1And.isChecked(),
                checked2 = a2And.isChecked();

        if (checked1) {
            totalCorrectAnd++;
        }
        if (checked2) {
            totalCorrectAnd++;
        }

        if (totalCheckedAnd == 3) {
            totalCorrectAnd++;
        }

        read_Answer();

    }

    /**
     * This method is called when checkbox is clicked
     */
    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.q3_a1_android:
                if (checked)
                    totalCheckedAnd += 1;
                else
                    totalCheckedAnd -= 1;
                break;
            case R.id.q3_a2_android:
                if (checked)
                    totalCheckedAnd -= 1;
                else
                    totalCheckedAnd += 1;
                break;
            case R.id.q3_a3_android:
                if (checked)
                    totalCheckedAnd += 1;
                else
                    totalCheckedAnd -= 1;
                break;
            case R.id.q3_a4_android:
                if (checked)
                    totalCheckedAnd += 1;
                else
                    totalCheckedAnd -= 1;
                break;
        }
    }

    /**
     * This method is used to read from EditText View
     */
    private void read_Answer() {

        String ans = ans4And.getText().toString();

        if (ans.equals("true") || ans.equals("True") || ans.equals("TRUE")) {

            totalCorrectAnd += 0;

        } else if (ans.equals("false") || ans.equals("False") || ans.equals("FALSE")) {

            totalCorrectAnd += 1;

        } else {

            toastMessage("Please input true or false.");
        }

    }

    /**
     * This method is called when Cancel is clicked on dialog
     */
    private void cancelMethod() {

        toastMessage("Give it another try.");
    }

    /**
     * This method is called when Ok is clicked on dialog
     */
    private void okMethod() {

        toastMessage("Click on Main Page.");
    }

    /**
     * This method is used to create custom dialog
     */
    public void customDialog(String title, String message) {
        final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(this);
        builderSingle.setIcon(R.mipmap.ic_notification);
        builderSingle.setTitle(title);
        builderSingle.setMessage(message);

        builderSingle.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cancelMethod();
                    }
                });

        builderSingle.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        okMethod();
                    }
                });


        builderSingle.show();
    }

    /**
     * This method is used to create toast
     */
    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
