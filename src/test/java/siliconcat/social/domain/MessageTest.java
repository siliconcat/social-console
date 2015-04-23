package siliconcat.social.domain;

import org.junit.Test;
import org.mockito.Mock;
import siliconcat.social.storage.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class MessageTest {

    @Mock
    private Repository repository;

    @Mock
    private User user;

    @Test
    public void messagesGetATimeStampOnCreation() throws Exception {
        Message msg = new Message(user, "Winter is coming");
        assertNotNull(msg.getTimestamp());
    }

    @Test
    public void messagesAreSortedByTimeNewestFirst() throws Exception {
        Message oldest = new Message(user, "I have seen many things");
        Thread.sleep(1);
        Message middle = new Message(user, "Time for crisis");
        Thread.sleep(1);
        Message newest = new Message(user, "I can barely talk!");

        List<Message> messages = Arrays.asList(middle, newest, oldest);
        Collections.sort(messages);

        assertSame(newest, messages.get(0));
        assertSame(middle, messages.get(1));
        assertSame(oldest, messages.get(2));
    }

    @Test
    public void messagesCanBeFormatted() throws Exception {
        Message msg = new Message(Users.createUser(repository, "May"), "I like the Life of Brian");

        String formatted = msg.format(m -> m.getContent() + " " + m.getUser().getName());

        assertEquals("I like the Life of Brian May", formatted);
    }

}