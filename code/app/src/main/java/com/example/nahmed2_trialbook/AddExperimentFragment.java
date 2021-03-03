package com.example.nahmed2_trialbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/*    Lab 3 Instructions - FragmentsFile. 2021-02-01. Public Domain.
        https://eclass.srv.ualberta.ca/mod/resource/view.php?id=4829653
*/

public class AddExperimentFragment extends DialogFragment {
    private EditText experimentName;
    private EditText dateName;
    private OnFragmentInteractionListener listener;

    /* Ok pressed interface */
    public interface OnFragmentInteractionListener{
        void onOkPressed(String name, String date);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    +" must implement OnFragmentInteractionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_experiment_layout,null);
        experimentName = view.findViewById(R.id.experiment_name_editText);
        dateName = view.findViewById(R.id.date_editText);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add Experiment")

                .setNegativeButton("Cancel",null)
                //exit fragment

                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //takes user input and returns it to main
                        String name = experimentName.getText().toString();
                        String date = dateName.getText().toString();

                        listener.onOkPressed(name,date);
                    }
                })
                .create();
    }
}
