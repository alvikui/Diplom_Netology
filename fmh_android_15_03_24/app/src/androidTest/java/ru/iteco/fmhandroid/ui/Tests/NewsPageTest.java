package ru.iteco.fmhandroid.ui.Tests;

import static ru.iteco.fmhandroid.ui.HelperUtil.randomCategory;
import static ru.iteco.fmhandroid.ui.HelperUtil.getCurrentDate;
import static ru.iteco.fmhandroid.ui.HelperUtil.getCurrentTime;

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
    @Story("21-ТК: Cоздание новости с валидными данными")
    public void shouldBeANewsArticleSuccessfullyCreatedWithValidData() {
        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();
        String title = "Новость1";
        String description = "Описание новости1";
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
        newsPageSteps.checkNotificationText("Заполните пустые поля", decorView);
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
        String title = "Новость900";
        String description = "Новость900";
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
        String title = "Новость099";
        String description = "Описание099";
        String newTitle = "Новость отредактирована099";
        String newDescription = "Описание отредактировано099";
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
        String title = "Новость695";
        String description = "Новость695";
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