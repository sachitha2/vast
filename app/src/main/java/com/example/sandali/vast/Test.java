package com.example.sandali.vast;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Test extends AppCompatActivity {

    private RequestQueue loginQueue;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnCreateAccount;
    private Button btnLogin;
    private Button btnShow;
    SQLiteHandler handler;

    public static String loggedEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        //getSupportActionBar().hide(); // hide the title bar

        //setContentView(R.layout.activity_main);

        //if(/*there is no logged account*/){

        //    edtEmail = (EditText) findViewById(R.id.edtEmail);
         //   edtPassword = (EditText) findViewById(R.id.edtPassword);
        //    btnShow = (Button) findViewById(R.id.btnShow);
        //    btnLogin = (Button) findViewById(R.id.btnLogin);
        //    btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);

        //    loginQueue = Volley.newRequestQueue(MainActivity.this);

        /*    btnCreateAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intentCreateAcc = new Intent();
                    intentCreateAcc.setClass(MainActivity.this, CreateAccount.class);

                    startActivity(intentCreateAcc);
                }
            });*/


        /*    btnShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showOrHidePassword();// Calling showOrHidePassword method to show the password
                }
            });

        }else{
            SharedPreferences sharedPreferences01 = getSharedPreferences("loginInfo", MainActivity.this.MODE_PRIVATE);
            loggedEmail = sharedPreferences01.getString("email", "");

            Intent intentHome = new Intent();
            intentHome.setClass(MainActivity.this, Home.class);
            intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intentHome);

        }*/

    }

    /*public void onLoginClick(View view){

        if (isValidEmail(edtEmail.getText())){
            if (!TextUtils.isEmpty(edtPassword.getText())){

                jsonParse();

            }else {
                Toast.makeText(MainActivity.this,"Enter Password", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(MainActivity.this,"Invalid e-mail", Toast.LENGTH_LONG).show();
        }

    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private void jsonParse(){

        String url = "https://vast.lk/app/login.php?uName="+ edtEmail.getText() + "&uPass="+ edtPassword.getText();

        JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{

                            Integer status = response.getInt("status");
                            String message = response.getString("message");
                            //String logOutTime = response.getString("logOutTime");

                            if (status == 1){

                                //Check if this email is available in cache

                                if (lookForEmail(MainActivity.this).equals(edtEmail.getText())){

                                    //update login status

                                    SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", MainActivity.this.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.putInt("loginStatus", 1);
                                    editor.apply();

                                }else{

                                    //create cache

                                    SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", MainActivity.this.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.putString("emali", edtEmail.getText().toString());
                                    editor.putInt("loginStatus", 1);
                                    editor.apply();

                                }

                                SharedPreferences sharedPreferences02 = getSharedPreferences("loginInfo", MainActivity.this.MODE_PRIVATE);
                                loggedEmail = sharedPreferences02.getString("email", "");

                                Intent intentHome = new Intent();
                                intentHome.setClass(MainActivity.this, Home.class);
                                intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                //make the progress bar invisible before go to the home screen
                                //progressBar.setVisibility(View.GONE);

                                startActivity(intentHome);

                            }else if (status == 0){

                                //make the progress bar invisible before show the error message
                                //progressBar.setVisibility(View.GONE);
                                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();

                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        loginQueue.add(loginRequest);

    }

    public String lookForEmail(Context context){
        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", context.MODE_PRIVATE);
        return sharedPreferences.getString("email", "");
    }

    public void showOrHidePassword(){

        if (btnShow.getText().toString() == "SHOW") {
            edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            btnShow.setText("HIDE");

        } else {

            edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            btnShow.setText("SHOW");
        }

    }

    public void onSignUpClick(View v){
        Intent myIntent = new Intent(MainActivity.this, CreateAccount.class);
        startActivity(myIntent);
    }

    long back_pressed;

    @Override
    public void onBackPressed() {
        if (back_pressed + 1000 > System.currentTimeMillis()){
            super.onBackPressed();
            //System.exit(0);     old code
            return;             // new code
        }
        else{
            Toast.makeText(getBaseContext(),
                    "Press again to exit!", Toast.LENGTH_SHORT)
                    .show();
        }
        back_pressed = System.currentTimeMillis();
    }*/

}
