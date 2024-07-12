package ru.iteco.fmhandroid.ui.Tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.Steps.HomePageSteps;
import ru.iteco.fmhandroid.ui.Steps.TopicQuotesPageSteps;
import ru.iteco.fmhandroid.ui.Steps.UserAuthorizationSteps;

@RunWith(AllureAndroidJUnit4.class)
public class TopicQuotesPageTest {
    HomePageSteps homePageSteps = new HomePageSteps();
    UserAuthorizationSteps userAuthorizationSteps = new UserAuthorizationSteps();
    TopicQuotesPageSteps topicQuotesPageSteps = new TopicQuotesPageSteps();

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        homePageSteps.OpenTheApp();
        try {
            homePageSteps.loadingHomePage();
        } catch (Exception e) {
            userAuthorizationSteps.validLogin();
            userAuthorizationSteps.validPassword();
            homePageSteps.loadingHomePage();
        }
        homePageSteps.loadingQuotesPage();
    }

    @After
    public void tearDown() {
        try {
            homePageSteps.logOutOfAccount();
        } catch (Exception ignored) {
        }
    }

    @Test
    @Story("27-ТК: Наличие цитат на странице с тематическими цитатами")
    public void shouldBeDisplayedThematicCitations() {
        topicQuotesPageSteps.displayingAllElementsOnQuotesPage();
    }

    @Test
    @Story("28-ТК: Сворачивание и разворачивание цитаты на странице тематических цитатат")
    public void shouldToggleQuoteDisplay() {
        String quoteTestText = "Нет шаблона и стандарта, есть только дух, который живет в разных домах по-разному. Но всегда он добрый, любящий и помогающий.";
        topicQuotesPageSteps.collapseAndExpandTopicQuote(1);
        topicQuotesPageSteps.descriptionOfTheQuoteAsItUnfolds(quoteTestText);
        topicQuotesPageSteps.collapseAndExpandTopicQuote(1);
    }
}