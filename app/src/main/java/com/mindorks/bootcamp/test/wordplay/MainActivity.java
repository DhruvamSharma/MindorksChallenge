package com.mindorks.bootcamp.test.wordplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.bootcamp.test.wordplay.models.WordHolderDomain;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView tvWord, tvMeaning;
    private Button btRandom;
    private WordDictionary dictionary;
    private static final String failMessage = "Could not load dictionary";
    public static final String START_MESSAGE = "Find as many words as possible that can be formed by adding one letter to <big>%s</big> (but that do not contain the substring %s).";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        tvWord = findViewById(R.id.tv_word);
        tvMeaning = findViewById(R.id.tv_meaning);

        btRandom = findViewById(R.id.btn_random);
        btRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNewWord();
            }
        });
        try {
            dictionary = new WordDictionary(MainActivity.this);
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, failMessage, Toast.LENGTH_LONG);
            toast.show();
        }
        tvWord.setText(START_MESSAGE);
    }

    private void getNewWord() {
        WordHolderDomain domain = dictionary.getAWordAndMeaning();
        if(domain != null) {
            if(domain.getmWord() != null && !domain.getmWord().equals("")) {
                tvWord.setText(domain.getmWord());
                tvMeaning.setText(domain.getmMeaning());
            } else {
                Toast.makeText(this, "No more words", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
