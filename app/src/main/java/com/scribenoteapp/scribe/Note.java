package com.scribenoteapp.scribe;

import java.util.Date;

/**
 * Created by ALLDe on 13/01/2020.
 */

public class Note {
    private String body;
    private String title;
    private String date;

    public Note(String body, String title, String date) {
        this.body = body;
        this.title = title;
        this.date = date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }
    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }
}

