package com.example.sandali.vast;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Practice extends AppCompatActivity {

    private String unitId, unitName;
    private ScrollView background;
    private TextView questionNo, question;
    RadioGroup answerGroup;
    //RadioButton answer01, answer02, answer03, answer04, answer05;
    Button nextQuestion, hint, explanation, result;
    private RequestQueue requestQueueForPractice;

    int No = 0;
    ArrayList<String> questionId = new ArrayList<String>();
    ArrayList<String> selectedAnswer = new ArrayList<String>();
    String currentQuestionId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        unitId= getIntent().getStringExtra("unitId");
        unitName= getIntent().getStringExtra("unitName");

        setTitle("Practice " + unitName);

        background = (ScrollView) findViewById(R.id.background);
        questionNo = (TextView) findViewById(R.id.questionNo);
        question = (TextView) findViewById(R.id.question);
        answerGroup = (RadioGroup) findViewById(R.id.answerGroup);
        //answer01 = (RadioButton) findViewById(R.id.answer01);
        //answer02 = (RadioButton) findViewById(R.id.answer02);
        //answer03 = (RadioButton) findViewById(R.id.answer03);
        //answer04 = (RadioButton) findViewById(R.id.answer04);
        //answer05 = (RadioButton) findViewById(R.id.answer05);
        nextQuestion = (Button) findViewById(R.id.nextQuestion);
        hint = (Button) findViewById(R.id.hint);
        explanation = (Button) findViewById(R.id.explanation);
        result = (Button) findViewById(R.id.result);

        setNextQuestion();

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextClicked();
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResultClicked();
            }
        });

        Toast.makeText(Practice.this, unitId, Toast.LENGTH_LONG).show();

    }

    private void jsonParsePractice() {

        String url = "http://mcq.infinisolutionslk.com/pc/appData/homePageImages.php?email=" + MainActivity.loggedEmail;

        JsonObjectRequest requestPractice = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            //Read json and assign them to local variables
                            String questionIdJSON = response.getString("questionIdJSON");
                            String questionJSON = response.getString("questionJSON");
                            JSONArray answersJSON = response.getJSONArray("answersJSON");
                            String[] answers = new String[answersJSON.length()];

                            for (int i = 0; i < answersJSON.length(); i++){
                                answers[i] = answersJSON.get(i).toString();

                                RadioButton radioButton = new RadioButton(Practice.this);
                                radioButton.setId(i);
                                radioButton.setText(answers[i]);
                                answerGroup.addView(radioButton);

                            }

                            questionNo.setText(No + ". ");
                            question.setText(questionJSON);
                            currentQuestionId = questionIdJSON;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueueForPractice.add(requestPractice);

    }

    private void setNextQuestion(){

        No = No + 1;

        String[] Colors = {"#fc5a5a", "#8efc58", "#59c3fc", "#defc58", "#c4669d", "#d1b57d", "#a7e2d0",};
        int random = (int)(Math.random() * 7 + 1);
        background.setBackgroundColor(Color.parseColor(Colors[random]));

        requestQueueForPractice = Volley.newRequestQueue(Practice.this);

        jsonParsePractice();

    }

    private void onNextClicked(){

        if (answerGroup.isSelected()){

            questionId.add(currentQuestionId);
            int btnId = answerGroup.getCheckedRadioButtonId();

            RadioButton btn = findViewById(btnId);
            selectedAnswer.add(btn.getText().toString());

            setNextQuestion();

        }else {
            Toast.makeText(Practice.this, "No answer selected.", Toast.LENGTH_LONG).show();
        }


    }

    private void onResultClicked(){
        //send two arrays to sever and requesting results
        //then move to the result activity with the results
    }

}
