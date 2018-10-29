package com.untitledlangproject.selectcontent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.untitledlangproject.R;
import com.untitledlangproject.root.App;
import com.untitledlangproject.wordlist.WordListActivity;

import javax.inject.Inject;

public class SelectContentActivity extends AppCompatActivity implements SelectContentMVP.View{

    @Inject
    SelectContentMVP.Presenter mPresenter;

    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_content);

        ((App)getApplication()).getApplicationComponent().inject(this);

        mButton = findViewById(R.id.btn);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.botaoViewClicked();
            }
        });

    }

    @Override
    public void startWordListActivity() {
        startActivity(new Intent(this, WordListActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setView(this);
    }


}
