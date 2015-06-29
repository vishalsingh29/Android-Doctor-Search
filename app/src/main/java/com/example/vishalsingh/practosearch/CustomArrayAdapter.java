package com.example.vishalsingh.practosearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<Doctor> {

    private Context context;
    private ArrayList<Doctor> doctors;

    public CustomArrayAdapter(Context context,ArrayList<Doctor> doctors){
        super(context,R.layout.activity_doctor,doctors);
        this.context = context;
        this.doctors = doctors;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent){
        Doctor doc = getItem(position);
        final LayoutInflater doctorInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View doctorView = doctorInflater.inflate(R.layout.activity_doctor, parent, false);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_doctor, parent, false);
        }

        // Lookup view for data population
        TextView name = (TextView) doctorView.findViewById(R.id.Name);
        name.setText(doc.getName());
        //TextView clinicName = (TextView) doctorView.findViewById(R.id.clinicName);
        TextView specialization = (TextView) doctorView.findViewById(R.id.Specialization);
        TextView recommendations = (TextView)doctorView.findViewById(R.id.Recommendations);
        // Populate the data into the template view using the data object


        //clinicName.setText(doc.getClinicName());
        specialization.setText(doc.getSpecialization());
        recommendations.setText(doc.getRecommendations()+" recommendations");

        // Return the completed view to render on screen

        return doctorView;
    }

}
