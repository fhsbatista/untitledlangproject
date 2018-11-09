package com.untitledlangproject.wordlist;

import android.content.Context;

import com.untitledlangproject.dao.Word;
import com.untitledlangproject.dao.WordItem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface WordListMVP {

    interface View {
        void showProgressBar();
        void hideProgressBar();
        void updateWordList(ArrayList<WordItem> words);
        void showErrorWhenProcessingFile();
        void goFlashCreatorActivity();
        void updateStats(int differenKnownWordsPercent);
        Context getContext();
    }

    interface Presenter {
        void setView(WordListMVP.View view);
        void onFinishButtonClicked();
        //This method will handle a specific word and insert it on database In order to this word don't be shown for the user again
        void saveWord(String word);

        //This method must be used in order to remove the words from the list that will be shown for the user
        void removeSavedWords(Map<String, Integer> words);
    }

    interface Model {
        BufferedReader requestContentTxtFile(Context context) throws IOException;
        List<String> requestSavedWordsList(Context context) throws IOException;
        void saveWord(Context context, String word) throws IOException;
    }
}
