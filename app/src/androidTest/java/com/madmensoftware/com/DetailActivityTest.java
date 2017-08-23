package com.madmensoftware.com;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import com.madmensoftware.com.common.TestComponentRule;
import com.madmensoftware.com.common.TestDataFactory;
import com.madmensoftware.com.data.model.response.Bar;
import com.madmensoftware.com.features.detail.DetailActivity;
import com.madmensoftware.com.util.ErrorTestUtil;
import io.reactivex.Single;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    public final TestComponentRule component =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());
    public final ActivityTestRule<DetailActivity> main =
            new ActivityTestRule<>(DetailActivity.class, false, false);

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule
    public TestRule chain = RuleChain.outerRule(component).around(main);

//    @Test
//    public void checkPokemonDisplays() {
//        Bar bar = TestDataFactory.makePokemon("id");
//        stubDataManagerGetPokemon(Single.just(bar));
//        main.launchActivity(
//                DetailActivity.getStartIntent(InstrumentationRegistry.getContext(), bar.name));
//    }
//
//    @Test
//    public void checkErrorViewDisplays() {
//        stubDataManagerGetPokemon(Single.error(new RuntimeException()));
//        Bar bar = TestDataFactory.makePokemon("id");
//        main.launchActivity(
//                DetailActivity.getStartIntent(InstrumentationRegistry.getContext(), bar.name));
//        ErrorTestUtil.checkErrorViewsDisplay();
//    }
//
//    public void stubDataManagerGetPokemon(Single<Bar> single) {
//        when(component.getMockApiManager().getBar(anyString())).thenReturn(single);
//    }
}
