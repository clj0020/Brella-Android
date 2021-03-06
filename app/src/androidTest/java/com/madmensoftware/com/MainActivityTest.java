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
import com.madmensoftware.com.ui.main.MainActivity;
import com.madmensoftware.com.util.ErrorTestUtil;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private final TestComponentRule componentRule =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());
    private final ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, false, false);

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule
    public TestRule chain = RuleChain.outerRule(componentRule).around(mainActivityActivityTestRule);

//    @Test
//    public void checkPokemonsDisplay() {
//        List<NamedResource> namedResourceList = TestDataFactory.makeNamedResourceList(5);
//        List<String> pokemonList = TestDataFactory.makePokemonNameList(namedResourceList);
//        stubDataManagerGetPokemonList(Single.just(pokemonList));
//        mainActivityActivityTestRule.launchActivity(null);
//
//        for (NamedResource pokemonName : namedResourceList) {
//            onView(withText(pokemonName.name)).check(matches(isDisplayed()));
//        }
//    }

//    @Test
//    public void clickingPokemonLaunchesDetailActivity() {
//        List<NamedResource> namedResourceList = TestDataFactory.makeNamedResourceList(5);
//        List<String> pokemonList = TestDataFactory.makePokemonNameList(namedResourceList);
//        stubDataManagerGetPokemonList(Single.just(pokemonList));
//        stubDataManagerGetPokemon(Single.just(TestDataFactory.makePokemon("id")));
//        mainActivityActivityTestRule.launchActivity(null);
//
//        onView(withText(pokemonList.get(0))).perform(click());
//
//        onView(withId(R.id.image_bar)).check(matches(isDisplayed()));
//    }

    @Test
    public void checkErrorViewDisplays() {
//        stubDataManagerGetPokemonList(Single.error(new RuntimeException()));
        mainActivityActivityTestRule.launchActivity(null);
        ErrorTestUtil.checkErrorViewsDisplay();
    }

//    public void stubDataManagerGetPokemonList(Single<List<String>> single) {
//        when(componentRule.getMockApiManager().getBarList(anyInt())).thenReturn(single);
//    }
//
//    public void stubDataManagerGetPokemon(Single<Bar> single) {
//        when(componentRule.getMockApiManager().getBar(anyString())).thenReturn(single);
//    }
}
