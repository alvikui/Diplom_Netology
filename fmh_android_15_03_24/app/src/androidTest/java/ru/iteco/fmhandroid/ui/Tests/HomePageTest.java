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
import ru.iteco.fmhandroid.ui.Steps.NewsPageSteps;
import ru.iteco.fmhandroid.ui.Steps.UserAuthorizationSteps;


@RunWith(AllureAndroidJUnit4.class)
public class HomePageTest {
    HomePageSteps homePageSteps = new HomePageSteps();
    NewsPageSteps newsPageSteps = new NewsPageSteps();
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
            homePageSteps.logOutOfAccount();
        } catch (Exception ignored) {
        }
    }

    @Test
    @Story("13-ТК: Сворачивание и разворачивание вкладки Новости на главной странице приложения")
    public void shouldExpandAndCollapseNewsListOnMainPage() {
        homePageSteps.clickListNews();
        homePageSteps.clickListNews();
        homePageSteps.displayingAllElementsOnTheHomePage();
    }

    @Test
    @Story("14-ТК: Разворачивание новости на главной странице приложения")
    public void shouldExpandNewsItemDescriptionOnMainPage() {
        homePageSteps.clickListNewInBlock();
        homePageSteps.checkListNewInBlock();
    }

    @Test
    @Story("15-ТК: Переход на страницу Новости нажатием на главной странице на надпись ВСЕ НОВОСТИ")
    public void shouldCheckAllNewsButtonIsDisplayed() {
        homePageSteps.displayingAllElementsOnTheHomePage();
        homePageSteps.clickAllNewsButtonIsDisplayed();
        newsPageSteps.displayingAllElementsOnNewsPage();
    }
}