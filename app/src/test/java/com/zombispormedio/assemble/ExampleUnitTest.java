package com.zombispormedio.assemble;

import com.zombispormedio.assemble.services.api.API;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void buildUrl_is_Correct() throws Exception {
        API.REST.Params params=new API.REST.Params();

        params.add("s", 5);

        params.add("id", "525412");

        assertEquals("htts://cer.com/525412?s=5",  params.buildURL("htts://cer.com/:id"));
    }
}