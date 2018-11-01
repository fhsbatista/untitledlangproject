package com.untitledlangproject.flashcardcreator;

import com.untitledlangproject.dao.FlashCard;
import com.untitledlangproject.dao.WordItem;

import java.util.ArrayList;
import java.util.List;

public interface FlashCardCreatorMVP {

    int KEY_SAVE_WORD = 1;
    int KEY_REMOVE_WORD = 2;

    interface View{
        void showSavedLabel();
        void showListIsOver();
        void showInputTranslationError();
        void goToFlashCardTestActivity(ArrayList<FlashCard> flashCardList);

    }

    interface Presenter{
        void setView(FlashCardCreatorMVP.View view);
        void saveWordRequest(WordItem word, String translation);
        void notifyWordRemoved(List<WordItem> words);

    }

    interface Model{
        //None needed method so far
    }
}
