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

public class WordDictionary {

    private HashMap<Boolean, ArrayList<WordHolderDomain>> wordToDefinitionMap;

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

    WordHolderDomain getAWordAndMeaning() {
        if(wordToDefinitionMap.get(false).size() != 0) {
            ArrayList<WordHolderDomain> domainList = wordToDefinitionMap.get(false);
            if(domainList != null) {
                WordHolderDomain domain = domainList.get(getRandomIntegerBetweenRange(0, wordToDefinitionMap.size()-1));
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
                        domainList.remove(domain);
                        wordToDefinitionMap.put(false, domainList);
                        return domain;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return  null;
            }
        } else {
            return new WordHolderDomain();
        }
    }

    private int getRandomIntegerBetweenRange(double min, double max){
        return (int)((Math.random()*((max-min)+1))+min);
    }

}
