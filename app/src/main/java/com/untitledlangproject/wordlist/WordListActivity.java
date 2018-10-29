package com.untitledlangproject.wordlist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.untitledlangproject.R;
import com.untitledlangproject.dao.WordItem;
import com.untitledlangproject.root.App;

import java.util.List;

import javax.inject.Inject;

public class WordListActivity extends AppCompatActivity implements WordListMVP.View{

    @Inject
    WordListMVP.Presenter mPresenter;

    //UI
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;

    //Variables
    private WordAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        ((App)getApplication()).getApplicationComponent().inject(this);

        //Load UI elements
        mProgressBar = findViewById(R.id.progressBar);
        mRecyclerView = findViewById(R.id.recycler_view);

        //Load RecyclerView attributes
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

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
    public void updateWordList(List<WordItem> words) {
        mAdapter = new WordAdapter(words);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorWhenProcessingFile() {
        Toast.makeText(this, "Error when opening the transcript File", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setView(this);
    }
}
