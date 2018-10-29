package com.untitledlangproject.wordlist;

import android.util.Log;

import com.untitledlangproject.dao.WordItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class WordListPresenter implements WordListMVP.Presenter {


    private WordListMVP.View mView;
    private WordListMVP.Model mModel;

    private List<WordItem> listWords = new ArrayList<>();

    public WordListPresenter(WordListMVP.Model model){

        mModel = model;
    }

    @Override
    public void setView(WordListMVP.View view) {
        mView = view;

        //Execute the needed actions since the view has been created
        onViewCreated();
    }


    private void onViewCreated() {

        mView.showProgressBar();
        try {

            //Request the reader for the file on the model layer
            BufferedReader reader = mModel.requestTxtFile(mView.getContext());

            //Processing the txt file
            String line;
            Log.d("teste leitura", "WordListPresenter after reading has been reached");
            Map<String, Integer> mapWordsListForCounting = new HashMap<>();
            while((line = reader.readLine()) != null){
                for(String word : line.trim().split(" ")){

                    //Check if the current word is inserted yet
                    if(mapWordsListForCounting.get(word) == null){
                        mapWordsListForCounting.put(word, 1);
                    } else{
                        int currentTimesOfUsage = mapWordsListForCounting.get(word);
                        mapWordsListForCounting.put(word, ++currentTimesOfUsage);
                    }

                    WordItem wordItem = new WordItem(
                            word.replaceAll("[^a-zA-Z0-9_-]", ""),
                            mapWordsListForCounting.get(word)
                    );
                    listWords.add(wordItem);
                }
            }
            reader.close();
            mView.updateWordList(listWords);
            mView.hideProgressBar();
        } catch (IOException e) {
            e.printStackTrace();
            mView.showErrorWhenProcessingFile();
        }
    }
}
