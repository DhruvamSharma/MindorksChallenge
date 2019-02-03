package com.mindorks.bootcamp.test.wordplay;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mindorks.bootcamp.test.wordplay.models.WordHolderDomain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class manages the json parsing and
 * the logic for the application.
 */
public class WordDictionary {

    private HashMap<Boolean, ArrayList<WordHolderDomain>> wordToDefinitionMap;

    /**
     * Constructor that initialises the wordplay app
     * And Parses json file
     * And initialises the wordToDefinitionMap.
     * @param context
     * @throws IOException
     */
    WordDictionary(Context context) throws IOException {
        InputStream streamForReading = context.getResources().
                openRawResource(R.raw.words);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(streamForReading));
        Gson gson = new GsonBuilder().create();
        wordToDefinitionMap = new HashMap<>();
        ArrayList<WordHolderDomain> domainListToAdd;
        WordHolderDomain[] domains = gson.fromJson(reader, WordHolderDomain[].class);
        for (WordHolderDomain domain : domains) {
            if(domain != null) {
                if(wordToDefinitionMap.get(false) == null) {
                    domainListToAdd = new ArrayList<>();
                    domainListToAdd.add(domain);
                    wordToDefinitionMap.put(false, domainListToAdd);
                } else {
                    domainListToAdd = wordToDefinitionMap.get(false);
                    domainListToAdd.add(domain);
                    wordToDefinitionMap.put(false, domainListToAdd);
                }
            }
        }
        Log.e("mindorks error", wordToDefinitionMap.get(false).size() + " is size");
        streamForReading.close();
        reader.close();
    }

    /**
     * This method gets the word and meaning to display
     * n the application.
     * @return WordHolderDomain
     */
    WordHolderDomain getAWordAndMeaning() {
        ArrayList<WordHolderDomain> currentList = wordToDefinitionMap.get(false);
        if(currentList != null && currentList.size() != 0) {
            WordHolderDomain domain = currentList.get(
                    getRandomIntegerBetweenRange(0, currentList.size()-1));
            if(domain != null) {
                ArrayList<WordHolderDomain> domainListToAdd;
                if(wordToDefinitionMap.get(true) == null) {
                    domainListToAdd = new ArrayList<>();
                } else {
                    domainListToAdd = wordToDefinitionMap.get(true);
                }
                if( domainListToAdd != null) {
                    domainListToAdd.add(domain);
                    wordToDefinitionMap.put(true, domainListToAdd);
                    currentList.remove(domain);
                    wordToDefinitionMap.put(false, currentList);
                    return domain;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return new WordHolderDomain();
        }
    }

    /**
     * This method generates a random number.
     * @param min
     * @param max
     * @return
     */
    private int getRandomIntegerBetweenRange(double min, double max){
        return (int)((Math.random()*((max-min)+1))+min);
    }

}
