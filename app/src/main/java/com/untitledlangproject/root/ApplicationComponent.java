package com.untitledlangproject.root;

import com.untitledlangproject.flashcardcreator.FlashCardCreatorActivity;
import com.untitledlangproject.flashcardcreator.FlashCardCreatorModule;
import com.untitledlangproject.flashcardtest.FlashCardTestActivity;
import com.untitledlangproject.flashcardtest.FlashCardTestMVP;
import com.untitledlangproject.flashcardtest.FlashCardTestModule;
import com.untitledlangproject.selectcontent.SelectContentActivity;
import com.untitledlangproject.selectcontent.SelectContentModule;
import com.untitledlangproject.wordlist.WordListActivity;
import com.untitledlangproject.wordlist.WordListModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {  ApplicationModule.class,
                        SelectContentModule.class,
                        WordListModule.class,
                        FlashCardCreatorModule.class,
                        FlashCardTestModule.class
                        })
public interface ApplicationComponent {

    void inject(SelectContentActivity target);
    void inject(WordListActivity target);
    void inject(FlashCardCreatorActivity target);
    void inject(FlashCardTestActivity target);


}
