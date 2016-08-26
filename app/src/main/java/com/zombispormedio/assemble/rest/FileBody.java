package com.zombispormedio.assemble.rest;

import java.io.File;

/**
 * Created by Xavier Serrano on 01/08/2016.
 */
public class FileBody {

    private File file;

    private String mediaType;

    private String key;

    private String filename;

    public FileBody(File file, String mediaType, String key, String filename) {
        this.file = file;
        this.mediaType = mediaType;
        this.key = key;
        this.filename = filename;
    }

    public File getFile() {
        return file;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getKey() {
        return key;
    }

    public String getFilename() {
        return filename;
    }
}
