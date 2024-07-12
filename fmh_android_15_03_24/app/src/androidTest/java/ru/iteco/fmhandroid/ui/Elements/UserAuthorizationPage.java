package ru.iteco.fmhandroid.ui.Elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class UserAuthorizationPage {
    public ViewInteraction headline = onView(withText("Авторизация"));
    public ViewInteraction login = onView(withHint("Логин"));
    public ViewInteraction password = onView(withHint("Пароль"));
    public ViewInteraction loginButton = onView(withId(R.id.enter_button));
}