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

public class SubjectList extends AppCompatActivity {

    private ListView subjectList;
    private RequestQueue requestQueueForSubjectList;
    private String[] subImages;
    private String[] subId;
    private String[] subNames;
    private String grade;
    private String medium;

    private DrawerLayout navigationDrawerSubjectList;
    private ActionBarDrawerToggle toggleSubjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);

        navigationDrawerSubjectList = (DrawerLayout) findViewById(R.id.activity_subject_list);
        toggleSubjectList = new ActionBarDrawerToggle(SubjectList.this, navigationDrawerSubjectList, R.string.open, R.string.close){

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


        navigationDrawerSubjectList.addDrawerListener(toggleSubjectList);
        toggleSubjectList.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final NavigationView navigationViewSubjectList = (NavigationView) findViewById(R.id.nav_subject_list);
        View headerViewSubjectList = navigationViewSubjectList.getHeaderView(0);

        Home.setNavigationProfile(SubjectList.this, headerViewSubjectList);

        navigationViewSubjectList.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        navigationDrawerSubjectList.closeDrawers();

                        // unset item as selected to persist highlight
                        menuItem.setChecked(false);

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        Home.onNavigationItemClick(SubjectList.this, menuItem);

                        return true;
                    }
                });

        subjectList = (ListView) findViewById(R.id.subjectList);
        requestQueueForSubjectList = Volley.newRequestQueue(SubjectList.this);
        jsonParseSubjectList();

    }

    private void jsonParseSubjectList() {

        //String url = "http://mcq.infinisolutionslk.com/pc/appData/homePageImages.php?email=" + MainActivity.loggedEmail;
        String url = "https://vast.lk/app/subjectList.php?email=" + MainActivity.loggedEmail;

        JsonObjectRequest requestSubjectList = new JsonObjectRequest(Request.Method.GET, url, null,
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
                                //subNames[i] = String.valueOf(i);
                            }

                            //then create the grid view
                            ListViewAdapter subjectListViewAdapter = new ListViewAdapter(SubjectList.this, subImages, subId, subNames, "SubjectList");
                            subjectList.setAdapter(subjectListViewAdapter);

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
        requestQueueForSubjectList.add(requestSubjectList);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggleSubjectList.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
