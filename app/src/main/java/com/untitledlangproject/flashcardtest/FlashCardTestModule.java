package com.untitledlangproject.flashcardtest;

import dagger.Module;
import dagger.Provides;

@Module
public class FlashCardTestModule {

    @Provides
    public FlashCardTestMVP.Presenter provideFlashCardTestMVPPresenter(){
        return new FlashCardTestPresenter();
    }
}
