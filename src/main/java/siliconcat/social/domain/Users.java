package siliconcat.social.domain;

import siliconcat.social.storage.Repository;

public class Users {

    public static User createUser(Repository repository, String name) {
        return new User(repository, name);
    }
}
