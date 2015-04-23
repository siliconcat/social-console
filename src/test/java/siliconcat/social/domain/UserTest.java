package siliconcat.social.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import siliconcat.social.storage.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

public class UserTest {

    private User hansel;
    private User gretel;
    private Repository repository;


    @Before
    public void setUp() throws Exception {
        repository = new Repository();

        hansel = Users.createUser(repository, "Hansel");
        gretel = Users.createUser(repository, "Gretel");

    }

    @After
    public void tearDown() throws Exception {
        hansel = null;
        gretel = null;
        repository = null;
    }

    @Test
    public void publishStoresAMessageInTheRepository() throws Exception {
        hansel.publish("Where are my breadcrumbs?");

        assertEquals("Where are my breadcrumbs?", repository.findMessages(hansel).findFirst().get().getContent());
    }

    @Test
    public void subscribeToStoresTheSubscriptionInTheRepository() throws Exception {
        hansel.subscribeTo(gretel);

        assertEquals(gretel, repository.findSubscriptions(hansel).findFirst().get());
    }

    @Test
    public void timelineGetsTheSortedMessagesForThisUser() throws Exception {
        hansel.publish("Hansel oldest message");
        gretel.publish("A random message");
        hansel.publish("Hansel newest message");

        List<String> messages = hansel.timeline().map(Message::getContent).collect(Collectors.toList());
        assertEquals(2, messages.size());
        assertThat(messages, hasItems("Hansel newest message", "Hansel oldest message"));
    }

    @Test
    public void wallGetsTheSortedMessagesForThisUserAndHerSubscriptions() throws Exception {
        hansel.publish("Hansel oldest message");
        gretel.publish("A random message");
        hansel.publish("Hansel newest message");
        gretel.subscribeTo(hansel);

        List<String> messages = gretel.wall().map(Message::getContent).collect(Collectors.toList());
        assertEquals(3, messages.size());
        assertThat(messages, hasItems("Hansel newest message", "A random message", "Hansel oldest message"));
    }
}