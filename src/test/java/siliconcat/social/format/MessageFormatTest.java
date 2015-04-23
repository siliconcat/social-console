package siliconcat.social.format;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.Test;
import org.mockito.Mock;
import siliconcat.social.domain.Message;
import siliconcat.social.domain.Users;
import siliconcat.social.storage.Repository;

import static org.junit.Assert.assertEquals;

public class MessageFormatTest {

    @Mock
    private Repository repository;

    private DateTime startDate = DateTime.now();

    @Before
    public void setUp() {
        DateTimeUtils.setCurrentMillisFixed(startDate.getMillis());
    }

    @After
    public void tearDown() {
        DateTimeUtils.setCurrentMillisSystem();
    }

    @Test
    public void timelineFormatDoesNotDisplayUser() throws Exception {
        Message msg = new Message(Users.createUser(repository, "Greta"), "I like films");
        String formatted = MessageFormat.timeline().format(msg);

        assertEquals("I like films (0 seconds ago)", formatted);
    }

    @Test
    public void wallFormatDisplaysTheUser() throws Exception {
        Message msg = new Message(Users.createUser(repository, "Jimi"), "I like guitars");
        String formatted = MessageFormat.wall().format(msg);

        assertEquals("Jimi - I like guitars (0 seconds ago)", formatted);
    }

    @Test
    public void friendlyDurationDisplaysTheTimeInSecondsIfLessThanAMinute() throws Exception {
        secondsSinceStart(1);
        assertEquals("1 second", MessageFormat.friendlyDuration(startDate));

        secondsSinceStart(2);
        assertEquals("2 seconds", MessageFormat.friendlyDuration(startDate));

        secondsSinceStart(59);
        assertEquals("59 seconds", MessageFormat.friendlyDuration(startDate));
    }

    @Test
    public void friendlyDurationDisplaysTheTimeInMinutesIfOneOrMore() throws Exception {
        secondsSinceStart(60);
        assertEquals("1 minute", MessageFormat.friendlyDuration(startDate));

        secondsSinceStart(119);
        assertEquals("1 minute", MessageFormat.friendlyDuration(startDate));

        secondsSinceStart(120);
        assertEquals("2 minutes", MessageFormat.friendlyDuration(startDate));
    }

    @Test
    public void friendlyDurationDoesNotDisplayAnythingHigherThanMinutesForNow() throws Exception {
        minutesSinceStart(1234);
        assertEquals("1234 minutes", MessageFormat.friendlyDuration(startDate));
    }

    private void secondsSinceStart(int seconds) {
        DateTimeUtils.setCurrentMillisFixed(startDate.plusSeconds(seconds).getMillis());
    }

    private void minutesSinceStart(int minutes) {
        DateTimeUtils.setCurrentMillisFixed(startDate.plusMinutes(minutes).getMillis());
    }
}