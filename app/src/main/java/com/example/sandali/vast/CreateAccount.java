package com.example.sandali.vast;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class CreateAccount extends AppCompatActivity {

    //Button show;
    //EditText edit_password;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar

        mDisplayDate = (Button) findViewById(R.id.btnDatePick);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CreateAccount.this,
                        android.R.style.Theme_DeviceDefault_Dialog,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                }
            });

//        show = (Button) findViewById(R.id.show);  //Show button in password
//        edit_password = (EditText) findViewById(R.id.password);   //Password EditText
//        show.setOnClickListener(new SignUpActivity.showOrHidePassword());//invoking the showOrHidePassword class to show the password
    }

        //class to show or hide password on button click in main activity
//    class showOrHidePassword implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            if (show.getText().toString() == "SHOW") {
//                edit_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                show.setText("HIDE");
//
//            } else {
//
//                edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                show.setText("SHOW");
//            }
//        }
//    }




}
