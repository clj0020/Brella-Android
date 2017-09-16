package com.madmensoftware.com;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import com.madmensoftware.com.common.TestDataFactory;
import com.madmensoftware.com.data.DataManager;
import com.madmensoftware.com.data.local.DbManager;
import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.data.remote.ParseService;
import com.madmensoftware.com.data.remote.StripeService;
import com.madmensoftware.com.util.RxSchedulersOverrideRule;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by shivam on 29/5/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Rule
    public final RxSchedulersOverrideRule overrideSchedulersRule = new RxSchedulersOverrideRule();

    @Mock
    private ParseService mockParseService;

    @Mock
    private StripeService mockStripeService;

    @Mock
    private DbManager mockDbManager;


    private DataManager dataManager;

    @Before
    public void setUp() {
        dataManager = new DataManager(mockParseService, mockStripeService, mockDbManager);
    }

//    @Test
//    public void getPokemonListCompletesAndEmitsPokemonList() {
//        List<NamedResource> namedResourceList = TestDataFactory.makeNamedResourceList(5);
//        BarListResponse barListResponse = new BarListResponse();
//        barListResponse.results = namedResourceList;
//
//        when(mockParseService.getBarList(anyInt()))
//                .thenReturn(Single.just(barListResponse));
//
//        dataManager
//                .getBarList(10)
//                .test()
//                .assertComplete()
//                .assertValue(TestDataFactory.makePokemonNameList(namedResourceList));
//    }

//    @Test
//    public void getPokemonCompletesAndEmitsPokemon() {
//        String name = "charmander";
//        Bar bar = TestDataFactory.makePokemon(name);
//        when(mockParseService.getBar(anyString())).thenReturn(Single.just(bar));
//
//        dataManager.getBar(name).test().assertComplete().assertValue(bar);
//    }
}
