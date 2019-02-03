package com.mindorks.bootcamp.test.wordplay.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WordHolderDomain {
    @SerializedName("word")
    @Expose
    private String mWord;
    @Expose
    @SerializedName("meaning")
    private String mMeaning;

    public String getmWord() {
        return mWord;
    }

    public void setmWord(String word) {
        this.mWord = mWord;
    }

    public String getmMeaning() {
        return mMeaning;
    }

}
