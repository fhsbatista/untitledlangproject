package com.untitledlangproject.flashcardtest;

import android.widget.Toast;

import com.untitledlangproject.dao.FlashCard;

import java.util.List;

public class FlashCardTestPresenter implements FlashCardTestMVP.Presenter {

    private FlashCardTestMVP.View mView;
    private List<FlashCard> mFlashCardList;

    @Override
    public void setView(FlashCardTestMVP.View view) {
        mView = view;
    }

    @Override
    public void sendFlashCardList(List<FlashCard> flashCardList) {
        mFlashCardList = flashCardList;
    }

    @Override
    public void startTests() {
        nextTest();
    }

    @Override
    public void notifyUserAnswer() {
        mView.showConfirmationLayout();
    }

    @Override
    public void notifyUserFeedback(boolean isAnswerRight) {
        if (isAnswerRight == false) {
            mFlashCardList.add(mFlashCardList.get(0));
        }
        mFlashCardList.remove(0);
        nextTest();

    }

    void nextTest(){
        if(mFlashCardList.size() > 0) {
            FlashCard flashCard = mFlashCardList.get(0);
            String word = flashCard.getText();
            int numberOfUsage = flashCard.getNumberOfUsage();
            String translation = flashCard.getTranslation();
            mView.hideConfirmationLayout();
            mView.showWordForTesting(word, numberOfUsage, translation);
        } else{
            mView.goToSelectContentActivity();
        }
    }
}
