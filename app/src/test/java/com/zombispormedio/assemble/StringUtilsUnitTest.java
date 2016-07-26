package com.zombispormedio.assemble;
import com.zombispormedio.assemble.utils.StringUtils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Master on 26/07/2016.
 */
public class StringUtilsUnitTest {

    @Test
    public void join_isCorrect() throws Exception {
        assertEquals("4=4", StringUtils.join("=", new String[]{"4","4"}));
    }
}
