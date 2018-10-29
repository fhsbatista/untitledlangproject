package com.untitledlangproject.root;

import com.untitledlangproject.selectcontent.SelectContentActivity;
import com.untitledlangproject.selectcontent.SelectContentModule;
import com.untitledlangproject.wordlist.WordListActivity;
import com.untitledlangproject.wordlist.WordListModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, SelectContentModule.class, WordListModule.class})
public interface ApplicationComponent {

    void inject(SelectContentActivity target);
    void inject(WordListActivity target);


}
