package com.zombispormedio.assemble;
import com.zombispormedio.assemble.network.HTTPMethod;
import com.zombispormedio.assemble.network.Request;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by Xavier Serrano on 26/07/2016.
 */
public class RequestUnitTest {

    @Test
    public void build_isCorrect() throws Exception {


        Request req= new Request.Builder()
                .method(HTTPMethod.POST)
                .url("https://hello.com/:id/:work")
                .params("s", 5)
                .headers("auth", "5")
                .build();

        assertEquals(HTTPMethod.POST, req.getMethod());

        assertEquals("https://hello.com?s=5", req.getUrl());

        assertEquals("5", req.getHeader("auth"));

        assertEquals("https://hello.com/45?s=5", new Request.Builder().method(HTTPMethod.GET).url("https://hello.com/:id/:work").params("id", 45).params("s", 5).build().getUrl());




    }
}
