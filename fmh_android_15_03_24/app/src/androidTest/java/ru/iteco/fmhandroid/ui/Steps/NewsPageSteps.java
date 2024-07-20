package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.HelperUtil.elementWaiting;
import static ru.iteco.fmhandroid.ui.HelperUtil.withIndex;

import android.view.View;

import androidx.test.espresso.matcher.RootMatchers;

import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Elements.HomePagePage;
import ru.iteco.fmhandroid.ui.Elements.NewsPagePage;

public class NewsPageSteps {
    NewsPagePage newsPagePage = new NewsPagePage();
    HomePagePage homePagePage = new HomePagePage();

    public void displayingAllElementsOnNewsPage() {
        Allure.step("Проверка отображения всех элементов на странице новостей");
        newsPagePage.newsListSwipeRefresh.check(matches(isDisplayed()));
        newsPagePage.allNewsCardsBlock.check(matches(isDisplayed()));
        newsPagePage.newsItemCardView.check(matches(isDisplayed()));
    }

    public void controlPanelPage() {
        Allure.step("Загрузка страницы Панель управления");
        homePagePage.controlPanelButton.perform(click());
        elementWaiting(withId(R.id.add_news_image_view), 5000);
    }

    public void displayingAllElementsOnControlPanel() {
        Allure.step("Проверка отображения всех элементов на странице Панель управления");
        elementWaiting(withId(R.id.add_news_image_view), 5000);
        newsPagePage.logoControlPanel.check(matches(isDisplayed()));
        newsPagePage.sortButton.check(matches(isDisplayed()));
        newsPagePage.filterButton.check(matches(isDisplayed()));
        newsPagePage.addNewsButton.check(matches(isDisplayed()));
    }

    public void openNewsCreationPage() {
        Allure.step("Открыть страницу создания новостей");
        newsPagePage.addNewsButton.perform(click());
    }

    public void deleteNews(String newsTitle) {
        Allure.step("Удалить новость");
        newsPagePage.deleteNewsButton(newsTitle).perform(click());
        newsPagePage.OKButtonInNotification.perform(click());
    }

    public void openNewsEditing(String newsTitle) {
        Allure.step("Открыть редактирование новости");
        newsPagePage.editNewsButton(newsTitle).perform(click());
    }

    public void checkingNewsByTitle(String titleText) {
        Allure.step("Проверка новости по заголовку");
        onView(withIndex(allOf(withText(titleText), isDisplayed()), 0)).check(matches(isDisplayed()));
    }

    public void checkingNoNewsByTitle(String titleText) {
        Allure.step("Отсутствие новость с определенным заголовком");
        onView(withIndex(allOf(withText(titleText), isDisplayed()), 0)).check(doesNotExist());
    }

    public void displayingAllElementsOnNewsCreation() {
        Allure.step("Проверка отображения всех элементов на странице создания новости");
        newsPagePage.headline.check(matches(isDisplayed()));
        newsPagePage.selectCategory.check(matches(isDisplayed()));
        newsPagePage.newsHeadline.check(matches(isDisplayed()));
        newsPagePage.newsDescription.check(matches(isDisplayed()));
        newsPagePage.newsPublicationDate.check(matches(isDisplayed()));
        newsPagePage.newsPublicationTime.check(matches(isDisplayed()));
        newsPagePage.activitySwitch.check(matches(isDisplayed()));
        newsPagePage.saveButton.check(matches(isDisplayed()));
        newsPagePage.cancelButton.check(matches(isDisplayed()));
    }

    public void fillingOutTheCategory(String text) {
        Allure.step("Поле Категория");
        newsPagePage.selectCategory.perform(replaceText(text));
    }

    public void fillingOutTheTitle(String text) {
        Allure.step("Поле Заголовок");
        newsPagePage.newsHeadline.perform(replaceText(text));
    }

    public void fillingOutTheDate(String text) {
        Allure.step("Поле Дата публикации");
        newsPagePage.newsPublicationDate.perform(replaceText(text));
    }

    public void fillingOutTheTime(String text) {
        Allure.step("Поле Время");
        newsPagePage.newsPublicationTime.perform(replaceText(text));
    }

    public void fillingOutTheDescription(String text) {
        Allure.step("Поле Описание");
        newsPagePage.newsDescription.perform(replaceText(text));
    }

    public void newsCreation(String category, String title, String publicationDate, String publicationTime, String description) {
        Allure.step("Создание новости");
        fillingOutTheCategory(category);
        fillingOutTheTitle(title);
        fillingOutTheDate(publicationDate);
        fillingOutTheTime(publicationTime);
        fillingOutTheDescription(description);
    }

    public void changeNewsStatus() {
        Allure.step("Смена статуса новости");
        newsPagePage.activitySwitch.perform(click());
    }

    public void clickSaveButton() {
        Allure.step("Кнопка Сохранить");
        newsPagePage.saveButton.perform(click());
    }

    public void clickCancelButton() {
        Allure.step("Кнопка Отмена");
        newsPagePage.cancelButton.perform(click());
    }

    public void clickOKButton() {
        Allure.step("Кнопка ОК");
        newsPagePage.OKButtonInNotification.perform(click());
    }

    public static final String emptyFieldsMessage = "Заполните пустые поля";

    public void checkNotificationText(String expectedText, View decorView) {
        Allure.step("Проверка уведомления");
        onView(withText(expectedText)).inRoot(RootMatchers.withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }
}