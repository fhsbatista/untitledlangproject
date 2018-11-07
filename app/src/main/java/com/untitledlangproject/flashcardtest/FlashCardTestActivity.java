package com.untitledlangproject.flashcardtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.untitledlangproject.R;
import com.untitledlangproject.dao.FlashCard;
import com.untitledlangproject.root.App;
import com.untitledlangproject.selectcontent.SelectContentActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FlashCardTestActivity extends AppCompatActivity implements FlashCardTestMVP.View {

    @Inject
    FlashCardTestMVP.Presenter mPresenter;

    //Variables
    private List<FlashCard> mFlashCardList = new ArrayList<>();

    //UI
    private TextView mNumberOfUsagesTextView, mWordTextView, mTranslationTextView;
    private Button mThumbsUpButton, mThumbsDownButton, mCheckAnswerButton;
    private RelativeLayout mButtonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_test);
        ((App)getApplication()).getApplicationComponent().inject(this);

        //Load the UI
        mNumberOfUsagesTextView = findViewById(R.id.tv_number_of_usages);
        mWordTextView = findViewById(R.id.tv_word);
        mTranslationTextView = findViewById(R.id.tv_translation);
        mThumbsUpButton = findViewById(R.id.bt_thumbs_up);
        mThumbsDownButton = findViewById(R.id.bt_thumbs_down);
        mCheckAnswerButton = findViewById(R.id.bt_check_answer);
        mButtonLayout = findViewById(R.id.layout_buttons);


        //Load the list that comes from the FlashCardCreatorActivity
        //This list contains the words that will be tested
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            if(extras.getParcelableArrayList("flashcardslist") != null){
                mFlashCardList = extras.getParcelableArrayList("flashcardslist");
                mPresenter.sendFlashCardList(mFlashCardList);
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setView(this);
        mPresenter.startTests();

    }



    @Override
    public void showWordForTesting(String word, int numberOfUsages, String translation) {
        mWordTextView.setText(word);
        mNumberOfUsagesTextView.setText(String.valueOf(numberOfUsages));
        mTranslationTextView.setText(translation);

        mCheckAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.notifyUserAnswer();
            }
        });
    }

    @Override
    public void hideConfirmationLayout() {
        mCheckAnswerButton.setVisibility(View.VISIBLE);
        mButtonLayout.setVisibility(View.GONE);
        mTranslationTextView.setVisibility(View.GONE);
        mWordTextView.setTextSize(48);
    }

    @Override
    public void showConfirmationLayout() {
        mCheckAnswerButton.setVisibility(View.GONE);
        mButtonLayout.setVisibility(View.VISIBLE);
        mTranslationTextView.setVisibility(View.VISIBLE);
        mWordTextView.setTextSize(28);



        mThumbsUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.notifyUserFeedback(true);
            }
        });

        mThumbsDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.notifyUserFeedback(false);
            }
        });
    }

    @Override
    public void goToSelectContentActivity() {
        startActivity(new Intent(this, SelectContentActivity.class));
    }
}
