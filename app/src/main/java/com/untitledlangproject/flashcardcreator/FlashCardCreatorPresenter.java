package com.untitledlangproject.flashcardcreator;

import com.untitledlangproject.dao.FlashCard;
import com.untitledlangproject.dao.WordItem;

import java.util.ArrayList;
import java.util.List;

public class FlashCardCreatorPresenter implements FlashCardCreatorMVP.Presenter {

    private static final String TAG = FlashCardCreatorPresenter.class.getSimpleName();

    private FlashCardCreatorMVP.View mView;

    //Variables
    private ArrayList<FlashCard> mListFlashCard = new ArrayList<>();

    public FlashCardCreatorPresenter(){

    }

    @Override
    public void setView(FlashCardCreatorMVP.View view) {
        mView = view;
    }

    @Override
    public void saveWordRequest(WordItem word, String translation) {

        if(!word.getText().isEmpty() && !translation.isEmpty()){
            mListFlashCard.add(new FlashCard(word.getText(), word.getNumberOfUsage(), translation));
            mView.showSavedLabel();
        } else{
            mView.showInputTranslationError();
        }


    }

    @Override
    public void notifyWordRemoved(List<WordItem> words) {
        if(words.size() == 0){
            mView.showListIsOver();
            mView.goToFlashCardTestActivity(mListFlashCard);
        }
    }
}
