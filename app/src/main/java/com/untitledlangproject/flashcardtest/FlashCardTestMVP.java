package com.untitledlangproject.flashcardtest;

import com.untitledlangproject.dao.FlashCard;

import java.util.List;

public interface FlashCardTestMVP {

    interface View{
        void showWordForTesting(String word, int numberOfUsages, String translation);
        void showConfirmationLayout();
        void hideConfirmationLayout();
        void goToSelectContentActivity();
    }

    interface Presenter{
        void setView(View view);
        void sendFlashCardList(List<FlashCard> flashCardList);
        void startTests();
        void notifyUserAnswer();
        void notifyUserFeedback(boolean isAnswerRight);

    }

    interface Model{

    }
}
