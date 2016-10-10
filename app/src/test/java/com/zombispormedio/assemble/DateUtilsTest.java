package com.zombispormedio.assemble;

import com.zombispormedio.assemble.utils.DateUtils;



import org.junit.Test;


import static org.junit.Assert.*;
/**
 * Created by Xavier Serrano on 10/10/2016.
 */

public class DateUtilsTest {

    @Test
    public void format_Test() throws Exception {
        String d=DateUtils.format("HH:mm", "2016-10-10T08:53:10.688Z");
        assertEquals("10:53", d);
    }
}
