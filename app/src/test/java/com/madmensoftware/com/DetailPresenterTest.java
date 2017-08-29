package com.madmensoftware.com;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.madmensoftware.com.common.TestDataFactory;
import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.ui.detail.DetailMvpView;
import com.madmensoftware.com.ui.detail.DetailPresenter;
import com.madmensoftware.com.util.RxSchedulersOverrideRule;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ravindra on 24/12/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetailPresenterTest {

    @Rule
    public final RxSchedulersOverrideRule overrideSchedulersRule = new RxSchedulersOverrideRule();

    @Mock
    DetailMvpView mockDetailMvpView;
    @Mock
    DataManager mockDataManager;
    private DetailPresenter detailPresenter;

    @Before
    public void setUp() {
        detailPresenter = new DetailPresenter(mockDataManager);
        detailPresenter.attachView(mockDetailMvpView);
    }

    @After
    public void tearDown() {
        detailPresenter.detachView();
    }

    @Test
    public void getPokemonDetailReturnsPokemon() throws Exception {
        Bar bar = TestDataFactory.makePokemon("id");
        when(mockDataManager.getBar(anyString())).thenReturn(Single.just(bar));

        detailPresenter.getBar(anyString());

        verify(mockDetailMvpView, times(2)).showProgress(anyBoolean());
        verify(mockDetailMvpView).showBar(bar);
        verify(mockDetailMvpView, never()).showError(any(Throwable.class));
    }

    @Test
    public void getPokemonDetailReturnsError() throws Exception {
        when(mockDataManager.getBar("id")).thenReturn(Single.error(new RuntimeException()));

        detailPresenter.getBar("id");

        verify(mockDetailMvpView, times(2)).showProgress(anyBoolean());
        verify(mockDetailMvpView).showError(any(Throwable.class));
        verify(mockDetailMvpView, never()).showBar(any(Bar.class));
    }
}
