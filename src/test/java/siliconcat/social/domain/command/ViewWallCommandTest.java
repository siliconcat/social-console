package siliconcat.social.domain.command;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import siliconcat.social.domain.Message;
import siliconcat.social.domain.User;
import siliconcat.social.domain.Users;
import siliconcat.social.storage.Repository;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ViewWallCommandTest {

    @Mock
    private User user;

    @Mock
    private Repository repository;

    @Test
    public void executeReturnsTheMessagesAsAStringWithCarriageReturns() throws Exception {
        when(user.wall()).thenReturn(Stream.of(
                new Message(Users.createUser(repository, "Peter"), "Newer message", DateTime.now()),
                new Message(Users.createUser(repository, "Wendy"), "Older message", DateTime.now().minusSeconds(1))
        ));

        Command command = new ViewWallCommand(user);

        assertEquals("Peter - Newer message (0 seconds ago)\nWendy - Older message (1 second ago)", command.execute().get());
    }

    @Test
    public void executeReturnsEmptyOptionalIfNoMessagesAreFound() throws Exception {
        when(user.wall()).thenReturn(Stream.empty());

        Command command = new ViewWallCommand(user);

        assertFalse(command.execute().isPresent());
    }
}