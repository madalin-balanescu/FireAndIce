package com.bma.codingchallange.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by balanescumadalin on 10/01/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Characters extends RealmObject {

    @PrimaryKey
    private String url;
    private RealmList<TitlesObject> titles;

    public Characters() {
    }

    public Characters(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RealmList<TitlesObject> getTitles() {
        return titles;
    }

    public void setTitles(Object titles) {
        if (titles != null && titles instanceof ArrayList) {
            RealmList<TitlesObject> titlesList = new RealmList<>();
            for (Object item : ((ArrayList) titles)) {
                if (item instanceof String && item != null) {
                    titlesList.add(new TitlesObject((String) item));
                }

            }
            this.titles = titlesList;
        }else{
            this.titles = (RealmList<TitlesObject>) titles;
        }
    }


}
