package com.example.amir.lab02;

/**
 * Created by amir on 25.02.2018.
 */



import android.os.Parcelable;
import android.os.Parcel;

import cs1.*;

public class MySimpleURLReader extends SimpleURLReader implements Parcelable{

    private String url;

    /*************************************/
    /**********   My     Code   **********/
    /*************************************/

    // normal constructor
    public MySimpleURLReader(String url) {
        super(url);
        this.url = url;
    }

    // my code
    public String getURL() {
        return this.url;
    }

    public String getName() {
        int counter;

        //Toast.makeText(getApplicationContext(), "link could not be added!", Toast.LENGTH_SHORT).show();
        System.out.println( "******************************" + url + "*******************************");
        counter = url.length() - 1;

        while ( url.charAt( counter) != '/') {
            counter--;
        }

        return url.substring( counter + 1, url.length());
    }

    public String getPageContents( ) {
        return super.getPageContents().substring(4, super.getPageContents().length());
    }

    /*************************************/
    /**********      Parcel     **********/
    /*************************************/

    @Override
    public int describeContents() {
        return 0;
    }

    // parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
    }

    // read from parcel
    private static MySimpleURLReader readFromParcel(Parcel in) {
        return new MySimpleURLReader(in.readString());
    }

    // parcel
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MySimpleURLReader createFromParcel(Parcel in) {
            return readFromParcel(in);
        }

        public MySimpleURLReader[] newArray(int size) {
            return new MySimpleURLReader[size];
        }
    };

    // constructor adaptable with parcel
   /* private MySimpleURLReader(Parcel in) {
        super(in.readString());
        this.url = in.readString();
    }*/


}
