package com.example.nahmed2_trialbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/*  SOURCES
     Accessing Parcelable Objects:
        Asked by Sushant Bhatnagar, Answered by Vineet Shukla. “Pass List of Objects from One Activity to Other Activity in Android.”
        Stack Overflow, 23 Aug. 2012.
        stackoverflow.com/questions/12092612/pass-list-of-objects-from-one-activity-to-other-activity-in-android/12092942#12092942.
        License CC BY-SA 4.0

     Creating Back Button:
        Asked by Drew, Answered by Luke F. “Android - Back Button in the Title Bar.”
        Stack Overflow, 27 Jan. 2013.
        stackoverflow.com/questions/14545139/android-back-button-in-the-title-bar/16755282#16755282.
        License CC BY-SA 4.0

     Update TextView:
        Asked by prash, Answered by Roushdy. “Dynamically Update TextView in Android.”
        Stack Overflow, 2 Feb. 2011.
        stackoverflow.com/questions/4873196/dynamically-update-textview-in-android/60388786#60388786.
        License CC BY-SA 4.0

      Lab 3 Instructions - FragmentsFile. 2021-02-01. Public Domain.
        https://eclass.srv.ualberta.ca/mod/resource/view.php?id=4829653
* */

/* Activity displays Experiment information (trials)
*  Activity also provides GUI for adding trials to the experiment object
*  Calls a handler to handle all input and create display string
*  Returns Experiment to Main to update GUI list
*  */
public class DisplayTrialActivity extends AppCompatActivity implements AddTrialsFragment.OnFragmentInteractionListener {
    private Experiment experiment;                                //Takes Experiment clicked as input
    private ExperimentHandler handler = new ExperimentHandler(); //Input handler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_trial);

        experiment= getIntent().getExtras().getParcelable("Experiment");     //Retrieve object to display

        ActionBar actionBar = getSupportActionBar();            //Initialize action bar
        actionBar.setDisplayHomeAsUpEnabled(true);             //create back arrow

        /* Display UI */
        displayTrials();
    }

    public void displayTrials(){
        //Display Experiment trial information on screen
        TextView display = findViewById(R.id.data_text);
        String data = handler.displayString(experiment);        //calls handler to create display string
        display.setText(data);
        display.invalidate();

    }

    /* BACK BUTTON
    *   Prepares Experiment object to send back to Main
    *   Returns to main */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /* Get return object ready  */
        Intent intent = new Intent();
        intent.putExtra("Experiment",experiment);
        setResult(Activity.RESULT_OK,intent);

        this.finish();
        return true;
    }

    /* ADD TRIALS BUTTON
    *   Calls the AddTrials fragment */
    public void addTrials(View view){
        new AddTrialsFragment().show(getSupportFragmentManager(), "ADD_TRIALS");
    }

    /* FRAGMENT "ADD" BUTTON
    *   Adds number of successes and failures to Experiment object
    *   Displays updated information */
    @Override
    public void onAddPressed(String successes, String failures){
        experiment=handler.updateTrials(experiment,successes,failures); //updates experiment with new trials
        displayTrials();
    }

}