package com.zombispormedio.assemble;
import com.zombispormedio.assemble.utils.StringUtils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Xavier Serrano on 26/07/2016.
 */
public class StringUtilsUnitTest {

    @Test
    public void join_isCorrect() throws Exception {
        assertEquals("4=4", StringUtils.join("=", new String[]{"4","4"}));
        assertEquals("1,2,3,4,5", StringUtils.join(",", new String[]{"1","2","3", "4", "5"}));

    }

    @Test
    public void capitalize_isCorrect() throws Exception {
        assertEquals("Hello", StringUtils.capitalize("hello"));
        assertEquals("Hello", StringUtils.capitalize("Hello"));
        assertEquals("Hello", StringUtils.capitalize("heLlo"));
        assertEquals("Hello", StringUtils.capitalize("HELLO"));
    }
}
