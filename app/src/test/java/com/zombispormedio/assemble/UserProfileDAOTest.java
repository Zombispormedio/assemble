package com.zombispormedio.assemble;

import com.zombispormedio.assemble.wrappers.realm.dao.UserProfileDAO;
import com.zombispormedio.assemble.models.UserProfile;

import org.junit.Test;

import static org.junit.Assert.assertSame;

/**
 * Created by Xavier Serrano on 14/09/2016.
 */
public class UserProfileDAOTest {


    @Test
    public void test_merge_builder() throws Exception {

        UserProfile profile1=new UserProfile();
        profile1.id=1;
        profile1.username="hello. I am 1";

        UserProfileDAO profile2=new UserProfileDAO();
        profile2.id=2;
        profile2.username="hello. I am 2";

        profile2.fromModel(profile1);

        System.out.println(profile2.id);

        assertSame("Field id must be the same", profile2.id, profile1.id);

        assertSame("Field username must be the same", profile2.username, profile1.username);

    }

}
