package siliconcat.social.domain;

import siliconcat.social.storage.Repository;

public final class Users {

    private Users() {}

    public static User createUser(Repository repository, String name) {
        return new User(repository, name);
    }
}
