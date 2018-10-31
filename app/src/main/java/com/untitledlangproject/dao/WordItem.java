package com.untitledlangproject.dao;

import android.support.annotation.NonNull;

public class WordItem extends Word implements Comparable {

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

    @Override
    public int compareTo(@NonNull Object o) {

//        return this.getNumberOfUsage() < ((WordItem) o).getNumberOfUsage() ? 1 : -1;
        if(this.getNumberOfUsage() < ((WordItem)o).getNumberOfUsage()){
            return 1;
        } else if(this.getNumberOfUsage() > ((WordItem)o).getNumberOfUsage()){
            return -1;
        } else{
            return 0;
        }
    }

}
