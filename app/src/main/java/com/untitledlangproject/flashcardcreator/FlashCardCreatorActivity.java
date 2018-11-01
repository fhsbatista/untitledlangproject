package com.untitledlangproject.flashcardcreator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.untitledlangproject.R;
import com.untitledlangproject.dao.FlashCard;
import com.untitledlangproject.dao.WordItem;
import com.untitledlangproject.flashcardtest.FlashCardTestActivity;
import com.untitledlangproject.root.App;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FlashCardCreatorActivity extends AppCompatActivity implements FlashCardCreatorMVP.View {

    @Inject
    FlashCardCreatorMVP.Presenter mPresenter;

    //Variables
    private SwipeFlingAdapterView mSwiper;
    private ArrayAdapter<WordItem> mArrayAdapter;
    private ArrayList<WordItem> mListWord;

    //UI
    private Button mButtonSave;
    private EditText mEditTextTranslation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_creator);

        ((App) getApplication()).getApplicationComponent().inject(this);

        //Load UI
        mSwiper = findViewById(R.id.swiper);
        mButtonSave = findViewById(R.id.bt_save);
        mEditTextTranslation = findViewById(R.id.et_translation);

        //Load the adapter with the arraylist, which comes from the WordItemActivity and has all the words needed
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.getParcelableArrayList("wordsList") != null) {
                mListWord = extras.getParcelableArrayList("wordsList");
            }
        }

        //Initialize the adapter that will be attached to the Swiper object and attaches it to the Swiper
        mArrayAdapter = new ArrayAdapter<>(this, R.layout.adapter_flashcard, R.id.tv_word, mListWord);
        mSwiper.setAdapter(mArrayAdapter);

        //Overriding Swiper's methods
        mSwiper.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                mListWord.remove(0);
                mArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {
            }

            @Override
            public void onRightCardExit(Object o) {
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                mPresenter.notifyWordRemoved(mListWord);
            }

            @Override
            public void onScroll(float v) {
                View view = mSwiper.getSelectedView();
                view.findViewById(R.id.tv_remove_left).setAlpha(v > 0 ? v : 0);
                view.findViewById(R.id.tv_remove_right).setAlpha(v < 0 ? -v : 0);
            }
        });


        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WordItem word = mListWord.get(0);
                String translation = mEditTextTranslation.getText().toString();
                mPresenter.saveWordRequest(word, translation);
            }
        });

    }

    @Override
    public void showSavedLabel() {
        View view = mSwiper.getSelectedView();
        view.findViewById(R.id.tv_saved).setAlpha(1);
        mSwiper.getTopCardListener().selectRight();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setView(this);
    }

    @Override
    public void showListIsOver() {
        Toast.makeText(this, "The list is over", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputTranslationError() {
        Toast.makeText(this, "You have to type something on the translation's field!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToFlashCardTestActivity(ArrayList<FlashCard> flashCardList) {
        Intent intent = new Intent(this, FlashCardTestActivity.class);
        intent.putParcelableArrayListExtra("flashcardslist", flashCardList);
        startActivity(intent);
        finish();
    }
}
