package com.untitledlangproject.wordlist;

import android.content.Context;

import com.untitledlangproject.dao.Word;
import com.untitledlangproject.dao.WordItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface WordListMVP {

    interface View {
        void showProgressBar();
        void hideProgressBar();
        void updateWordList(List<WordItem> words);
        void showErrorWhenProcessingFile();
        Context getContext();
    }

    interface Presenter {
        void setView(WordListMVP.View view);

    }

    interface Model {
        BufferedReader requestTxtFile(Context context) throws IOException;
    }
}
