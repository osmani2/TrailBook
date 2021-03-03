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

/* Called when user clicks on the floating button
*   Allows user to add an experiment by input a name and date
*   Users can enter no info and default values will be applied
* */
public class AddTrialsFragment extends DialogFragment {
    private EditText successTrials;
    private EditText failTrials;
    private OnFragmentInteractionListener listener;

    public interface OnFragmentInteractionListener{
        void onAddPressed(String successes, String failures);
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_trials_layout,null);

        successTrials = view.findViewById(R.id.success_amount_editText);
        failTrials = view.findViewById(R.id.failure_amount_editText);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add Trials")

                /* Exit fragment */
                .setNegativeButton("Cancel",null)

               /* Takes user input and returns to Display */
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String success=successTrials.getText().toString();
                        String fail=failTrials.getText().toString();

                        listener.onAddPressed(success,fail);
                    }
                })
                .create();
    }
}
