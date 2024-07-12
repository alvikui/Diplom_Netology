package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class HelperUtil {
    private static final long waitTimeout = 7000;
    private static final Random rand = new Random();
    private static final String[] CATEGORIES = {
            "Объявление", "День рождения", "Зарплата", "Профсоюз", "Праздник", "Массаж", "Благодарность", "Нужна помощь"
    };

    public static class User {
        private final String login;
        private final String password;

        public User(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

    public User getValidUser() {
        return new User("login2", "password2");
    }

    public User nonExistentLogin() {
        return new User("login21", "password2");
    }

    public User loginSpecialCharacters() {
        return new User("@#$&", "password2");
    }

    public User oneCharacterLogin() {
        return new User("a", "password2");
    }

    public User validLoginCapsLock() {
        return new User("LOGIN2", "password2");
    }

    public User nonExistentPassword() {
        return new User("login2", "password");
    }

    public User passwordSpecialCharacters() {
        return new User("login2", "@#$&");
    }

    public User oneCharacterPassword() {
        return new User("login2", "a");
    }

    public User validPasswordCapsLock() {
        return new User("login2", "PASSWORD2");
    }

    public static ViewAction waitForElement(final Matcher<View> matcher, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with attribute <" + matcher + "> during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = matcher;
                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        if (viewMatcher.matches(child)) {
                            return;
                        }
                    }
                    uiController.loopMainThreadForAtLeast(100);
                } while (System.currentTimeMillis() < endTime);
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };

    }

    public static void waitUntilElement(final int viewId) {
        onView(isRoot()).perform(waitForElement(withId(viewId), waitTimeout));
    }

    public static void waitUntilElement(final String text) {
        onView(isRoot()).perform(waitForElement(withText(text), waitTimeout));
    }

    public static String getCurrentDate() {
        return formatDate("dd.MM.yyyy");
    }

    public static String getCurrentTime() {
        return formatDate("HH:mm");
    }

    private static String formatDate(String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return dateFormat.format(new Date());
    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ").appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }

    public static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static String randomCategory() {
        return CATEGORIES[rand.nextInt(CATEGORIES.length)];
    }

    public static void elementWaiting(Matcher<View> matcher, int millis) {
        onView(isRoot()).perform(waitForElement(matcher, millis));
    }
}