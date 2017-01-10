package com.bma.codingchallange.network;

import com.bma.codingchallange.application.App;
import com.bma.codingchallange.model.Book;
import com.bma.codingchallange.model.Characters;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by balanescumadalin on 10/01/2017.
 */

public class LPNetworkManager {

    @Inject
    LPServices apiService;


    public LPNetworkManager() {
        App.getAppComponent().inject(this);
    }

    public Observable<List<Book>> getBooks() {
        return apiService.getBooks();
    }

    public Observable<Characters> getCharacters(int characterId) {
        return apiService.getCharacters(characterId);
    }


}
