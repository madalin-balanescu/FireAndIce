package com.bma.codingchallange.database;

import com.bma.codingchallange.application.App;
import com.bma.codingchallange.model.Book;
import com.bma.codingchallange.model.Characters;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by balanescumadalin on 10/01/2017.
 */

public class RealmHelper {


    public RealmHelper() {
        App.getAppComponent().inject(this);
    }

    public Realm getRealmInstance() {
        return Realm.getDefaultInstance();
    }


    public void saveObject(RealmObject object) {
        getRealmInstance().beginTransaction();
        getRealmInstance().copyToRealmOrUpdate(object);
        getRealmInstance().commitTransaction();
    }


    public <T extends RealmObject> void saveList(List<T> list) {
        getRealmInstance().beginTransaction();
        getRealmInstance().copyToRealmOrUpdate(list);
        getRealmInstance().commitTransaction();
    }

    public List<Book> getBooks(){
        return getRealmInstance().where(Book.class).findAll();
    }

    public Book getBookByName(String name){
        return getRealmInstance().where(Book.class).equalTo("name", name).findFirst();
    }

    public List<Characters> getCharacters(String characterId){
        return getRealmInstance().where(Characters.class).equalTo("url", characterId).findAll();
    }


    public void release() {
        if (getRealmInstance() != null) {
            getRealmInstance().close();
        }
    }

}
