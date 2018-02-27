package com.example.amir.lab02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayMessageActivity extends AppCompatActivity {

    // variables
    ArrayList<MySimpleURLReader> listOfPoemLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String message;

        message = "";
        listOfPoemLinks = new ArrayList<MySimpleURLReader>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        //intent.putParcelableArrayListExtra(MainActivity.EXTRA_MESSAGE_LIST_OF_POEMS_ARRAYLIST, listOfPoemLinks);
        listOfPoemLinks = intent.getParcelableArrayListExtra(MainActivity.EXTRA_MESSAGE_LIST_OF_POEMS_ARRAYLIST);

        for ( int i = 0; i < listOfPoemLinks.size(); i++) {
            message = message + i + "- " + listOfPoemLinks.get(i).getName() + "\n";
        }
        // capture the layout's textview and edit its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);

    }

    public void showPoem(View view) {
        String indexResponse;
        Intent intent;
        TextView textViewResponse;
        TextView textViewPoemResult;

        intent = new Intent(getApplicationContext(), DisplayMessageActivity.class);
        textViewResponse = (TextView) findViewById(R.id.editText);
        textViewPoemResult = findViewById(R.id.textView7);
        indexResponse = textViewResponse.getText().toString();

        if ( listOfPoemLinks.get( Integer.parseInt(indexResponse)) != null) {
            textViewPoemResult.setText( listOfPoemLinks.get( Integer.parseInt(indexResponse)).getPageContents());
        }
    }
}
