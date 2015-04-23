package siliconcat.social.storage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import siliconcat.social.domain.Message;
import siliconcat.social.domain.User;
import siliconcat.social.domain.Users;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class RepositoryTest {

    private Repository repository;

    @Before
    public void setUp() throws Exception {
        repository = new Repository();
    }

    @After
    public void tearDown() throws Exception {
        repository = null;
    }

    @Test
    public void messagesAreStoredByUserName() throws Exception {
        User maria = Users.createUser(repository, "Maria");
        User kiriko = Users.createUser(repository, "Kiriko");

        Message msg1 = new Message(maria, "I am from Spain");
        Message msg2 = new Message(kiriko, "I am from Japan");
        Message msg3 = new Message(maria, "It is quite sunny here");

        repository.saveMessage(msg1);
        repository.saveMessage(msg2);
        repository.saveMessage(msg3);

        List<String> messagesFromMaria = repository.findMessages(maria).map(Message::getContent).collect(Collectors.toList());
        assertEquals(2, messagesFromMaria.size());
        assertThat(messagesFromMaria, hasItems("I am from Spain", "It is quite sunny here"));
    }

    @Test
    public void subscriptionsAreStoredByUserName() throws Exception {
        User maria = Users.createUser(repository, "Maria");
        User kiriko = Users.createUser(repository, "Kiriko");

        repository.saveSubscription(maria, kiriko);

        List<User> mariaSubs = repository.findSubscriptions(maria).collect(Collectors.toList());
        assertEquals(1, mariaSubs.size());
        assertEquals("Kiriko", mariaSubs.get(0).getName());

        assertEquals(0, repository.findSubscriptions(kiriko).count());
    }

}