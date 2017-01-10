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
public class Book extends RealmObject {

    private String url;
    @PrimaryKey
    private String name;
    private String isbn;
    private Integer numberOfPages;
    private String publisher;
    private String country;
    private String mediaType;
    private String released;
    private RealmList<Characters> characters;

    public Book() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public RealmList<Characters> getCharacters() {
        return characters;
    }

    public void setCharacters(Object characters) {
        if (characters instanceof ArrayList) {
            RealmList<Characters> characterList = new RealmList<>();
            for (Object item : ((ArrayList)characters)) {
                if (item instanceof String && item != null) {
                    characterList.add(new Characters((String) item));
                }

            }
            this.characters = characterList;
        }
    }


}
