package com.zombispormedio.assemble;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.zombispormedio.assemble.models.User;
import com.zombispormedio.assemble.rest.Response;
import com.zombispormedio.assemble.wrappers.okhttp.RestWrapper;

import org.junit.Test;

/**
 * Created by Master on 26/07/2016.
 */
public class RestWrapperUnitTest {

    @Test
    public void check__user_isCorrect() throws Exception {
        Moshi moshi = new Moshi.Builder().build();

        JsonAdapter<User> userAdapter = moshi.adapter(User.class);
        User user = new User("zombispormedio007@gmail.com", "wantedhex");

        String json =new RestWrapper()
                .url("https://assemble-api.herokuapp.com/signout")
                .header("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIzZTFkMGEyOC05MWFjLTRlOGQtYWVkNy1mYzk2ZjViZWQxOTAiLCJ0aW1lc3RhbXAiOjE0Njk1MzM3ODV9.lVDVk8wZHjRM83x9oo7G-8DF-44gsAHvzQ36ZrMpWQ0")
                .get();


        JsonAdapter<Response> jsonAdapter = moshi.adapter(Response.class);

        Response blackjackHand = jsonAdapter.fromJson(json);
        System.out.println(blackjackHand.success);
        System.out.println(blackjackHand.result==null?blackjackHand.result:blackjackHand.result.msg);
        System.out.println(blackjackHand.error==null?blackjackHand.error:blackjackHand.error.msg);

    }
}
