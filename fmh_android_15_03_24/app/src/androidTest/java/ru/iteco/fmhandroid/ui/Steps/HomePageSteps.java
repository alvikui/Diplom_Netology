package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.HelperUtil.elementWaiting;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Elements.HomePagePage;

public class HomePageSteps {
    HomePagePage homePagePage = new HomePagePage();

    public void loadingHomePage() {
        Allure.step("Загрузка страницы");
        elementWaiting(withId(R.id.all_news_text_view), 10000);
    }

    public void displayingAllElementsOnTheHomePage() {
        Allure.step("Проверка отображения всех элементов на странице Главная");
        homePagePage.profileButton.check(matches(isDisplayed()));
        homePagePage.menuButton.check(matches(isDisplayed()));
        homePagePage.topicQuotesButton.check(matches(isDisplayed()));
        homePagePage.newsHeadline.check(matches(isDisplayed()));
        homePagePage.allNewsButton.check(matches(isDisplayed()));
    }

    public void loadingTheNewsPage() {
        Allure.step("Загрузка страницы Новости");
        homePagePage.menuButton.perform(click());
        homePagePage.newsButtonInMenu.perform(click());
    }

    public void loadingAboutAppPage() {
        Allure.step("Загрузка страницы О приложении");
        homePagePage.menuButton.perform(click());
        homePagePage.aboutApplicationButtonInMenu.perform(click());
    }

    public void loadingQuotesPage() {
        Allure.step("Загрузка страницы с тематическими цитатами");
        homePagePage.topicQuotesButton.perform(click());
    }

    public void logOutOfAccount() {
        Allure.step("Выход из учетной записи");
        homePagePage.profileButton.perform(click());
        homePagePage.exitButton.perform(click());
    }

    public void OpenTheApp() {
        Allure.step("Запуск приложения");
        elementWaiting(withId(R.id.splashscreen_image_view), 10000000);
    }

    public void clickAllNewsButtonIsDisplayed() {
        Allure.step("Нажатие на кнопку 'Все новости'");
        homePagePage.allNewsButton.perform(click());
    }

    public void clickListNews() {
        Allure.step("Разворачивание и сворачивание новостной ленты");
        homePagePage.listNews.perform(click());
    }

    public void clickListNewInBlock() {
        Allure.step("Разворачивание описания новости");
        onView(homePagePage.listNewInBlock()).perform(click());
    }

    public void checkListNewInBlock() {
        Allure.step("Отображение описания новости");
        homePagePage.newsItemCardView.check(matches(isDisplayed()));
    }
}