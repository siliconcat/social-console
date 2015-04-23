package siliconcat.social;

import com.google.common.base.Joiner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.format.DateTimeFormat;
import org.junit.Assert;
import siliconcat.social.CommandLineClient;

import java.util.List;

public class TimelineSteps {

    private CommandLineClient client = new CommandLineClient();
    private String lastResponse = null;

    @Given("^I execute the following commands at the specified times:$")
    public void i_execute_the_following_commands(List<CommandWithTimestamp> commands) throws Throwable {
        for (CommandWithTimestamp cwt : commands) {
            DateTimeUtils.setCurrentMillisFixed(cwt.getTimeMillis());
            executeCommand(cwt.getCommand());
        }
    }

    @Given("^I execute the following commands:$")
    public void i_execute_the_following_commands_in_order(List<String> commands) throws Throwable {
        for (String cmd : commands) {
            executeCommand(cmd);
        }
    }

    @Given("^now is the (\\d+)/(\\d+)/(\\d+) at (\\d+):(\\d+):(\\d+)$")
    public void now_is_the_at(int day, int month, int year, int hour, int minute, int second) throws Throwable {
        DateTimeUtils.setCurrentMillisFixed(new DateTime(year, month, day, hour, minute, second).getMillis());
    }

    @When("^I execute the command '(.*)'$")
    public void executeCommand(String command) throws Throwable {
        lastResponse = client.execute(command).orElse("");
    }

    @Then("^I see:$")
    public void i_see(List<String> expectedResponse) throws Throwable {
        Assert.assertEquals(Joiner.on("\n").join(expectedResponse), lastResponse);
    }


    private class CommandWithTimestamp {
        private String command;
        private String timestamp;

        public CommandWithTimestamp(String command, String timestamp) {
            this.command = command;
            this.timestamp = timestamp;
        }

        public long getTimeMillis() {
            return DateTime.parse(timestamp, DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss")).getMillis();
        }

        public String getCommand() {
            return command;
        }

        public String getTimestamp() {
            return timestamp;
        }
    }
}
