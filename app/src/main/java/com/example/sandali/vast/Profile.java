package com.example.sandali.vast;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

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

public class Profile extends AppCompatActivity {

    ImageView ProfilePic;
    String Name, Email, Gender, NIC, DoB, Address, School, District, ProfilePicURL;
    TextView name, email, gender, nic, dob, address, school, district;
    private RequestQueue requestQueueForProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = (TextView) findViewById(R.id.Name);
        email = (TextView) findViewById(R.id.Email);
        gender = (TextView) findViewById(R.id.Gender);
        nic = (TextView) findViewById(R.id.NIC);
        dob = (TextView) findViewById(R.id.DoB);
        address = (TextView) findViewById(R.id.Address);
        school = (TextView) findViewById(R.id.School);
        district = (TextView) findViewById(R.id.District);
        ProfilePic = (ImageView) findViewById(R.id.ProfilePic);

        requestQueueForProfile = Volley.newRequestQueue(Profile.this);
        jsonParseProfile();

    }

    private void jsonParseProfile() {

        //String url = "http://mcq.infinisolutionslk.com/pc/appData/homePageImages.php?email=" + MainActivity.loggedEmail;
        final String url = "https://vast.lk/app/getProfileData.php?email=" + MainActivity.loggedEmail;
        JsonObjectRequest requestProfile = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            //Read json and assign them to local variables
                            Name = response.getString("name");
                            Email = response.getString("email");
                            Gender = response.getString("gender");
                            NIC = response.getString("nic");
                            DoB = response.getString("dob");
                            Address = response.getString("address");
                            School = response.getString("School");
                            District = response.getString("Distric");
                            ProfilePicURL = response.getString("propic");

                            String base64String ="" + ProfilePicURL;
                            String base64Image = base64String.split(",")[1];

                            byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                            ProfilePic.setImageBitmap(decodedByte);

                            name.setText(Name);
                            email.setText(Email);
                            gender.setText(Gender);
                            nic.setText(NIC);
                            dob.setText(DoB);
                            address.setText(Address);
                            school.setText(School);
                            district.setText(District);

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
        requestQueueForProfile.add(requestProfile);

    }

}
