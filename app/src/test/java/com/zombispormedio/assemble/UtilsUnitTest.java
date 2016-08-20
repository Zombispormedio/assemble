package com.zombispormedio.assemble;
import com.zombispormedio.assemble.utils.Utils;

import org.junit.Test;
import org.objenesis.instantiator.basic.ObjectInputStreamInstantiator;

import static org.junit.Assert.*;
/**
 * Created by Xavier Serrano on 20/08/2016.
 */
public class UtilsUnitTest {


    @Test
    public void test_presence_of_object() throws Exception {
        assertTrue("Object must exist",Utils.presenceOf(new Object()));
        assertFalse("Object must be null", Utils.presenceOf(null));
    }

    @Test
    public void test_presence_of_string() throws Exception {
        assertTrue("Object mustn't be empty or null",Utils.presenceOf("Hello World!"));
        assertFalse("Object must be empty", Utils.presenceOf(""));
    }
}
