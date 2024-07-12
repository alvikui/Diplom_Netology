package ru.iteco.fmhandroid.ui.Elements;

import androidx.test.espresso.ViewInteraction;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import org.hamcrest.Matcher;

import static ru.iteco.fmhandroid.ui.HelperUtil.waitUntilElement;
import static ru.iteco.fmhandroid.ui.HelperUtil.withIndex;

import ru.iteco.fmhandroid.R;

public class HomePagePage {
    public ViewInteraction profileButton = onView(withId(R.id.authorization_image_button));
    public ViewInteraction exitButton = onView(withText("Выйти"));
    public ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    public ViewInteraction newsButtonInMenu = onView(withText("Новости"));
    public ViewInteraction aboutApplicationButtonInMenu = onView(withText("О приложении"));
    public ViewInteraction topicQuotesButton = onView(withId(R.id.our_mission_image_button));
    public ViewInteraction newsHeadline = onView(withText("Новости"));
    public ViewInteraction allNewsButton = onView(withId(R.id.all_news_text_view));
    public ViewInteraction logo = onView(withId(R.id.trademark_image_view));
    public ViewInteraction controlPanelButton = onView(withId(R.id.edit_news_material_button));
    public ViewInteraction listNews = onView(withId(R.id.expand_material_button));
    public ViewInteraction newsItemCardView = onView(withIndex(withId(R.id.news_item_material_card_view), 1));

    public static Matcher<View> listNewInBlock() {
        waitUntilElement(R.id.container_list_news_include_on_fragment_main);
        return withIndex(withId(R.id.container_list_news_include_on_fragment_main), 0);
    }
}