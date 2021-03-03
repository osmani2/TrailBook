package com.example.nahmed2_trialbook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* SOURCES
         Creating Default Date String:
            “Java Convert Date to String - Javatpoint.” Www.Javatpoint.Com.
            www.javatpoint.com/java-date-to-string.
            Accessed 9 Feb. 2021.
* */

/*
* Handler Class
*   Handles all user input from GUI
*   Applys input to experiment object
*   Creates display string with Experiments attributes
* */
public class ExperimentHandler {

    public ExperimentHandler() {
    }

    // Convert input to integer value
    private int convertToInt(String input){
        if(input.equals("")) {
            return 0;
        }
        return Integer.parseInt(input);
    }

    private String checkDate(String input){
        //Validate date input
        //if nothing entered use default date
        if(input.equals("")){
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(date);
        }
        return input;
    }

    private String checkName(String input){
        //Validate name input
        //if nothing entered, use default "Untitled"
        if(input.equals("")){
            return "Untitled";
        }
        return input;
    }

    public Experiment createExperiment(String name, String date){
        //Create a new experiment object with given input
        //validate input first
        name = this.checkName(name);
        date = this.checkDate(date);
       return new Experiment(name,date);
    }

    public Experiment updateExperiment(Experiment experiment,String name){
        //validate input
        //update object with new name
        name = this.checkName(name);
        experiment.setName(name);
        return experiment;
    }

    public Experiment updateTrials(Experiment experiment, String inputSuccesses, String inputFailures){
        //validate input
        //update object with new trials
        int intSuccess = this.convertToInt(inputSuccesses);
        int intFails = this.convertToInt(inputFailures);
        experiment.addSuccesses(intSuccess);
        experiment.addFailures(intFails);
        return experiment;
    }

    public String displayString(Experiment experiment){
        //create string to display in GUI
        return experiment.getName()+"\n"
                +experiment.getDate()+"\n"
                +experiment.getSuccesses()+"\n"
                +experiment.getFailures()+"\n"
                +experiment.getTotals()+"\n"
                +String.format("%.1f",experiment.getSuccessRate())+"%";
    }

}
