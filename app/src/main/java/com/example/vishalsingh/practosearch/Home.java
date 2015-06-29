package com.example.vishalsingh.practosearch;

import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.AutoCompleteTextView;
import android.content.*;
import java.lang.*;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


public class Home extends ActionBarActivity {


    public void goHome(){
        String [] cities = {"Bangalore", "Delhi", "Mumbai", "Jaipur", "Udaipur"};

        String [] localities = {"BTM","Whitefield","JP Nagar","Jayanagar","Koramangala",
                "Indira Nagar","Pratap Nagar","Gandhi Nagar","Hiran Magri","Yeshwantpur",
                "Marathalli"};

        String [] specialities = {"Dentist","Cardiologist","Surgeon","Heart Specialist",
                "Neurologist","Dermatologist","Brain Surgeon","Urologist","Psychologist",
                "Psychiatrist","Ayurveda"};

        final AutoCompleteTextView v1 = (AutoCompleteTextView)findViewById(R.id.locality);

        final AutoCompleteTextView v2 = (AutoCompleteTextView)findViewById(R.id.speciality);

        final AutoCompleteTextView v0 = (AutoCompleteTextView)findViewById(R.id.city);

        ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinner_dropdown, cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinner_dropdown, localities);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinner_dropdown, specialities);


        v0.setAdapter(adapter0);

        v2.setAdapter(adapter2);

        v1.setAdapter(adapter);


        v2.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AutoCompleteTextView a1 = (AutoCompleteTextView) findViewById(R.id.locality);
                AutoCompleteTextView a2 = (AutoCompleteTextView) findViewById(R.id.speciality);
                AutoCompleteTextView a3 = (AutoCompleteTextView) findViewById(R.id.city);
                String locality = a1.getText().toString();
                String speciality = a2.getText().toString();
                String city = a3.getText().toString();
                Intent query = new Intent(getApplicationContext(), SearchResults.class);
                query.putExtra("locality", locality);
                query.putExtra("speciality", speciality);
                query.putExtra("city", city);
                Toast.makeText(getApplicationContext(),locality + speciality + city,Toast.LENGTH_SHORT);
                startActivity(query);

            }

        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new CountDownTimer(3000,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){
                //set the new Content of your activity
                setContentView(R.layout.activity_home);
                goHome();
            }
        }.start();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
