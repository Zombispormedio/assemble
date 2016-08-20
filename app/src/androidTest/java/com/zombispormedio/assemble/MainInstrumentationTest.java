package com.zombispormedio.assemble;


import com.zombispormedio.assemble.activities.MainActivity;


import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class MainInstrumentationTest extends ActivityTestRule<MainActivity> {
    public MainInstrumentationTest() {
        super(MainActivity.class);
    }
}