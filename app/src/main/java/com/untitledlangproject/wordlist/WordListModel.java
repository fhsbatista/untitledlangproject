package com.untitledlangproject.wordlist;

import android.content.Context;
import android.util.Log;

import com.untitledlangproject.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WordListModel implements WordListMVP.Model {

    @Override
    public BufferedReader requestTxtFile(Context context) throws IOException{

        //Opening the txt file
        InputStream ins = context.getResources().openRawResource(R.raw.timferris);
        BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
        Log.d("teste leitura", "Model return after reading has been reached");

        return reader;
    }
}
