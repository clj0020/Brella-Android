package com.madmensoftware.com.features.bar_detail;

import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.features.base.MvpView;

/**
 * Created by clj00 on 8/23/2017.
 */

public interface BarDetailMvpView extends MvpView {

    void showBar(Bar bar);

    void showProgress(boolean show);

    void showError(Throwable error);

    void showQrCode(String key);

}
