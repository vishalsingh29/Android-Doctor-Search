package com.example.vishalsingh.practosearch;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
import java.util.List;


public class DocInfo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_info);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        Intent current = getIntent();
        String docId = current.getStringExtra("docId");
        //String url = "http://192.168.1.110:5000/" + docId;
        String url = "http://10.0.2.2:5000/doctor/" + docId;
        Log.v(DocInfo.class.getSimpleName(), url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Log.v(Home.class.getSimpleName(), response.toString());
                            JSONObject jsob = response.getJSONObject("results");

                            String doctorName = jsob.getString("name");
                            TextView name = (TextView)findViewById(R.id.NameOfDoctor);
                            name.setText(doctorName);
                            //Toast.makeText(getApplicationContext(),doctorName,Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),doctorName,Toast.LENGTH_SHORT).show();
                            String doctorEmail = jsob.getString("email");
                            TextView email = (TextView)findViewById(R.id.Email);
                            email.setText(doctorEmail);

                            String doctorPhone = jsob.getString("phone_number");
                            TextView phone = (TextView)findViewById(R.id.DoctorPhone);
                            phone.setText(doctorPhone);

                            int doctorExperienceInYear = jsob.getInt("experience_in_year");
                            TextView experience = (TextView)findViewById(R.id.DoctorExperience);
                            experience.setText(doctorExperienceInYear + " Years");

                            int recommendation = jsob.getInt("recommendations");
                            TextView recommendations = (TextView)findViewById(R.id.DoctorRecommendations);
                            recommendations.setText(recommendation + " recommendations");

                            String Area = jsob.getString("area");
                            TextView area = (TextView)findViewById(R.id.DoctorArea);
                            area.setText(Area);

                            String City = jsob.getString("city");
                            TextView city = (TextView)findViewById(R.id.DoctorCity);
                            city.setText(City);

                            String _Specialization = jsob.getString("specialization");
                            TextView specializationsList = (TextView)findViewById(R.id.DoctorSpecialization);
                            specializationsList.setText(_Specialization);

                            JSONArray clinicsObj = (JSONArray)jsob.getJSONArray("clinics");
                            //JSONArray reviewsObj = (JSONArray)jsob.getJSONArray("reviews");
                            JSONObject educationObj = (JSONObject)jsob.getJSONObject("education");

                            String services = jsob.getString("services");
                            String docEducation = educationObj.getString("college") + ", " + educationObj.getString("degree") + ", " + educationObj.getString("year");
                            //Toast.makeText(getApplicationContext(),docEducation,Toast.LENGTH_SHORT).show();

                            ArrayList<String> listOfClinics = new ArrayList<String>();

                            for(int i=0;i<clinicsObj.length();i++){
                                JSONObject clinicname = (JSONObject)clinicsObj.get(i);
                                String clinicNameString = clinicname.getString("clinicName");
                                String clinicArea = clinicname.getString("area");
                                String clinicCity = clinicname.getString("city");
                                String fees = clinicname.getString("fees");
                                String timings = clinicname.getString("timings");
                                String NameOfClinic = clinicNameString + ", " + clinicArea + ", " + clinicCity;
                                listOfClinics.add(NameOfClinic);
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                    R.layout.spinner_dropdown, listOfClinics);

                            ListView listClinic = (ListView)findViewById(R.id.clinics);
                            TextView edu = (TextView)findViewById(R.id.Doceducations);
                            edu.setText(docEducation);
                            listClinic.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TextView mTextView = (TextView)findViewById(R.id.errMsgs);
                        //mTextView.setText("That didn't work!");

                    }
                });

       queue.add(jsObjRequest);
        ((Button)findViewById(R.id.callButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView phone = (TextView)findViewById(R.id.DoctorPhone);
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                String phoneNumber = phone.getText().toString();
                callIntent.setData(Uri.parse("tel:"+phoneNumber));
                try {
                    startActivity(callIntent);
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Unable to call "+phoneNumber,Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doc_info, menu);
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
