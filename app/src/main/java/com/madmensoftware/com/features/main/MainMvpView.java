package com.madmensoftware.com.features.main;

import java.util.List;

import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.features.base.MvpView;

public interface MainMvpView extends MvpView {

    void showBars(List<Bar> bars);

    void showProgress(boolean show);

    void showError(Throwable error);
}
