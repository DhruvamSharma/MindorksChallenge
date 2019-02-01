package com.mindorks.bootcamp.test.wordplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvWord, tvMeaning;
    private Button btRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWord = findViewById(R.id.tv_word);
        tvMeaning = findViewById(R.id.tv_meaning);

        btRandom = findViewById(R.id.btn_random);
    }
}
