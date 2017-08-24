package com.madmensoftware.com.features.settings;

import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.features.base.BasePresenter;
import javax.inject.Inject;

/**
 * Created by clj00 on 8/24/2017.
 */

public class SettingsPresenter extends BasePresenter<SettingsMvpView> {

    private final DataManager dataManager;

    @Inject
    public SettingsPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(SettingsMvpView mvpView) {
        super.attachView(mvpView);
    }



}
