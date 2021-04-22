package dev.machine.code.mvvm_application.utils;

import androidx.annotation.IntDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
public class FragmentUtils {
    public static final int TRANSITION_POP = 0;
    public static final int TRANSITION_FADE_IN_OUT = 1;
    public static final int TRANSITION_SLIDE_LEFT_RIGHT = 2;
    public static final int TRANSITION_SLIDE_LEFT_RIGHT_WITHOUT_EXIT = 3;
    public static final int TRANSITION_NONE = 4;

    @IntDef({TRANSITION_POP, TRANSITION_FADE_IN_OUT, TRANSITION_SLIDE_LEFT_RIGHT, TRANSITION_SLIDE_LEFT_RIGHT_WITHOUT_EXIT, TRANSITION_NONE})
    @interface FragmentAnimation {
    }

    private FragmentUtils() {
    }

    public static void replaceFragment(AppCompatActivity activity, Fragment fragment, int id, boolean addToBackStack) {
        if (null == activity)
            return;
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (addToBackStack)
            transaction.addToBackStack(fragment.getClass().getCanonicalName());
        transaction.replace(id, fragment, fragment.getClass().getCanonicalName());
        transaction.commit();
    }
}
