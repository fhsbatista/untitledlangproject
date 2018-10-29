package com.untitledlangproject.selectcontent;


import dagger.Module;
import dagger.Provides;

@Module
public class SelectContentModule {

    @Provides
    public SelectContentMVP.Presenter provideSelectContentMVPPresenter(){
        return new SelectContentPresenter();
    }
}
