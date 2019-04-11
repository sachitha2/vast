package com.example.sandali.vast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Paper extends AppCompatActivity {

    String paperId, paperName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);

        paperId= getIntent().getStringExtra("paperId");
        paperName= getIntent().getStringExtra("paperName");

        setTitle(paperName);


        Toast.makeText(this, paperName + " is clicked", Toast.LENGTH_LONG).show();

    }
}
