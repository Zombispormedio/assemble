package com.zombispormedio.assemble.net;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Xavier Serrano on 26/07/2016.
 */
public class HTTPMethod {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({GET, POST, PUT, PATCH, DELETE})

    public @interface Def{}

    public static final String GET = "GET";

    public static final String POST = "POST";

    public static final String PUT = "PUT";

    public static final String PATCH = "PATCH";

    public static final String DELETE = "DELETE";
}
