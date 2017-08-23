package com.madmensoftware.com.features.detail;

import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.features.base.MvpView;

public interface DetailMvpView extends MvpView {

    void showBar(Bar bar);

    void showProgress(boolean show);

    void showError(Throwable error);

    void showQrCode(String key);

    void showQrCodeDialog(String key);

}
