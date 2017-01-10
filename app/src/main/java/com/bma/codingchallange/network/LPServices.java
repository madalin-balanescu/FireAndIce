package com.bma.codingchallange.network;

import com.bma.codingchallange.model.Book;
import com.bma.codingchallange.model.Characters;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by balanescumadalin on 10/01/2017.
 */

public interface LPServices {

    @GET(ServerConstants.GET_BOOKS)
    Observable<List<Book>> getBooks();

    @GET(ServerConstants.GET_CHARACTERS)
    Observable<Characters> getCharacters(@Path("characterId") int characterId);
}
