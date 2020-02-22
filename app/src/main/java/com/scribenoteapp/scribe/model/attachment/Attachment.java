package com.scribenoteapp.scribe.model.attachment;
import java.io.Serializable;

/**
 * Created by ALLDe on 15/01/2020.
 */

public abstract class Attachment implements Serializable {
    private String absolutePath;

    Attachment(String absolutePath)
    {
        this.absolutePath = absolutePath;
    }

    public String getAbsolutePath() {
        return this.absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
}
