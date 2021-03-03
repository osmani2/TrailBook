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

/* After long clicking an item in list
*   Allows user to "Apply" edits or "Delete" experiment */
public class EditExperimentFragment extends DialogFragment {
    private EditText experimentName;
    private OnFragmentInteractionListener listener;

    private int position;

    public interface OnFragmentInteractionListener{
        void onEditPressed(String newName, int position);
        void onDeletePressed(int position );
    }

    /* Keeps track of index of experiment object to be deleted/edited */
    public EditExperimentFragment(int position) {
        this.position=position;
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.edit_experiment_layout,null);
        experimentName = view.findViewById(R.id.experiment_name_editText);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit/Delete Experiment")

                /* Returns position to Main */
                .setNegativeButton("Delete",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDeletePressed(position);
                    }
                })

                /* Returns input to Main */
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String experiment = experimentName.getText().toString();
                        listener.onEditPressed(experiment,position);
                    }
                })

                .create();
    }
}
