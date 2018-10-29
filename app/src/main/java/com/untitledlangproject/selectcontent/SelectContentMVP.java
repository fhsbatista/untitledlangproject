package com.untitledlangproject.selectcontent;

import android.content.Context;

public interface SelectContentMVP {

    interface View{
        void startWordListActivity();
    }

    interface Presenter{
        void setView(SelectContentMVP.View view);
        void botaoViewClicked();

    }

    interface Model{
        //None needed method so far
    }
}
