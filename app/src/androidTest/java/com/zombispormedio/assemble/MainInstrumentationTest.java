package com.zombispormedio.assemble;


import com.zombispormedio.assemble.activities.MainActivity;

import android.app.Application;
import android.test.ActivityInstrumentationTestCase2;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class MainInstrumentationTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainInstrumentationTest() {
        super(MainActivity.class);
    }
}