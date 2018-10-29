package com.untitledlangproject.wordlist;

import dagger.Module;
import dagger.Provides;

@Module
public class WordListModule {

    @Provides
    public WordListMVP.Presenter provideWordListPresenter(WordListMVP.Model model){
        return new WordListPresenter(model);
    }

    @Provides
    public WordListMVP.Model provideWordListModel(){
        return new WordListModel();
    }
}
