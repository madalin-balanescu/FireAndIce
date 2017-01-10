package com.bma.codingchallange.manager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bma.codingchallange.R;
import com.bma.codingchallange.application.App;
import com.bma.codingchallange.ui.activities.MainActivity;
import com.bma.codingchallange.ui.fragments.BooksFragment;
import com.bma.codingchallange.ui.fragments.CharactersFragment;

import javax.inject.Inject;

/**
 * Created by balanescumadalin on 10/01/2017.
 */

public class LPFragmentManager {


    FragmentActivity mainActivity;

    public static final String BOOKS_FRAGMENT = "booksFragment";
    public static final String CHARACTERS_FRAGMENT = "charactersFragment";

    public LPFragmentManager() {
        App.getAppComponent().inject(this);
    }

    public void setActivity(FragmentActivity mainActivity) {
        if (mainActivity != null) {
            this.mainActivity = mainActivity;
        }

    }

    public Fragment getCurrentFragment() {
        if (mainActivity != null) {
            return mainActivity.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        } else {
            return null;
        }
    }


    private void addFragment(Fragment fragment, int containerResID, String tag, boolean addToBackStack, int enter, int exit, int popEnter, int popExit) {
        if (mainActivity != null) {
            FragmentManager fm = mainActivity.getSupportFragmentManager();

            final FragmentTransaction transaction = fm.beginTransaction();

            if (enter > 0 && exit > 0 && popEnter > 0 && popExit > 0) {
                transaction.setCustomAnimations(enter, exit, popEnter, popExit);
            } else if (enter > 0) {
                transaction.setCustomAnimations(enter, exit);
            }

            transaction.add(containerResID, fragment, tag);
            if (addToBackStack) {
                transaction.addToBackStack(null);
            } else {
                clearBackStack();
            }
            transaction.commitAllowingStateLoss();


        }
    }


    private void changeFragment(Fragment fragment, int containerResID, String tag, boolean addToBackStack) {
        if (mainActivity != null) {
            FragmentManager fm = mainActivity.getSupportFragmentManager();
            final FragmentTransaction transaction = fm.beginTransaction();

            transaction.add(containerResID, fragment, tag);
            if (addToBackStack) {
                transaction.addToBackStack(null);
            } else {
                clearBackStack();
            }
            transaction.commitAllowingStateLoss();


        }
    }

    public void displayBooks() {
        changeFragment(new BooksFragment(), R.id.fragment_container, BOOKS_FRAGMENT, false);
    }

    public void displayCharacters(String characterId) {
        addFragment(CharactersFragment.newInstance(characterId), R.id.fragment_container, CHARACTERS_FRAGMENT,
                true, R.anim.translate_left_in, R.anim.translate_left_out,
                R.anim.translate_right_in, R.anim.translate_right_out);
    }

    public void clearBackStack() {
        FragmentManager manager = mainActivity.getSupportFragmentManager();
        for (int i = 0; i < manager.getBackStackEntryCount(); i++) {
            manager.popBackStack();
        }
    }


}
