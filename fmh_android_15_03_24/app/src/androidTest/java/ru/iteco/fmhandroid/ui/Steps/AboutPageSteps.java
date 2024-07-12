package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Elements.AboutPagePage;

public class AboutPageSteps {
    AboutPagePage aboutPagePage = new AboutPagePage();

    public void clickOnThePrivacyPolicyLink() {
        Allure.step("Переход по ссылке Политика конфиденциальности");
        aboutPagePage.privacyPolicyValue.perform(click());
    }

    public void clickOnTheTermsOfUseLink() {
        Allure.step("Переход по ссылке Пользовательское соглашению");
        aboutPagePage.termsOfUseValue.perform(click());
    }

    public void checkingTextOnThePrivacyPolicyPage() {
        Allure.step("Проверка cодержимого на странице Политика конфиденциальности");
        onView(withId(R.id.action_bar_root)).check(matches(isDisplayed()));
    }

    public void checkingTextOnTheTermOfUsePage() {
        Allure.step("Проверка cодержимого на странице Пользовательское соглашение");
        onView(withId(R.id.action_bar_root)).check(matches(isDisplayed()));
    }
}