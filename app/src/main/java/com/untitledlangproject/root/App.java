package com.untitledlangproject.root;

import android.app.Application;

import com.untitledlangproject.flashcardcreator.FlashCardCreatorModule;
import com.untitledlangproject.selectcontent.SelectContentModule;
import com.untitledlangproject.wordlist.WordListModule;

import dagger.android.DaggerApplication;

public class App extends Application {

    private ApplicationComponent mApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .selectContentModule(new SelectContentModule())
                .wordListModule(new WordListModule())
                .flashCardCreatorModule(new FlashCardCreatorModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplication;
    }
}
