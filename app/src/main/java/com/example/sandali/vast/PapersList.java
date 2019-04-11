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

public class PapersList extends AppCompatActivity{

        private ListView papersList;
        private RequestQueue requestQueueForPapersList;
        private String[] paperId;
        private String[] paperName;
        private String[] paperCreator;
        private String[] paperImage;
        private String[] numOfQuestions;
        private String[] numOfHours;

        private String subId, subName;

        private DrawerLayout navigationDrawerPapersList;
        private ActionBarDrawerToggle togglePapersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papers_list);

        subId= getIntent().getStringExtra("subId");
        subName= getIntent().getStringExtra("subName");

        navigationDrawerPapersList = (DrawerLayout) findViewById(R.id.activity_papers_list);
        togglePapersList = new ActionBarDrawerToggle(PapersList.this, navigationDrawerPapersList, R.string.open, R.string.close){

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

        navigationDrawerPapersList.addDrawerListener(togglePapersList);
        togglePapersList.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final NavigationView navigationViewPapersList = (NavigationView) findViewById(R.id.nav_papers_list);
        View headerViewPapersList = navigationViewPapersList.getHeaderView(0);

        Home.setNavigationProfile(PapersList.this, headerViewPapersList);

        navigationViewPapersList.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        navigationDrawerPapersList.closeDrawers();

                        // unset item as selected to persist highlight
                        menuItem.setChecked(false);

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        Home.onNavigationItemClick(PapersList.this, menuItem);

                        return true;
                    }
                });

        papersList = (ListView) findViewById(R.id.PapersList);
        requestQueueForPapersList = Volley.newRequestQueue(PapersList.this);
        jsonParsePapersList();

    }

        private void jsonParsePapersList() {

            //String url = "http://mcq.infinisolutionslk.com/pc/appData/homePageImages.php?email=" + MainActivity.loggedEmail;
            String url = "https://vast.lk/app/getPapersOfASubject.php?email=" + MainActivity.loggedEmail + "&subId=" + subId;

            JsonObjectRequest requestPapersList = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                //Read json and assign them to local variables
                                JSONArray LnumOfQuestions = response.getJSONArray("numOfQuestions");
                                JSONArray LnumOfHours = response.getJSONArray("numOfHours");
                                JSONArray LpaperId = response.getJSONArray("paperId");
                                JSONArray LpaperName = response.getJSONArray("paperName");
                                JSONArray LpaperCreator = response.getJSONArray("paperCreator");
                                JSONArray LpaperImage = response.getJSONArray("paperImage");

                                numOfQuestions = new String[LpaperId.length()];
                                numOfHours = new String[LpaperId.length()];
                                paperId = new String[LpaperId.length()];
                                paperName = new String[LpaperId.length()];
                                paperCreator = new String[LpaperId.length()];
                                paperImage = new String[LpaperId.length()];

                                for (int i = 0; i < LpaperId.length(); i++){
                                    numOfQuestions[i] = LnumOfQuestions.get(i).toString();
                                    numOfHours[i] = LnumOfHours.get(i).toString();
                                    paperId[i] = LpaperId.get(i).toString();
                                    paperName[i] = LpaperName.get(i).toString();
                                    paperCreator[i] = LpaperCreator.get(i).toString();
                                    paperImage[i] = LpaperImage.get(i).toString();
                                }

                                //then create the grid view
                                PapersListViewAdapter papersListViewAdapter = new PapersListViewAdapter(PapersList.this, paperId, paperName, paperCreator, paperImage, numOfQuestions, numOfHours, "PapersList");
                                papersList.setAdapter(papersListViewAdapter);

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
            requestQueueForPapersList.add(requestPapersList);

        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            if (togglePapersList.onOptionsItemSelected(item)){
                return true;
            }
            return super.onOptionsItemSelected(item);

        }

}
