package com.example.amir.lab02;

/**
 * Created by amir on 25.02.2018.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class SuperHTMLFilteredReader extends HTMLFilteredReader implements Serializable {

    public SuperHTMLFilteredReader(String arg0) {
        super(arg0);
    }

    /**
     * finds list of links in a webpage
     * @return
     */
    public ArrayList<String> getLinks() {

        String content;
        int length;
        int endIndex;
        int startIndex;
        int counter;
        ArrayList<String> listOfLinks;

        content = super.getUnfilteredPageContents();
        length = content.length();
        endIndex = 0;
        startIndex = 0;
        counter = 0;
        listOfLinks = new ArrayList<String>();

        while( startIndex < length - 4) { 				// href="LIN"
            if ( content.substring(startIndex, startIndex + 4).equals("href")) {

                startIndex += 6;

                while ( content.charAt( startIndex + counter) != '"') {
                    counter++;
                }

                endIndex = startIndex + counter;
                listOfLinks.add( content.substring(startIndex, endIndex));
                startIndex = endIndex + 1;

                // reset counter
                counter = 0;
                endIndex = 0;
            }
            startIndex++;
        }
        return listOfLinks;
    }

}
