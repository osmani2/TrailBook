package com.example.nahmed2_trialbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/*
        Lab 3 Instructions - CustomListFile. 2021-02-01. Public Domain.
        https://eclass.srv.ualberta.ca/mod/resource/view.php?id=4829652
*/

public class CustomList extends ArrayAdapter<Experiment> {
    private ArrayList<Experiment> experiments;
    private Context context;

    public CustomList(Context context, ArrayList<Experiment> experiments) {
        super(context, 0,experiments);
        this.experiments = experiments;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View view = convertView;

        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.content,parent,false);
        }

        Experiment experiment = experiments.get(position);

        TextView experimentName = view.findViewById(R.id.experiment_text);
        TextView dateName = view.findViewById(R.id.date_text);

        experimentName.setText(experiment.getName());
        dateName.setText(experiment.getDate());

        return view;
    }
}
