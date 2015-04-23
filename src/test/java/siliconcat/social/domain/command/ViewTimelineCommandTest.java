package siliconcat.social.domain.command;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import siliconcat.social.domain.Message;
import siliconcat.social.domain.User;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ViewTimelineCommandTest {

    @Mock
    private User user;

    @Test
    public void executeReturnsTheMessagesAsAStringWithCarriageReturns() throws Exception {
        when(user.timeline()).thenReturn(Stream.of(
                new Message(user, "Newer message", DateTime.now()),
                new Message(user, "Older message", DateTime.now().minusSeconds(1))
        ));

        Command command = new ViewTimelineCommand(user);

        assertEquals("Newer message (0 seconds ago)\nOlder message (1 second ago)", command.execute().get());
    }

    @Test
    public void executeReturnsEmptyOptionalIfNoMessagesAreFound() throws Exception {
        when(user.timeline()).thenReturn(Stream.empty());

        Command command = new ViewTimelineCommand(user);

        assertFalse(command.execute().isPresent());
    }
}