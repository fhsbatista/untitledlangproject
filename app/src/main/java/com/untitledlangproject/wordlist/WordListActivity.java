package com.untitledlangproject.wordlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.untitledlangproject.R;
import com.untitledlangproject.dao.WordItem;
import com.untitledlangproject.flashcardcreator.FlashCardCreatorActivity;
import com.untitledlangproject.root.App;

import java.util.ArrayList;

import javax.inject.Inject;

public class WordListActivity extends AppCompatActivity implements WordListMVP.View{

    private static final String TAG = WordListActivity.class.getSimpleName();
    @Inject
    WordListMVP.Presenter mPresenter;

    //UI
    private ProgressBar mProgressBar, mWordsKnownProgressBar;
    private RecyclerView mRecyclerView;
    private Button mButtonFinish;
    private TextView mDifferentWordsPercent, mTotalWordsPercent;

    //Variables
    private WordAdapter mAdapter;
    private ArrayList<WordItem> mWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        ((App)getApplication()).getApplicationComponent().inject(this);

        //Load UI elements
        mProgressBar = findViewById(R.id.progressBar);
        mWordsKnownProgressBar = findViewById(R.id.pb_word_percent);
        mRecyclerView = findViewById(R.id.recycler_view);
        mButtonFinish = findViewById(R.id.bt_finish);
        mDifferentWordsPercent = findViewById(R.id.tv_different_words_percent);

        //Load RecyclerView attributes
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));




        mButtonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onFinishButtonClicked();
            }
        });

    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateWordList(ArrayList<WordItem> words) {
        mWords = words;
        mAdapter = new WordAdapter(mWords);
        mAdapter.setOnNoKeepButtonListener(new WordAdapter.OnClickListener() {
            @Override
            public void OnNoKeepButtonListener(String word) {
                mPresenter.saveWord(word);

            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorWhenProcessingFile() {
        Toast.makeText(this, "Error when opening the transcript File", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goFlashCreatorActivity() {
        Intent intent = new Intent(this, FlashCardCreatorActivity.class);
        intent.putParcelableArrayListExtra("wordsList", mWords);
        startActivity(intent);
    }

    @Override
    public void updateStats(int differenKnowntWordsNumber) {
        mDifferentWordsPercent.setText(String.valueOf(differenKnowntWordsNumber) + "%");
        mWordsKnownProgressBar.setProgress(differenKnowntWordsNumber);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setView(this);
        if(mAdapter != null){
            mAdapter.notifyDataSetChanged();
        }
    }

}
