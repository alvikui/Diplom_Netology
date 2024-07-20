package ru.iteco.fmhandroid.ui.Tests;

import static ru.iteco.fmhandroid.ui.HelperUtil.randomCategory;
import static ru.iteco.fmhandroid.ui.HelperUtil.getCurrentDate;
import static ru.iteco.fmhandroid.ui.HelperUtil.getCurrentTime;
import static ru.iteco.fmhandroid.ui.Steps.NewsPageSteps.emptyFieldsMessage;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

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
public class NewsPageTest {
    HomePageSteps homePageSteps = new HomePageSteps();
    UserAuthorizationSteps userAuthorizationSteps = new UserAuthorizationSteps();
    NewsPageSteps newsPageSteps = new NewsPageSteps();

    private View decorView;

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

    @Test
    @Story("21-ТК: Cоздание новости с валидными данными")
    public void shouldBeANewsArticleSuccessfullyCreatedWithValidData() {
        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();
        String title = "Новость8";
        String description = "Описание новости8";
        homePageSteps.loadingTheNewsPage();
        newsPageSteps.controlPanelPage();
        newsPageSteps.openNewsCreationPage();
        newsPageSteps.newsCreation(randomCategory(), title, publicationDate, publicationTime, description);
        newsPageSteps.clickSaveButton();
        newsPageSteps.checkingNewsByTitle(title);
    }

    @Test
    @Story("22-ТК: Создание новости незаполненными данными")
    public void shouldNotBeCreatedNewsWithEmptyFields() {
        homePageSteps.loadingTheNewsPage();
        newsPageSteps.controlPanelPage();
        newsPageSteps.openNewsCreationPage();
        newsPageSteps.clickSaveButton();
        newsPageSteps.checkNotificationText(emptyFieldsMessage, decorView);
    }

    @Test
    @Story("23-ТК: Отмена создания новости")
    public void shouldNotBeCreatedNewsWhenCreationIsCancelled() {
        homePageSteps.loadingTheNewsPage();
        newsPageSteps.controlPanelPage();
        newsPageSteps.openNewsCreationPage();
        newsPageSteps.clickCancelButton();
        newsPageSteps.clickOKButton();
        newsPageSteps.displayingAllElementsOnControlPanel();
    }

    @Test
    @Story("24-ТК: Удаление новости")
    public void shouldBeDeletedNews() {
        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();
        String title = "Новость707";
        String description = "Новость707";
        homePageSteps.loadingTheNewsPage();
        newsPageSteps.controlPanelPage();
        newsPageSteps.openNewsCreationPage();
        newsPageSteps.newsCreation(randomCategory(), title, publicationDate, publicationTime, description);
        newsPageSteps.clickSaveButton();
        newsPageSteps.deleteNews(title);
        newsPageSteps.displayingAllElementsOnControlPanel();
        newsPageSteps.checkingNoNewsByTitle(title);
    }

    @Test
    @Story("25-ТК: Редактирование новости")
    public void shouldBeEditedNews() {
        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();
        String title = "Новость013";
        String description = "Описание013";
        String newTitle = "Новость отредактирована013";
        String newDescription = "Описание отредактировано013";
        homePageSteps.loadingTheNewsPage();
        newsPageSteps.controlPanelPage();
        newsPageSteps.openNewsCreationPage();
        newsPageSteps.newsCreation(randomCategory(), title, publicationDate, publicationTime, description);
        newsPageSteps.clickSaveButton();
        newsPageSteps.openNewsEditing(title);
        newsPageSteps.displayingAllElementsOnNewsCreation();
        newsPageSteps.newsCreation(randomCategory(), newTitle, publicationDate, publicationTime, newDescription);
        newsPageSteps.changeNewsStatus();
        newsPageSteps.clickSaveButton();
        newsPageSteps.checkingNewsByTitle(newTitle);
    }

    @Test
    @Story("26-ТК: Отмена редактирования новости")
    public void shouldNotBeEditedNewsWhenCanceled() {
        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();
        String title = "Новость694";
        String description = "Новость694";
        homePageSteps.loadingTheNewsPage();
        newsPageSteps.controlPanelPage();
        newsPageSteps.openNewsCreationPage();
        newsPageSteps.newsCreation(randomCategory(), title, publicationDate, publicationTime, description);
        newsPageSteps.clickSaveButton();
        newsPageSteps.openNewsEditing(title);
        newsPageSteps.displayingAllElementsOnNewsCreation();
        newsPageSteps.changeNewsStatus();
        newsPageSteps.clickCancelButton();
        newsPageSteps.clickOKButton();
        newsPageSteps.displayingAllElementsOnControlPanel();
    }
}