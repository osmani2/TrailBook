package com.example.nahmed2_trialbook;

import android.os.Parcel;
import android.os.Parcelable;

/*
        Parcelable Object:
        “Parcelable.” Https://Developer.Android.Com/, 30 Sept. 2020,
        developer.android.com/reference/android/os/Parcelable.
*/

/* A parcelable object that records name, date, and trial information */
public class Experiment implements Parcelable {
    private String name;
    private String date;
    private int successes;
    private int failures;

    public Experiment(String name, String date) {
        this.name = name;
        this.date = date;
        this.successes=0;
        this.failures=0;
    }

    /***********************************************************************************************
     *                                                                                             *
     *                                   GETTERS/SETTERS                                           *
     *                                                                                             *
     ***********************************************************************************************/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public int getSuccesses() {
        return successes;
    }

    public int getFailures() {
        return failures;
    }

    /***********************************************************************************************
     *                                                                                             *
     *                                   OTHER METHODS                                             *
     *                                                                                             *
     ***********************************************************************************************/

    public int getTotals() {
        return successes+failures;
    }

    public void addSuccesses(int amount){
        this.successes+=amount;
    }

    public void addFailures(int amount){
        this.failures+=amount;
    }

    public double getSuccessRate() {
        /* Avoid division by 0 */
        if(this.successes==0 & this.failures==0){
            return 0.0;
        }
        return ((double)this.successes)/(this.successes+this.failures)*100;
    }


    /***********************************************************************************************
     *                                                                                             *
     *                                   PARCELABLE METHODS                                        *
     *                               Imported by Android Studio                                    *
     *                                                                                             *
     ***********************************************************************************************/

    protected Experiment(Parcel in) {
        name = in.readString();
        date = in.readString();
        successes = in.readInt();
        failures = in.readInt();
    }

    public static final Creator<Experiment> CREATOR = new Creator<Experiment>() {
        @Override
        public Experiment createFromParcel(Parcel in) {
            return new Experiment(in);
        }

        @Override
        public Experiment[] newArray(int size) {
            return new Experiment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(date);
        dest.writeInt(successes);
        dest.writeInt(failures);
    }
}
