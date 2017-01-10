package com.bma.codingchallange.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.realm.RealmObject;

/**
 * Created by balanescumadalin on 10/01/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class TitlesObject extends RealmObject {

    private String title;

    public TitlesObject() {
    }

    public TitlesObject(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
