package com.untitledlangproject.selectcontent;

import android.widget.Toast;

public class SelectContentPresenter implements SelectContentMVP.Presenter {

    private SelectContentMVP.View mView;

    public SelectContentPresenter() {
    }

    @Override
    public void botaoViewClicked() {
        mView.startWordListActivity();
    }


    @Override
    public void setView(SelectContentMVP.View view) {
        mView = view;
    }
}
