package com.example.nahmed2_trialbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/*  SOURCES
        Lab 2 ListView Demo Example - InstructionsFile. 2021-01-25. Public Domain.
                https://eclass.srv.ualberta.ca/mod/resource/view.php?id=4829607

        Lab 3 Instructions - CustomListFile. 2021-02-01. Public Domain.
                https://eclass.srv.ualberta.ca/mod/resource/view.php?id=4829652

        Lab 3 Instructions - FragmentsFile. 2021-02-01. Public Domain.
                https://eclass.srv.ualberta.ca/mod/resource/view.php?id=4829653

        Display Text On Empty List:
                Asked by Daniel, Answered by dymmeh. “Android Displaying Text When ListView Is Empty.”
                Stack Overflow, 4 Apr. 2012.
                stackoverflow.com/questions/10017088/android-displaying-text-when-listview-is-empty/10017294#10017294.
                License CC BY-SA 4.0

        Long Click on Item:
                Asked by Lior Iluz, Answered by blindstuff. “Android: Long Click on a Button -> Perform Actions.”
                Stack Overflow, 9 Dec. 2010.
                stackoverflow.com/questions/4402740/android-long-click-on-a-button-perform-actions/4402854#4402854.
                License CC BY-SA 4.0

        Open New Activity:
                Asked by David_D, Answered by Stack Fox. “How Open New Activity Clicking an Item in Listview?”
                Stack Overflow, 29 Oct. 2013.
                stackoverflow.com/questions/19662233/how-open-new-activity-clicking-an-item-in-listview/50314738#50314738.
                License CC BY-SA 4.0

        Passing Objects Between Activities:
                Gandla, Neeraja. “How to Pass Objects between Android Activities? - Neeraja Gandla.”
                Medium, 31 July 2020.
                medium.com/@gaandlaneeraja/how-to-pass-objects-between-android-activities-86f2cfb61bd4.

        Accessing Parcelable Objects:
                Asked by Sushant Bhatnagar, Answered by Vineet Shukla. “Pass List of Objects from One Activity to Other Activity in Android.”
                Stack Overflow, 23 Aug. 2012.
                stackoverflow.com/questions/12092612/pass-list-of-objects-from-one-activity-to-other-activity-in-android/12092942#12092942.
                License CC BY-SA 4.0
*/


/* Main Activity
*   The main screen of the app
*   Displays list of experiments
*   Allows users to Add or Edit or Delete experiments
*   Calls AddFragment (floating button) and EditFragments (long click item)
*   Calls Handler to handle fragment input
*   Starts DisplayActivity when Experiment is clicked on */
public class MainActivity extends AppCompatActivity implements AddExperimentFragment.OnFragmentInteractionListener, EditExperimentFragment.OnFragmentInteractionListener {

    /* Lab 2 and 3 Instructions */
    ListView experimentList;
    ArrayAdapter<Experiment> experimentAdapter;
    ArrayList<Experiment> dataList;

    static int savePosition;    //save postion in list when item clicked on
    private ExperimentHandler handler = new ExperimentHandler(); //Input handler

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Lab 2 and Lab 3 Instructions
            Create and display list of Experiments */
        experimentList = findViewById(R.id.experiment_list);
        dataList = new ArrayList<>();
        experimentAdapter = new CustomList(this, dataList);
        experimentList.setAdapter(experimentAdapter);

        /* Display Text On Empty List */
        TextView empty_text = findViewById(R.id.empty_text);
        experimentList.setEmptyView(empty_text);

        /* Long Click on Item
        *   Calls edit fragment
        * */
        experimentList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new EditExperimentFragment(position).show(getSupportFragmentManager(), "EDIT_EXP");
                return true;
            }
        });

        /* Lab 3 - Fragments
        *   Click Floating action button to open add fragment
        *  */
        final FloatingActionButton addExperimentButton = findViewById(R.id.add_edit_button);
        addExperimentButton.setOnClickListener((v)-> {
            new AddExperimentFragment().show(getSupportFragmentManager(), "ADD_EXP");
        });

        /* Open New Activity
        *       Click on Item in List
        *       Starts new activity for result
        *       Sends Experiment object to new activity
        *  */
        experimentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /* Prepare experiment object clicked on to send to new activity */
                Intent intent = new Intent(getApplicationContext(),DisplayTrialActivity.class);
                intent.putExtra("Experiment",dataList.get(position));

                savePosition=position;      //save Experiments position in list
                startActivityForResult(intent,1);       //start new activity
            }
        });
    }


    /* Returning from second activity */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        Experiment experiment=(Experiment) intent.getExtras().getParcelable("Experiment");   //Accessing Parcelable Objects

        dataList.set(savePosition, experiment);     //replace old experiment object with new one
        experimentAdapter.notifyDataSetChanged();   //update list
    }


    /* Return from Add fragment */
    @Override
    public void onOkPressed(String name, String date){
        experimentAdapter.add(handler.createExperiment(name,date));     //handler validates input and creates new experiment
    }

    /* Return from Edit fragment */
    @Override
    public void onEditPressed(String newName,int position){
        //updates name of Experiment
        Experiment updatedExperiment = handler.updateExperiment(dataList.get(position),newName);        //handler updates experiment
        dataList.set(position,updatedExperiment);
        experimentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDeletePressed(int position){
        //Deletes Experiment selected
        dataList.remove(position);
        experimentAdapter.notifyDataSetChanged();
    }


}