package com.example.vishalsingh.practosearch;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.content.Intent;
import android.widget.*;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;


public class SearchResults extends ActionBarActivity {

    ArrayList<Doctor> docs = new ArrayList<Doctor>();
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        Intent query = getIntent();
        String locality = query.getStringExtra("locality");
        String speciality = query.getStringExtra("speciality");
        String city = query.getStringExtra("city");
        //Toast.makeText(getApplicationContext(),"city = "+city,Toast.LENGTH_SHORT);
        locality = locality.replaceAll(" ","%20");
        speciality = speciality.replaceAll(" ","%20");
        //String url = "http://192.168.1.110:5000/"+ city +'/' + locality + "/" + speciality;
        String url = "http://10.0.2.2:5000/"+ city +'/' + locality + "/" + speciality;
        Log.v(Home.class.getSimpleName(), url);
        //Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
        JsonObjectRequest jsreq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                //Toast.makeText(getApplicationContext(),response.toString() + "",Toast.LENGTH_LONG).show();
                                JSONArray results = response.getJSONArray("results");
                                final ArrayList<Doctor> docs = new ArrayList<Doctor>();
                                for (int i = 0; i < results.length(); i++) {
                                    JSONObject index = (JSONObject)results.get(i);

                                    //JSON for each doctor
                                    String id = index.getString("id");
                                    String nameOfDoctor = index.getString("name");
                                    String email = index.getString("email");
                                    String phone_number = index.getString("phone_number");
                                    int experience_in_year = index.getInt("experience_in_year");
                                    int recommendations = index.getInt("recommendations");
                                    String area = index.getString("area");
                                    String specializationS = index.getString("specialization");

                                    Doctor doc = new Doctor();
                                    doc.setName(nameOfDoctor);
                                    doc.setPhoneNumber(phone_number);
                                    doc.setEmail(email);
                                    doc.setExperience_in_years(experience_in_year);
                                    doc.setArea(area);
                                    doc.setId(id);
                                    Log.v(SearchResults.class.getSimpleName(), id);
                                    doc.setRecommendations(recommendations);
                                    doc.setSpecialization(specializationS);
                                    //doc.setClinicName(clinicName);
                                    docs.add(doc);

                                }

                                if(docs.size() >= 1){
                                    CustomArrayAdapter arr = new CustomArrayAdapter(getApplicationContext(), docs);
                                    ListView listView1 = (ListView)findViewById(R.id.list);
                                    listView1.setAdapter(arr);

                                    listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                                            //Toast.makeText(getApplicationContext(),"ABCD", Toast.LENGTH_SHORT).show();
                                            Intent docInfo = new Intent(getApplicationContext(), DocInfo.class);
                                            docInfo.putExtra("docId",docs.get(position).getId());
                                            startActivity(docInfo);
                                        }
                                    });
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"No results for that query!!",Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),
                                        "Error::  " + e.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),
                            error.getMessage() + " : Ermsg2", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(jsreq);




       }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
