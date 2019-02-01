package com.mindorks.bootcamp.test.wordplay.models;

public class WordHolderDomain {
    private String mWord;
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
