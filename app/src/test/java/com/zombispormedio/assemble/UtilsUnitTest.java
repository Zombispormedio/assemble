package com.zombispormedio.assemble;
import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.dao.UserProfileDAO;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.utils.Utils;

import org.junit.Test;

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


    @Test
    public void test_merge_builder() throws Exception {

        UserProfile profile1=new UserProfile();


        UserProfileDAO profile2=new UserProfileDAO();
        profile2.id=1;
        profile2.username="hello";

        new Utils.MergeBuilder<UserProfileDAO, UserProfile >()
                .emite(profile2)
                .receive(profile1)
                .merge();
        System.out.println(profile1.id);

        assertSame("Field id must be the same", profile2.id, profile1.id);

        assertSame("Field username must be the same", profile2.username, profile1.username);

    }


}
