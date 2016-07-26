package com.zombispormedio.assemble;
import com.zombispormedio.assemble.rest.Request;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by Master on 26/07/2016.
 */
public class RequestUnitTest {

    @Test
    public void build_isCorrect() throws Exception {


        Request req= new Request.Builder()
                .method("POST")
                .url("https://hello.com")

                .headers("auth", "5")
                .build();



        assertEquals("POST", req.getMethod());

        assertEquals("https://hello.com", req.getUrl());

        assertEquals("5", req.getHeaders("auth"));





    }
}
