package siliconcat.social.domain.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import siliconcat.social.domain.User;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PublishCommandTest {

    @Mock
    private User user;

    @Test
    public void executeInvokesPublicationOfMessageAndReturnsEmpty() throws Exception {
        Command command = new PublishCommand(user, "I want to publish this");
        Optional<String> response = command.execute();

        assertFalse(response.isPresent());

        verify(user, times(1)).publish("I want to publish this");
    }
}