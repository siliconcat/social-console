package siliconcat.social.domain.command;

import java.util.Optional;

public interface Command {

    Optional<String> execute();
}
