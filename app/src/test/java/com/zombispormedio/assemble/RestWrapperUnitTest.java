package com.zombispormedio.assemble;

import com.zombispormedio.assemble.models.User;
import com.zombispormedio.assemble.rest.JSONBinder;
import com.zombispormedio.assemble.rest.responses.DefaultResponse;
import com.zombispormedio.assemble.rest.Result;
import com.zombispormedio.assemble.wrappers.moshi.JSONWrapper;
import com.zombispormedio.assemble.wrappers.okhttp.RestWrapper;

import org.junit.Test;

/**
 * Created by Master on 26/07/2016.
 */
public class RestWrapperUnitTest {

    @Test
    public void check__user_isCorrect() throws Exception {

        JSONWrapper<User> userAdapter=new JSONWrapper<User>(User.class);
        String json =new RestWrapper()
                .url("https://assemble-api.herokuapp.com/login")
                .post(userAdapter.toJSON(new User("pepe@gmail.com", "1255284")));




        DefaultResponse blackjackHand = JSONBinder.toDefaultResponse(json);
        System.out.println(blackjackHand.success);
        Result result= blackjackHand.result;
        System.out.println(result.token);
        System.out.println(blackjackHand.error==null?blackjackHand.error:blackjackHand.error.msg);

    }
}
