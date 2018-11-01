package com.untitledlangproject.flashcardcreator;

import dagger.Module;
import dagger.Provides;

@Module
public class FlashCardCreatorModule {

    @Provides
    public FlashCardCreatorMVP.Presenter provideFlashCardCreatorMVPPresenter(){
        return new FlashCardCreatorPresenter();
    }


}
