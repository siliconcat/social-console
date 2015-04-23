package siliconcat.social.domain.command;

import siliconcat.social.domain.Users;
import siliconcat.social.storage.Repository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commands {

    private static final Pattern PUBLISH = Pattern.compile("^(.+?) -> (.+)$");
    private static final Pattern SUBSCRIBE = Pattern.compile("^(.+?) follows (.+)");
    private static final Pattern WALL = Pattern.compile("^(.+?) wall$");

    public static Command parse(Repository repository, String s) {
        Matcher publishMatcher = PUBLISH.matcher(s);
        Matcher subscribeMatcher = SUBSCRIBE.matcher(s);
        Matcher wallMatcher = WALL.matcher(s);

        if (publishMatcher.matches()) {
            String name = publishMatcher.group(1);
            String message = publishMatcher.group(2);
            return new PublishCommand(Users.createUser(repository, name), message);
        } else if (subscribeMatcher.matches()) {
            String name = subscribeMatcher.group(1);
            String subscription = subscribeMatcher.group(2);
            return new SubscribeCommand(Users.createUser(repository, name), Users.createUser(repository, subscription));
        } else if (wallMatcher.matches()) {
            String name = wallMatcher.group(1);
            return new ViewWallCommand(Users.createUser(repository, name));
        } else {
            return new ViewTimelineCommand(Users.createUser(repository, s));
        }
    }
}
