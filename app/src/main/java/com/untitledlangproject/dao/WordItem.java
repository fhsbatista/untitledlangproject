package com.untitledlangproject.dao;

public class WordItem extends Word {

    private int numberOfUsage;
    private boolean keep;

    public WordItem(String text, int timesOfUsage) {
        super(text);
        this.numberOfUsage = timesOfUsage;
        this.keep = true;
    }

    public int getNumberOfUsage() {
        return numberOfUsage;
    }

    public boolean isKeep() {
        return keep;
    }

    public void setNumberOfUsage(int numberOfUsage) {
        this.numberOfUsage = numberOfUsage;
    }

    public void setKeep(boolean keep) {
        this.keep = keep;
    }
}
