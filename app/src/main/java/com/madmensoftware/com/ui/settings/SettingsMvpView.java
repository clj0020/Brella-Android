package com.madmensoftware.com.ui.settings;

import com.madmensoftware.com.data.model.response.User;
import com.madmensoftware.com.ui.base.MvpView;

/**
 * Created by clj00 on 8/24/2017.
 */

public interface SettingsMvpView extends MvpView {

    void showLoginFragment();

    void showUserInfo(User user);

}
