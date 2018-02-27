package com.example.amir.lab02;

import java.io.Serializable;

/**
 * Created by amir on 25.02.2018.
 */

public class HTMLFilteredReader extends MySimpleURLReader implements Serializable {

    public HTMLFilteredReader(String arg0) {
        super(arg0);

    }

    public String getPageContents() {
        int lineCounter;
        int charCounter;
        String content;
        String modifiedContent;

        lineCounter = 0;
        charCounter = 0;
        modifiedContent = "";
        content = super.getPageContents();

        while ( charCounter < content.length()) {
            if ( content.charAt( charCounter) == '\n') {
                lineCounter++;
            }

            if ( content.charAt( charCounter) == '<')
            {
                while ( content.charAt( charCounter) != '>') {
                    charCounter++;
                }
            }

            if ( content.charAt( charCounter) != '>')
                modifiedContent = modifiedContent + content.charAt( charCounter);
            charCounter++;
        }

        return modifiedContent;
    }

    public String getUnfilteredPageContents() {
        return super.getPageContents();
    }
}
