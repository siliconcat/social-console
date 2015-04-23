package siliconcat.social.domain.command;

import siliconcat.social.domain.User;
import siliconcat.social.format.MessageFormat;

import java.util.Optional;

public class ViewTimelineCommand implements Command {

    private final User user;

    public ViewTimelineCommand(User user) {
        this.user = user;
    }

    @Override
    public Optional<String> execute() {
        return user.timeline()
                .map(m -> m.format(MessageFormat.timeline()))
                .reduce((a, b) -> a + "\n" + b);
    }

    public User getUser() {
        return user;
    }
}
