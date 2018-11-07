package com.untitledlangproject.wordlist;

import android.util.Log;

import com.untitledlangproject.dao.WordItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordListPresenter implements WordListMVP.Presenter {


    private WordListMVP.View mView;
    private WordListMVP.Model mModel;

    private ArrayList<WordItem> listWords = new ArrayList<>();

    public WordListPresenter(WordListMVP.Model model){

        mModel = model;
    }

    @Override
    public void setView(WordListMVP.View view) {
        mView = view;

        //Execute the needed actions since the view has been created
        onViewCreated();
    }

    @Override
    public void onFinishButtonClicked() {
        mView.goFlashCreatorActivity();
    }


    private void onViewCreated() {

        mView.showProgressBar();
        try {
            //Request the reader for the file on the model layer
            BufferedReader reader = mModel.requestContentTxtFile(mView.getContext());

            //Processing the txt file
            String line;
            Log.d("teste leitura", "WordListPresenter after reading has been reached");
            Map<String, Integer> mapWordsListForCounting = new HashMap<>();
            while((line = reader.readLine()) != null){
                for(String word : line.trim().split(" ")){
                    word = word.toLowerCase().replaceAll("[^a-zA-Z0-9_-]", "");

                    //Check if the current word is inserted yet
                    if(mapWordsListForCounting.get(word) == null){
                        mapWordsListForCounting.put(word, 1);
                    } else{
                        int currentTimesOfUsage = mapWordsListForCounting.get(word);
                        mapWordsListForCounting.put(word, ++currentTimesOfUsage);
                    }


                }
            }

            //Remove from the map the words that have been saved by the user before
            removeSavedWords(mapWordsListForCounting);

            //Iterates the Map in order to add them into a list
            for(Map.Entry<String, Integer> entry : mapWordsListForCounting.entrySet()){
                if(entry.getKey().equals("to")){
                    Log.d("to aqui", "te peguei " + entry.getKey());
                }
                WordItem wordItem = new WordItem(entry.getKey(), entry.getValue());
                Log.d("palavra", wordItem.getText());
                listWords.add(wordItem);
            }
            //Sort the list putting the words that were most often used on the top
            Collections.sort(listWords);
            reader.close();

            //Notifies the view that the word list has been updated
            mView.updateWordList(listWords);
            mView.hideProgressBar();
        } catch (IOException e) {
            e.printStackTrace();
            mView.showErrorWhenProcessingFile();
        }
    }

    @Override
    public void removeSavedWords(Map<String, Integer> words) {
        try {

            List<String> savedWords = mModel.requestSavedWordsList(mView.getContext());
            for(String savedWord : savedWords){
                if(words.get(savedWord) != null){
                    words.remove(savedWord);
                }
            }

        } catch (IOException e) {
            mView.showErrorWhenProcessingFile();
        }
    }
}
