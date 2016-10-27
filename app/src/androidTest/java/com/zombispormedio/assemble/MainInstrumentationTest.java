package com.zombispormedio.assemble;



import org.junit.Test;
import org.junit.runner.RunWith;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainInstrumentationTest {

    @Test
    public void useAppContext() throws Exception{
        Context appContext= InstrumentationRegistry.getTargetContext();

        assertEquals("com.zombispormedio.assemble", appContext.getPackageName());
    }

}