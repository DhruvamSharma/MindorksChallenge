package com.mindorks.bootcamp.test.wordplay;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;
import com.mindorks.bootcamp.test.wordplay.models.WordHolderDomain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WordDictionary {

    private HashMap<String, WordHolderDomain> wordToDefinitionMap;
    private ArrayList<String> wordList;

    WordDictionary(Context context) throws IOException {
        InputStream streamForReading = context.getResources().
                openRawResource(R.raw.words);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(streamForReading));
        Gson gson = new GsonBuilder().create();
        JsonStreamParser parser = new JsonStreamParser(reader);
        wordToDefinitionMap = new HashMap<>();
        wordList = new ArrayList<>();
        while(parser.hasNext()) {
            JsonElement currentJsonElement = parser.next();
            if(currentJsonElement.isJsonObject()) {
                WordHolderDomain domain = gson.fromJson(currentJsonElement, WordHolderDomain.class);
                if(domain != null) {
                    wordToDefinitionMap.put(domain.getmWord(), domain);
                    wordList.add(domain.getmWord());
                }
            }
        }
        streamForReading.close();
        reader.close();
    }

    private boolean isGoodWord(String word) {
        if (wordToDefinitionMap.containsKey(word)) {
            WordHolderDomain domain = wordToDefinitionMap.get(word);
            if (domain != null && !domain.ismRead()) {
                return true;
            } else{
                return  false;
            }
        } else {
            return false;
        }
    }

    public WordHolderDomain getAWordAndMeaning() {
        WordHolderDomain domain = wordToDefinitionMap.get(wordList
                .get(getRandomIntegerBetweenRange(0, wordToDefinitionMap.size()-1)));
        if(isGoodWord(domain.getmWord())) {
            domain.setmRead(true);
            wordToDefinitionMap.put(domain.getmWord(), domain);
            return domain;
        }
    }

    private int getRandomIntegerBetweenRange(double min, double max){
        return (int)((Math.random()*((max-min)+1))+min);
    }

}
