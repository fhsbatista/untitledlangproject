package com.untitledlangproject.wordlist;

import android.content.Context;
import android.util.Log;

import com.untitledlangproject.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WordListModel implements WordListMVP.Model {

    @Override
    public BufferedReader requestContentTxtFile(Context context) throws IOException{

        //Opening the txt file
        InputStream ins = context.getResources().openRawResource(R.raw.timferris);
        BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
        Log.d("teste leitura", "Model return after reading has been reached");

        return reader;
    }

    @Override
    public List<String> requestSavedWordsList(Context context) throws IOException {

        //Creating the list that will be used on the return statement
        List<String> savedwords = new ArrayList<>();

        //Opening the file
        InputStream ins = context.getResources().openRawResource(R.raw.savedwords);
        BufferedReader reader = new BufferedReader(new InputStreamReader(ins));

        //Reading the file
        String line;
        while((line = reader.readLine())!=null){
            for(String word : line.toLowerCase().trim().split(" ")){
                word = word.replaceAll("[^a-zA-Z0-9_-]", "");
                savedwords.add(word);
            }
        }
        return savedwords;

    }
}
