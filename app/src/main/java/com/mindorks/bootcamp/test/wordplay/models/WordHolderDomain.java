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
    private boolean mRead = false;

    public String getmWord() {
        return mWord;
    }

    public void setmWord(String word) {
        this.mWord = mWord;
    }

    public String getmMeaning() {
        return mMeaning;
    }

    public void setmMeaning(String meaning) {
        this.mMeaning = mMeaning;
    }

    public boolean ismRead() {
        return mRead;
    }

    public void setmRead(boolean read) {
        this.mRead = mRead;
    }
}
