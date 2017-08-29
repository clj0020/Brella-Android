package com.madmensoftware.com.ui.bar_list;

import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.ui.base.MvpView;

import java.util.List;

/**
 * Created by clj00 on 8/23/2017.
 */

public interface BarListMvpView extends MvpView {

    void showBars(List<Bar> bars);

    void showProgress(boolean show);

    void showError(Throwable error);

}
