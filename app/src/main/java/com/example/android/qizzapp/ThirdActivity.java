package com.example.android.qizzapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private static final String key1 = "totalCheckedJav", key2 = "Focus";
    private int totalCorrectJav = 0, totalCheckedJav = 0;
    protected EditText ans4Jav;
    protected RadioButton a1Jav, a2Jav;
    protected Button btnToFirst, solutionBtn;
    protected View focusedChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        a1Jav = (RadioButton) findViewById(R.id.q1_a3_java);
        a2Jav = (RadioButton) findViewById(R.id.q2_a2_java);
        ans4Jav = (EditText) findViewById(R.id.q4_a4_java);

        btnToFirst = (Button) findViewById(R.id.btnFirstScreen2);
        solutionBtn = (Button) findViewById(R.id.btnSubmitj);

        btnToFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        solutionBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                checkAnswer();
                Resources res = getResources();
                String summary = res.getString(R.string.dialog, totalCorrectJav);
                customDialog("Solution", summary);
                totalCorrectJav = 0;
            }
        });
    }

    /**
     * This method is right before we change orientation, to preserve values
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putInt(key1, totalCheckedJav);
        focusedChild = (ScrollView) findViewById(R.id.rootView2);
        focusedChild.requestFocus();
        outState.putInt(key2, focusedChild.getId());

    }

    /**
     * This method is called to display values in the UI which were saved
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        totalCheckedJav = savedInstanceState.getInt(key1);
        int focusID = savedInstanceState.getInt(key2, View.NO_ID);
        focusedChild = findViewById(focusID);
        focusedChild.requestFocus();
    }

    /**
     * This method is used to check answers
     */
    public void checkAnswer() {

        boolean checked1 = a1Jav.isChecked(),
                checked2 = a2Jav.isChecked();

        if (checked1) {
            totalCorrectJav++;
        }
        if (checked2) {
            totalCorrectJav++;
        }

        if (totalCheckedJav == 3) {
            totalCorrectJav++;
        }

        read_Answer();

    }

    /**
     * This method is called when checkbox is clicked
     */
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.q3_a1_java:
                if (checked)
                    totalCheckedJav += 1;
                else
                    totalCheckedJav -= 1;
                break;
            case R.id.q3_a2_java:
                if (checked)
                    totalCheckedJav -= 1;
                else
                    totalCheckedJav += 1;
                break;
            case R.id.q3_a3_java:
                if (checked)
                    totalCheckedJav += 1;
                else
                    totalCheckedJav -= 1;
                break;
            case R.id.q3_a4_java:
                if (checked)
                    totalCheckedJav += 1;
                else
                    totalCheckedJav -= 1;
                break;
        }
    }

    /**
     * This method is used to read from EditText View
     */
    private void read_Answer() {

        String ans = ans4Jav.getText().toString();

        if (ans.equals("true") || ans.equals("True") || ans.equals("TRUE")) {

            totalCorrectJav += 0;

        } else if (ans.equals("false") || ans.equals("False") || ans.equals("FALSE")) {

            totalCorrectJav += 1;

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
