package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.HelperUtil.elementWaiting;

import android.view.View;

import androidx.test.espresso.matcher.RootMatchers;

import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Elements.UserAuthorizationPage;
import ru.iteco.fmhandroid.ui.HelperUtil;

public class UserAuthorizationSteps {
    UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
    HelperUtil helperUtil = new HelperUtil();

    public void loadingTheLoginPage() {
        Allure.step("Загрузка страницы авторизации");
        elementWaiting(withId(R.id.enter_button), 10000);
    }

    public void displayingAllElementsOnAuthorization() {
        Allure.step("Проверка отображения всех элементов на страницe авторизации");
        userAuthorizationPage.headline.check(matches(isDisplayed()));
        userAuthorizationPage.login.check(matches(isDisplayed()));
        userAuthorizationPage.password.check(matches(isDisplayed()));
        userAuthorizationPage.loginButton.check(matches(isDisplayed()));
    }

    public void enterLogin(String login) {
        Allure.step("Ввод логина");
        userAuthorizationPage.login.perform(typeText(login), closeSoftKeyboard());
    }

    public void enterPassword(String password) {
        Allure.step("Ввод пароля");
        userAuthorizationPage.password.perform(typeText(password), closeSoftKeyboard());
    }

    public void clickOKButton() {
        userAuthorizationPage.loginButton.perform(click());
    }

    public void validLogin() {
        Allure.step("Ввод валидного логина");
        enterLogin(helperUtil.getValidUser().getLogin());
    }

    public void validPassword() {
        Allure.step("Ввод валидного пароля");
        enterPassword(helperUtil.getValidUser().getPassword());
        clickOKButton();
    }

    public void nonExistentLogin() {
        Allure.step("Ввод данных несуществующего пользователя в поле Логин");
        enterLogin(helperUtil.nonExistentLogin().getLogin());
        enterPassword(helperUtil.getValidUser().getPassword());
        clickOKButton();
    }

    public void nonExistentPassword() {
        Allure.step("Ввод данных несуществующего пользователя в поле Пароль");
        enterLogin(helperUtil.getValidUser().getLogin());
        enterPassword(helperUtil.nonExistentPassword().getPassword());
        clickOKButton();
    }

    public void loginSpecialCharacters() {
        Allure.step("Ввод спецсимволов в поле Логин");
        enterLogin(helperUtil.loginSpecialCharacters().getLogin());
        enterPassword(helperUtil.getValidUser().getPassword());
        clickOKButton();
    }

    public void passwordSpecialCharacters() {
        Allure.step("Ввод спецсимволов в поле Пароль");
        enterLogin(helperUtil.getValidUser().getLogin());
        enterPassword(helperUtil.passwordSpecialCharacters().getPassword());
        clickOKButton();
    }

    public void oneCharacterLogin() {
        Allure.step("Ввод одного символа в поле Логин");
        enterLogin(helperUtil.oneCharacterLogin().getLogin());
        enterPassword(helperUtil.getValidUser().getPassword());
        clickOKButton();
    }

    public void oneCharacterPassword() {
        Allure.step("Ввод одного символа в поле Пароль");
        enterLogin(helperUtil.getValidUser().getLogin());
        enterPassword(helperUtil.oneCharacterPassword().getPassword());
        clickOKButton();
    }

    public void validLoginCapsLock() {
        Allure.step("Ввод валидных данных в поле Логин с включенной клавишей CapsLock");
        enterLogin(helperUtil.validLoginCapsLock().getLogin());
        enterPassword(helperUtil.getValidUser().getPassword());
        clickOKButton();
    }

    public void validPasswordCapsLock() {
        Allure.step("Ввод валидных данных в поле Пароль с включенной клавишей CapsLock");
        enterLogin(helperUtil.getValidUser().getLogin());
        enterPassword(helperUtil.validPasswordCapsLock().getPassword());
        clickOKButton();
    }

    public static final String EmptyLoginPasswordMessage = "Логин и пароль не могут быть пустыми";
    public static final String InvalidLoginMessage = "Логин и/или пароль введен неверно";

    public void checkNotificationText(String expectedText, View decorView) {
        Allure.step("Проверка уведомления");
        onView(withText(expectedText)).inRoot(RootMatchers.withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }
}