package com.zombispormedio.assemble.net;

import java.io.File;

/**
 * Created by Xavier Serrano on 01/08/2016.
 */
public class FileBody {

    private final File file;

    private final String mediaType;

    private final String key;

    private final String filename;

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
