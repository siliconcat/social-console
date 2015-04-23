package siliconcat.social.domain.command;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import siliconcat.social.domain.User;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SubscribeCommandTest {

    @Mock
    private User user;

    @Mock
    private User subscribedTo;

    @Test
    public void executeInvokesPublicationOfMessageAndReturnsEmpty() throws Exception {
        Command command = new SubscribeCommand(user, subscribedTo);
        Optional<String> response = command.execute();

        assertFalse(response.isPresent());

        verify(user, times(1)).subscribeTo(subscribedTo);
    }
}