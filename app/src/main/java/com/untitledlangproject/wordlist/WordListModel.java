package com.untitledlangproject.wordlist;

import android.content.Context;
import android.util.Log;

import com.untitledlangproject.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
    public List<String> requestSavedWordsList(Context context) throws IOException{
        List<String> savedWords = new ArrayList<>();

        try {
            InputStream inputStream = context.openFileInput("savedwords.txt");
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = "";

                while((line=reader.readLine())!=null){
                    savedWords.add(line);
                }

                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            //The file will be created
            File file = new File(context.getFilesDir(), "savedwords.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.close();

        }


        return savedWords;
    }






    @Override
    public void saveWord(Context context, String word) throws IOException {
        File file = new File(context.getFilesDir(), "savedwords.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        outputStreamWriter.write("\n" + word);
        outputStreamWriter.close();
    }
}
