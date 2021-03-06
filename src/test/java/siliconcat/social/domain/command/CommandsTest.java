package siliconcat.social.domain.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import siliconcat.social.storage.Repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class CommandsTest {

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
    public void createAPublishCommand() throws Exception {
        String s = "Mike -> Hello there";
        Command command = Commands.parse(repository, s);

        assertThat(command, instanceOf(PublishCommand.class));

        PublishCommand c = (PublishCommand) command;
        assertThat(c.getUser().getName(), is(equalTo("Mike")));
        assertThat(c.getContent(), is(equalTo("Hello there")));
    }

    @Test
    public void createASubscribeCommand() throws Exception {
        String s = "Adam follows Eva";
        Command command = Commands.parse(repository, s);

        assertThat(command, instanceOf(SubscribeCommand.class));

        SubscribeCommand c = (SubscribeCommand) command;
        assertThat(c.getUser().getName(), is(equalTo("Adam")));
        assertThat(c.getSubscription().getName(), is(equalTo("Eva")));
    }

    @Test
    public void createAViewWallCommand() throws Exception {
        String s = "Wendy wall";
        Command command = Commands.parse(repository, s);

        assertThat(command, instanceOf(ViewWallCommand.class));

        ViewWallCommand c = (ViewWallCommand) command;
        assertThat(c.getUser().getName(), is(equalTo("Wendy")));
    }

    @Test
    public void createAViewTimeline() throws Exception {
        String s = "Anna";
        Command command = Commands.parse(repository, s);

        assertThat(command, instanceOf(ViewTimelineCommand.class));

        ViewTimelineCommand c = (ViewTimelineCommand) command;
        assertThat(c.getUser().getName(), is(equalTo("Anna")));
    }
}