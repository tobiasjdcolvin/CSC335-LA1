package test.model;

import org.junit.Test;
import org.junit.Assert;
import src.model.UserModel;
import src.store.MusicStore;

public class UserModelTest {
    MusicStore myStore = new MusicStore();
    UserModel myUserModel = new UserModel(myStore);

    @Test
    // NOT A REAL TEST CASE, I AM JUST TESTING RANDOM THINGS HERE WITH PRINTING TO CONSOLE
    public void placeholderTest() {
        System.out.println(myUserModel.getNumUsers());
        myUserModel.addUser("chris", "mynameischris");
        System.out.println(myUserModel.getNumUsers());
        myUserModel.addUser("jonathan", "passwd");
        System.out.println(myUserModel.getNumUsers());

        System.out.println(myUserModel.getCurrUserIsNull());
        System.out.println(myUserModel.login("chri", "mynameischris"));
        System.out.println(myUserModel.getCurrUserIsNull());
        System.out.println(myUserModel.login("chris", "mynameischris"));
        System.out.println(myUserModel.getCurrUserIsNull());
    }
}
