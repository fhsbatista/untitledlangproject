package com.untitledlangproject.wordlist;

import android.content.Context;

import com.untitledlangproject.dao.Word;
import com.untitledlangproject.dao.WordItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface WordListMVP {

    interface View {
        void showProgressBar();
        void hideProgressBar();
        void updateWordList(ArrayList<WordItem> words);
        void showErrorWhenProcessingFile();
        void goFlashCreatorActivity();
        Context getContext();
    }

    interface Presenter {
        void setView(WordListMVP.View view);
        void onFinishButtonClicked();

    }

    interface Model {
        BufferedReader requestTxtFile(Context context) throws IOException;
    }
}
