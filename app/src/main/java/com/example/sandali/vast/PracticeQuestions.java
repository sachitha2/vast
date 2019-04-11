package com.example.sandali.vast;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PracticeQuestions extends AppCompatActivity {

    private ListView practiceQuestionsList;
    private RequestQueue requestQueueForPracticeQuestions;
    private String[] subImages;
    private String[] subId;
    private String[] subNames;
    private String grade;
    private String medium;

    private DrawerLayout navigationDrawerPracticeQuestions;
    private ActionBarDrawerToggle togglePracticeQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_questions);

        navigationDrawerPracticeQuestions = (DrawerLayout) findViewById(R.id.activity_practice_questions);
        togglePracticeQuestions = new ActionBarDrawerToggle(PracticeQuestions.this, navigationDrawerPracticeQuestions, R.string.open, R.string.close){

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


        navigationDrawerPracticeQuestions.addDrawerListener(togglePracticeQuestions);
        togglePracticeQuestions.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final NavigationView navigationViewPracticeQuestions = (NavigationView) findViewById(R.id.nav_practice_questions);
        View headerViewPracticeQuestions = navigationViewPracticeQuestions.getHeaderView(0);

        Home.setNavigationProfile(PracticeQuestions.this, headerViewPracticeQuestions);

        navigationViewPracticeQuestions.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        navigationDrawerPracticeQuestions.closeDrawers();

                        // unset item as selected to persist highlight
                        menuItem.setChecked(false);

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        Home.onNavigationItemClick(PracticeQuestions.this, menuItem);

                        return true;
                    }
                });

        practiceQuestionsList = (ListView) findViewById(R.id.practiceQuestionsList);
        requestQueueForPracticeQuestions = Volley.newRequestQueue(PracticeQuestions.this);
        jsonParsePracticeQuestions();

    }

    private void jsonParsePracticeQuestions() {

        String url = "http://mcq.infinisolutionslk.com/pc/appData/homePageImages.php?email=" + MainActivity.loggedEmail;

        JsonObjectRequest requestPracticeQuestions = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            //Read json and assign them to local variables
                            grade = response.getString("grade");
                            medium = response.getString("medium");
                            JSONArray LsubImages = response.getJSONArray("subIconUrl");
                            JSONArray LsubId = response.getJSONArray("subId");
                            //JSONArray LsubNames = response.getJSONArray("subNames");
                            subImages = new String[LsubId.length()];
                            subId = new String[LsubId.length()];
                            subNames = new String[LsubId.length()];

                            for (int i = 0; i < LsubId.length(); i++){
                                subImages[i] = LsubImages.get(i).toString();
                                subId[i] = LsubId.get(i).toString();
                                //subNames[i] = LsubNames.get(i).toString();
                                subNames[i] = String.valueOf(i);
                            }

                            //then create the grid view
                            ListViewAdapter practiceQuestionsListViewAdapter = new ListViewAdapter(PracticeQuestions.this, subImages, subId, subNames, "PracticeQuestions");
                            practiceQuestionsList.setAdapter(practiceQuestionsListViewAdapter);

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
        requestQueueForPracticeQuestions.add(requestPracticeQuestions);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (togglePracticeQuestions.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
