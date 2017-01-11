package com.bma.codingchallange.presenter;

import com.bma.codingchallange.application.App;
import com.bma.codingchallange.database.RealmHelper;
import com.bma.codingchallange.log.LPLog;
import com.bma.codingchallange.manager.LPFragmentManager;
import com.bma.codingchallange.model.Book;
import com.bma.codingchallange.model.Characters;
import com.bma.codingchallange.network.LPNetworkManager;
import com.bma.codingchallange.ui.fragments.BooksFragment;
import com.bma.codingchallange.ui.fragments.CharactersFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by balanescumadalin on 11/01/2017.
 */

public class MainPresenter {

    @Inject
    public LPNetworkManager networkManager;
    @Inject
    public LPFragmentManager fragmentManager;
    @Inject
    public RealmHelper realmHelper;

    public MainPresenter() {
        App.getAppComponent().inject(this);
    }


    public void getBooks() {
        networkManager.getBooks().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Book>>() {
                    @Override
                    public void onCompleted() {
                        LPLog.i(LPLog.LPLogTAG, "Complete Books");

                        if (fragmentManager.getCurrentFragment() != null && fragmentManager.getCurrentFragment() instanceof BooksFragment) {
                            ((BooksFragment) fragmentManager.getCurrentFragment()).setAdapter(realmHelper.getBooks());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LPLog.e(LPLog.LPLogTAG, "Error on the Books");
                    }

                    @Override
                    public void onNext(List<Book> books) {
                        if (books != null && books.size() > 0) {
                            realmHelper.saveList(books);
                        }
                    }
                });
    }

    @SuppressWarnings("unchecked")
    public void getTitleForCharacters(List<Observable<Characters>> apiCalls) {
        Observable.zip(
                apiCalls, args -> {

                    List<Characters> aux = new ArrayList<Characters>();

                    if (args != null && args.length > 0) {

                        for (Object item : args) {
                            if (item != null && item instanceof Characters) {
                                aux.add((Characters) item);
                            }
                        }
                    }

                    return aux;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Characters>>() {
                    @Override
                    public void onCompleted() {
                        if (fragmentManager.getCurrentFragment() != null && fragmentManager.getCurrentFragment() instanceof CharactersFragment) {
                            ((CharactersFragment) fragmentManager.getCurrentFragment()).notifyAdapter();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<Characters> listOfCharacters) {
                        if (fragmentManager.getCurrentFragment() != null && fragmentManager.getCurrentFragment() instanceof CharactersFragment) {

                            if (fragmentManager.getCurrentFragment() != null && fragmentManager.getCurrentFragment() instanceof CharactersFragment) {
                                if (listOfCharacters != null && listOfCharacters.size() > 0) {
                                    ((CharactersFragment) fragmentManager.getCurrentFragment()).updateTheTitles(listOfCharacters);
                                }
                            }

                        }
                    }
                });

    }


}
