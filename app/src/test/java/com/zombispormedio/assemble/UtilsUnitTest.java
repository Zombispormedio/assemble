package com.zombispormedio.assemble;
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
        profile1.id=1;
        profile1.username="hello";

        UserProfileDAO profile2=new UserProfileDAO();

        new Utils.MergeBuilder<UserProfile, UserProfileDAO>()
                .emite(profile1)
                .receive(profile2)
                .merge();


        assertSame("Field id must be the same", profile1.id, profile2.id);

        assertSame("Field username must be the same", profile1.username, profile2.username);

    }


}
