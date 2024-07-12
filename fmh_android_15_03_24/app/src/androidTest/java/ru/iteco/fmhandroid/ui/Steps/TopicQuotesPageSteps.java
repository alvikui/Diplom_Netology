package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Elements.TopicQuotesPagePage;

public class TopicQuotesPageSteps {
    TopicQuotesPagePage topicQuotesPagePage = new TopicQuotesPagePage();

    public void displayingAllElementsOnQuotesPage() {
        Allure.step("Проверка отображения всех элементов на странице c тематическими цитатами");
        topicQuotesPagePage.logo.check(matches(isDisplayed()));
        topicQuotesPagePage.title.check(matches(isDisplayed()));
        topicQuotesPagePage.listOfTopicalQuotes.check(matches(isDisplayed()));
    }

    public void collapseAndExpandTopicQuote(int number) {
        Allure.step("Сворачивание и разворачивание тематической цитаты");
        topicQuotesPagePage.topicQuote.check(matches(isDisplayed()));
        topicQuotesPagePage.topicQuote.perform(actionOnItemAtPosition(number, click()));
    }

    public void descriptionOfTheQuoteAsItUnfolds(String text) {
        Allure.step("Описание тематической цитаты при разворачивании");
        onView(allOf(withId(R.id.our_mission_item_description_text_view), withText(text), isCompletelyDisplayed())).check(matches(isDisplayed()));
    }
}