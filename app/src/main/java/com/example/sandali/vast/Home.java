package com.example.sandali.vast;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Home extends AppCompatActivity {


    private ListView homeList;
    private RequestQueue requestQueueForHome;
    private String[] subImages;
    private String[] subId;
    private String[] subNames;
    private String grade;
    private String medium;

    private DrawerLayout navigationDrawerHome;
    private ActionBarDrawerToggle toggleHome;
    public static SharedPreferences sharedPreferences03;

    //navigation profile
    public static RequestQueue requestQueueForProPic;
    public static String name, profilePic;
    //Navigation navigationHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences03 = getSharedPreferences("loginInfo", Home.MODE_PRIVATE);
        navigationDrawerHome = (DrawerLayout) findViewById(R.id.activity_home);
        toggleHome = new ActionBarDrawerToggle(Home.this, navigationDrawerHome, R.string.open, R.string.close){

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /*@Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                super.onDrawerSlide(drawerView, 0); // this disables the arrow @ completed state
            }*/

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0); // this disables the animation
            }

        };


        navigationDrawerHome.addDrawerListener(toggleHome);
        toggleHome.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        final NavigationView navigationViewHome = (NavigationView) findViewById(R.id.nav_home);
        View headerViewHome = navigationViewHome.getHeaderView(0);

        setNavigationProfile(Home.this, headerViewHome);

        navigationViewHome.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        navigationDrawerHome.closeDrawers();

                        // unset item as selected to persist highlight
                        menuItem.setChecked(false);

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        onNavigationItemClick(Home.this, menuItem);

                        return true;
                    }
                });



        homeList = (ListView) findViewById(R.id.homeList);
        requestQueueForHome = Volley.newRequestQueue(Home.this);
        jsonParseHome();

    }

    private void jsonParseHome() {

        //String url = "http://mcq.infinisolutionslk.com/pc/appData/homePageImages.php?email=" + MainActivity.loggedEmail;
        String url = "https://vast.lk/app/subjectList.php?email=" + MainActivity.loggedEmail;

        JsonObjectRequest requestHome = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            //Read json and assign them to local variables
                            grade = response.getString("grade");
                            medium = response.getString("medium");
                            JSONArray LsubImages = response.getJSONArray("subIconUrl");
                            JSONArray LsubId = response.getJSONArray("subId");
                            JSONArray LsubNames = response.getJSONArray("subNames");
                            subImages = new String[LsubId.length()];
                            subId = new String[LsubId.length()];
                            subNames = new String[LsubId.length()];

                            for (int i = 0; i < LsubId.length(); i++){
                                subImages[i] = LsubImages.get(i).toString();
                                subId[i] = LsubId.get(i).toString();
                                subNames[i] = LsubNames.get(i).toString();
                            }

                            //then create the grid view
                            ListViewAdapter homeListViewAdapter = new ListViewAdapter(Home.this, subImages, subId, subNames, "Home");
                            homeList.setAdapter(homeListViewAdapter);

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

        requestQueueForHome.add(requestHome);

    }


    /*@Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }*/

    long back_pressed;

    @Override
    public void onBackPressed() {
        if (back_pressed + 1000 > System.currentTimeMillis()){
            //super.onBackPressed();

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            return;
        }
        else{
            Toast.makeText(getBaseContext(),
                    "Press again to exit!", Toast.LENGTH_SHORT)
                    .show();
        }
        back_pressed = System.currentTimeMillis();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggleHome.onOptionsItemSelected(item)){

            /*//requesting for name and profile picture

            firstName = "Sandali Senarathne";
            imgURL = "http://mcq.infinisolutionslk.com/pc/appData/images/btns/ict.png";

            //change placeholders

            userName = (TextView) findViewById(R.id.userName);
            profilePicture = (ImageView) findViewById(R.id.profilePicture);

            userName.setText(firstName);
            Picasso.with(Home.this).load(imgURL).into(profilePicture);*/

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void setNavigationProfile(Context context, View headerView){
        //requesting for name and profile picture

        String urlP = "https://vast.lk/app/getImageAndUsername.php";

        JsonObjectRequest requestProPic = new JsonObjectRequest(Request.Method.GET, urlP, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            //Read json and assign them to local variables
                            name = response.getString("username");
                            profilePic = response.getString("propic");

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

        requestQueueForProPic = Volley.newRequestQueue(context);
        requestQueueForProPic.add(requestProPic);

        //change placeholders

        String base64URL ="" + profilePic;
        String base64Image = base64URL.split(",")[0];

        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        TextView userName = (TextView) headerView.findViewById(R.id.userName);
        ImageView profilePicture = (ImageView) headerView.findViewById(R.id.profilePicture);

        profilePicture.setImageBitmap(decodedByte);
        userName.setText(Home.name);
    }

    public static void onNavigationItemClick(Context context, MenuItem menuItem){

        switch(menuItem.toString()) {
            case "Profile" :
                Intent intentProfile = new Intent(context, Profile.class);
                context.startActivity(intentProfile);
                break;

            case "Papers" :
                Intent intentSubjectList = new Intent(context, SubjectList.class);
                context.startActivity(intentSubjectList);
                break;

            case "Mark Sheet" :
                Toast.makeText(context, menuItem.toString(), Toast.LENGTH_LONG).show();
                break;

            case "High Score" :
                Toast.makeText(context, menuItem.toString(), Toast.LENGTH_LONG).show();
                break;

            case "Settings" :
                Toast.makeText(context, menuItem.toString(), Toast.LENGTH_LONG).show();
                break;

            case "Log Out" :
                /*SQLiteHandler handlerNav = new SQLiteHandler(context);

                AccountModel logOutAccount = new AccountModel(MainActivity.loggedEmail, 0);
                handlerNav.logOutAccount(logOutAccount, MainActivity.loggedEmail);*/

                //sharedPreferences03 = getSharedPreferences("loginInfo", Home.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences03.edit();

                editor.putInt("loginStatus", 0);
                editor.apply();

                Intent intentLogin = new Intent();
                intentLogin.setClass(context, MainActivity.class);
                intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intentLogin);
                break;

            case "Share" :
                //shareApplication();
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "VAST - eClz");
                System.out.println(""+R.string.email_content);
                intent.putExtra(Intent.EXTRA_TEXT, ""+context.getText(R.string.email_content)+context.getText(R.string.link));
                Intent chooser = Intent.createChooser(intent, "Share VAST via");
                context.startActivity(chooser);
                break;

            case "Help" :
                Intent intentHelp = new Intent(context, Help.class);
                context.startActivity(intentHelp);
                break;

            case "About Us" :
                Intent intentAbout = new Intent(context, About.class);
                context.startActivity(intentAbout);
                break;

            //default : // Optional
            // Statements
        }

    }

}