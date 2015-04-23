package siliconcat.social.domain.command;

import siliconcat.social.domain.User;
import siliconcat.social.format.MessageFormat;

import java.util.Optional;

public class ViewWallCommand implements Command {

    private final User user;

    public ViewWallCommand(User user) {
        this.user = user;
    }

    @Override
    public Optional<String> execute() {
        return user.wall()
                .map(m -> m.format(MessageFormat.wall()))
                .reduce((a,b) -> a + "\n" + b);
    }

    public User getUser() {
        return user;
    }
}
