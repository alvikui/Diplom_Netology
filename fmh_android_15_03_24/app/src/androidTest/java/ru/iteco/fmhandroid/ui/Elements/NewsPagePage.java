package ru.iteco.fmhandroid.ui.Elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.HelperUtil.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewsPagePage {
    public ViewInteraction newsListSwipeRefresh = onView(withId(R.id.news_list_swipe_refresh));
    public ViewInteraction allNewsCardsBlock = onView(withId(R.id.all_news_cards_block_constraint_layout));
    public ViewInteraction newsItemCardView = onView(withIndex(withId(R.id.news_item_material_card_view), 1));
    public ViewInteraction logoControlPanel = onView(withId(R.id.trademark_image_view));
    public ViewInteraction sortButton = onView(withId(R.id.sort_news_material_button));
    public ViewInteraction filterButton = onView(withId(R.id.filter_news_material_button));
    public ViewInteraction addNewsButton = onView(withId(R.id.add_news_image_view));
    public ViewInteraction headline = onView(withId(R.id.custom_app_bar_title_text_view));
    public ViewInteraction selectCategory = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public ViewInteraction newsHeadline = onView(withId(R.id.news_item_title_text_input_edit_text));
    public ViewInteraction newsDescription = onView(withId(R.id.news_item_description_text_input_edit_text));
    public ViewInteraction newsPublicationDate = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public ViewInteraction newsPublicationTime = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public ViewInteraction activitySwitch = onView(withId(R.id.switcher));
    public ViewInteraction saveButton = onView(withId(R.id.save_button));
    public ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public ViewInteraction OKButtonInNotification = onView(withText("OK"));
    public ViewInteraction CANCELButtonInNotification = onView(withText("Отмена"));

    public ViewInteraction deleteNewsButton(String newsTitle) {
        return onView(allOf(withId(R.id.delete_news_item_image_view),
                withParent(withParent(allOf(withId(R.id.news_item_material_card_view),
                        withChild(withChild(withText(newsTitle))))))));
    }

    public ViewInteraction editNewsButton(String newsTitle) {
        return onView(allOf(withId(R.id.edit_news_item_image_view),
                withParent(withParent(allOf(withId(R.id.news_item_material_card_view),
                        withChild(withChild(withText(newsTitle))))))));
    }
}