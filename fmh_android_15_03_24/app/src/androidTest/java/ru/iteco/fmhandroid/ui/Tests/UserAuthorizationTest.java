package ru.iteco.fmhandroid.ui.Tests;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.CoreMatchers.not;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.Elements.HomePagePage;
import ru.iteco.fmhandroid.ui.Elements.UserAuthorizationPage;
import ru.iteco.fmhandroid.ui.Steps.HomePageSteps;
import ru.iteco.fmhandroid.ui.Steps.UserAuthorizationSteps;


@RunWith(AllureAndroidJUnit4.class)
public class UserAuthorizationTest {
    UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
    UserAuthorizationSteps userAuthorizationSteps = new UserAuthorizationSteps();
    HomePagePage homePagePage = new HomePagePage();
    HomePageSteps homePageSteps = new HomePageSteps();
    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        homePageSteps.OpenTheApp();
        try {
            userAuthorizationSteps.loadingTheLoginPage();
        } catch (Exception e) {
            userAuthorizationSteps.loadingTheLoginPage();
        }
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }

    @After
    public void tearDown() {
        try {
            homePageSteps.logOutOfAccount();
        } catch (Exception ignored) {
        }
    }

    @Test
    @Story("2-ТК: Ввод валидных данных в поля Логин и Пароль")
    public void shouldBeSuccessfulAuthorizationWhenEnteringValidData() {
        userAuthorizationSteps.validLogin();
        userAuthorizationSteps.validPassword();
        homePageSteps.loadingHomePage();
        homePageSteps.displayingAllElementsOnTheHomePage();
    }

    @Test
    @Story("3-ТК: Оставить поле Логин пустым")
    public void shouldNotLogInIfTheLoginFieldIsEmpty() {
        userAuthorizationSteps.validPassword();
        userAuthorizationSteps.checkNotificationText("Логин и пароль не могут быть пустыми", decorView);
        userAuthorizationPage.headline.check(matches(isDisplayed()));
        homePagePage.logo.check(matches(not(isDisplayed())));
    }

    @Test
    @Story("4-ТК: Ввод данных несуществующего пользователя в поле Логин")
    public void shouldNotLogIfLoginDoesNotExist() {
        userAuthorizationSteps.nonExistentLogin();
        userAuthorizationSteps.checkNotificationText("Что-то пошло не так. Попробуйте позднее.", decorView);
        userAuthorizationPage.headline.check(matches(isDisplayed()));
        homePagePage.logo.check(matches(not(isDisplayed())));
    }

    @Test
    @Story("5-ТК: Ввод спецсимволов в поле Логин")
    public void shouldNotLogIfLoginSpecialCharacter() {
        userAuthorizationSteps.loginSpecialCharacters();
        userAuthorizationSteps.checkNotificationText("Что-то пошло не так. Попробуйте позднее.", decorView);
        userAuthorizationPage.headline.check(matches(isDisplayed()));
        homePagePage.logo.check(matches(not(isDisplayed())));
    }

    @Test
    @Story("6-ТК: Ввод одного символа в поле Логин")
    public void shouldNotLogIfLoginOneCharacter() {
        userAuthorizationSteps.oneCharacterLogin();
        userAuthorizationSteps.checkNotificationText("Что-то пошло не так. Попробуйте позднее.", decorView);
        userAuthorizationPage.headline.check(matches(isDisplayed()));
        homePagePage.logo.check(matches(not(isDisplayed())));
    }

    @Test
    @Story("7-ТК: Ввод валидных данных в поле Логин с включенной клавишей CapsLock")
    public void shouldNotLogIfValidLoginCapsLock() {
        userAuthorizationSteps.validLoginCapsLock();
        userAuthorizationSteps.checkNotificationText("Что-то пошло не так. Попробуйте позднее.", decorView);
        userAuthorizationPage.headline.check(matches(isDisplayed()));
        homePagePage.logo.check(matches(not(isDisplayed())));
    }

    @Test
    @Story("8-ТК: Оставить поле Пароль пустым")
    public void shouldNotLogInIfThePasswordFieldIsEmpty() {
        userAuthorizationSteps.validLogin();
        userAuthorizationSteps.clickOKButton();
        userAuthorizationSteps.checkNotificationText("Логин и пароль не могут быть пустыми", decorView);
        userAuthorizationPage.headline.check(matches(isDisplayed()));
        homePagePage.logo.check(matches(not(isDisplayed())));
    }

    @Test
    @Story("9-ТК: Ввод данных несуществующего пользователя в поле Пароль")
    public void shouldNotLogIfPasswordDoesNotExist() {
        userAuthorizationSteps.nonExistentPassword();
        userAuthorizationSteps.checkNotificationText("Что-то пошло не так. Попробуйте позднее.", decorView);
        userAuthorizationPage.headline.check(matches(isDisplayed()));
        homePagePage.logo.check(matches(not(isDisplayed())));
    }

    @Test
    @Story("10-ТК: Ввод спецсимволов в поле Пароль")
    public void shouldNotLogIfPasswordSpecialCharacter() {
        userAuthorizationSteps.passwordSpecialCharacters();
        userAuthorizationSteps.checkNotificationText("Что-то пошло не так. Попробуйте позднее.", decorView);
        userAuthorizationPage.headline.check(matches(isDisplayed()));
        homePagePage.logo.check(matches(not(isDisplayed())));
    }

    @Test
    @Story("11-ТК: Ввод одного символа в поле Пароль")
    public void shouldNotLogIfPasswordOneCharacter() {
        userAuthorizationSteps.oneCharacterPassword();
        userAuthorizationSteps.checkNotificationText("Что-то пошло не так. Попробуйте позднее.", decorView);
        userAuthorizationPage.headline.check(matches(isDisplayed()));
        homePagePage.logo.check(matches(not(isDisplayed())));
    }

    @Test
    @Story("12-ТК: Ввод валидных данных в поле Пароль с включенной клавишей CapsLock")
    public void shouldNotLogIfValidPasswordCapsLock() {
        userAuthorizationSteps.validPasswordCapsLock();
        userAuthorizationSteps.checkNotificationText("Что-то пошло не так. Попробуйте позднее.", decorView);
        userAuthorizationPage.headline.check(matches(isDisplayed()));
        homePagePage.logo.check(matches(not(isDisplayed())));
    }

    @Test
    @Story("31-ТК: Выход из учетной записи")
    public void shouldSuccessfullyLogOutOfYourAccount() {
        userAuthorizationSteps.validLogin();
        userAuthorizationSteps.validPassword();
        homePageSteps.loadingHomePage();
        homePageSteps.logOutOfAccount();
        userAuthorizationSteps.displayingAllElementsOnAuthorization();
    }
}