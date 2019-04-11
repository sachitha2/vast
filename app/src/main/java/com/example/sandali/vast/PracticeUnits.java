package com.example.sandali.vast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class PracticeUnits extends AppCompatActivity {

    private String subId, subName;
    private ListView practiceUnitsList;
    private RequestQueue requestQueueForPracticeUnits;
    private String[] unitImages;
    private String[] unitNames;
    private String[] unitId;
    private String grade;
    private String medium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_units);

        subId= getIntent().getStringExtra("subId");
        subName= getIntent().getStringExtra("subName");

        setTitle("Practice " + subName);

        practiceUnitsList = (ListView) findViewById(R.id.practiceUnitsList);
        requestQueueForPracticeUnits = Volley.newRequestQueue(PracticeUnits.this);
        jsonParsePracticeUnits();

    }

    private void jsonParsePracticeUnits() {

        String url = "http://mcq.infinisolutionslk.com/pc/appData/homePageImages.php?email=" + MainActivity.loggedEmail;

        JsonObjectRequest requestPracticeUnits = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            //Read json and assign them to local variables
                            grade = response.getString("grade");
                            medium = response.getString("medium");
                            JSONArray LunitImages = response.getJSONArray("unitImages");
                            JSONArray LunitNames = response.getJSONArray("unitNames");
                            JSONArray LunitId = response.getJSONArray("unitId");
                            unitImages = new String[LunitId.length()];
                            unitNames = new String[LunitId.length()];
                            unitId = new String[LunitId.length()];

                            for (int i = 0; i < LunitId.length(); i++){
                                unitImages[i] = LunitImages.get(i).toString();
                                unitNames[i] = LunitNames.get(i).toString();
                                unitId[i] = LunitId.get(i).toString();
                                //subNames[i] = String.valueOf(i);
                            }

                            //then create the grid view
                            ListViewAdapter practiceUnitsListViewAdapter = new ListViewAdapter(PracticeUnits.this, unitImages, unitId, unitNames, "PracticeUnits");
                            practiceUnitsList.setAdapter(practiceUnitsListViewAdapter);

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
        requestQueueForPracticeUnits.add(requestPracticeUnits);

    }
}
