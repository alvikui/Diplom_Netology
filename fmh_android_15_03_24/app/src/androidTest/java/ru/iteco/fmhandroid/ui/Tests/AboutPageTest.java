package ru.iteco.fmhandroid.ui.Tests;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.Steps.AboutPageSteps;
import ru.iteco.fmhandroid.ui.Steps.HomePageSteps;
import ru.iteco.fmhandroid.ui.Steps.UserAuthorizationSteps;


@RunWith(AllureAndroidJUnit4.class)
public class AboutPageTest {
    HomePageSteps homePageSteps = new HomePageSteps();
    AboutPageSteps aboutPageSteps = new AboutPageSteps();
    UserAuthorizationSteps userAuthorizationSteps = new UserAuthorizationSteps();

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
    }

    @After
    public void tearDown() {
        try {
            homePageSteps.OpenTheApp();
            homePageSteps.logOutOfAccount();
        } catch (Exception ignored) {
        }
    }

    private void withIntents(Runnable testBody) {
        Intents.init();
        try {
            testBody.run();
        } finally {
            Intents.release();
        }
    }

    @Test
    @Story("29-ТК: Переход по ссылке Политика конфиденциальности")
    public void shouldOpenPrivacyPolicyLink() {
        withIntents(() -> {
            homePageSteps.loadingAboutAppPage();
            aboutPageSteps.clickOnThePrivacyPolicyLink();
            intended(hasData("https://vhospice.org/#/privacy-policy"));
            aboutPageSteps.checkingTextOnThePrivacyPolicyPage();
        });
    }

    @Test
    @Story("30-ТК: Переход по ссылке Пользовательское соглашение")
    public void shouldOpenTheLinkToTheUserAgreement() {
        withIntents(() -> {
            homePageSteps.loadingAboutAppPage();
            aboutPageSteps.clickOnTheTermsOfUseLink();
            intended(hasData("https://vhospice.org/#/terms-of-use"));
            aboutPageSteps.checkingTextOnTheTermOfUsePage();
        });
    }
}