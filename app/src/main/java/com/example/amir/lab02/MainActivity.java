package com.example.amir.lab02;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.ClipboardManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // constants
    //public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRA_MESSAGE_LIST_OF_POEMS_ARRAYLIST = "com.example.myfirstapp.LISTOFPOEMSARRAYLIST";

    // variables
    ArrayList<MySimpleURLReader> listOfPoemLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init variables
        listOfPoemLinks = new ArrayList<>();

        // do something else here
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Link", "http://www.cs.bilkent.edu.tr/~david/housman.txt");
        clipboard.setPrimaryClip(clip);
    }

    public void processURL(View view) {
        MySimpleURLReader temp;


        //Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.helloEditText);
        String message = editText.getText().toString();

        // new
        temp = checkForLinkType( message);
        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

        if ( temp != null) {
            Toast.makeText(getApplicationContext(), "link was added successfully", Toast.LENGTH_SHORT).show();
            listOfPoemLinks.add( temp);

            //System.out.println( "link added successfully!");
        } else {
            Toast.makeText(getApplicationContext(), "link could not be added!", Toast.LENGTH_SHORT).show();
            //System.out.println( "link could not be added!");
        }

        //intent.putExtra(EXTRA_MESSAGE, message);
        //startActivity(intent);
    }

    public void quitTheApp(View view) {
        finish();
    }

    public void listAllPoemsInNewActivity(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_MESSAGE_LIST_OF_POEMS_ARRAYLIST, listOfPoemLinks);
        startActivity(intent);
    }

    public static MySimpleURLReader checkForLinkType( String url) {

        // check for url extension
        System.out.println( "**************************" + url + "********************************");
        if ( url.substring( url.length() - 3, url.length()).equals( "txtz")) {
            return new MySimpleURLReader( url);
        } else if ( url.substring( url.length() - 3, url.length()).equals( "htm")
                || url.substring( url.length() - 4, url.length()).equals( "html")) {
            return new HTMLFilteredReader( url);
        } else {
            return null;
        }
    }
}
