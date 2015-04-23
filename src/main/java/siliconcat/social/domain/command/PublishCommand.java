package siliconcat.social.domain.command;

import siliconcat.social.domain.User;

import java.util.Optional;

public class PublishCommand implements Command {

    private final User user;
    private final String content;

    public PublishCommand(User user, String content) {
        this.user = user;
        this.content = content;
    }

    @Override
    public Optional<String> execute() {
        user.publish(content);
        return Optional.empty();
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }
}
