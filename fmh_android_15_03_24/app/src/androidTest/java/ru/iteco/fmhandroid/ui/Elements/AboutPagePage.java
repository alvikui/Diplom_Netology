package ru.iteco.fmhandroid.ui.Elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutPagePage {
    public ViewInteraction privacyPolicyValue = onView(withId(R.id.about_privacy_policy_value_text_view));
    public ViewInteraction termsOfUseValue = onView(withId(R.id.about_terms_of_use_value_text_view));
}