package siliconcat.social.domain.command;

import siliconcat.social.domain.User;

import java.util.Optional;

public class SubscribeCommand implements Command {

    private final User user;
    private final User subscription;

    public SubscribeCommand(User user, User subscription) {
        this.user = user;
        this.subscription = subscription;
    }

    @Override
    public Optional<String> execute() {
        user.subscribeTo(subscription);
        return Optional.empty();
    }

    public User getUser() {
        return user;
    }

    public User getSubscription() {
        return subscription;
    }
}
