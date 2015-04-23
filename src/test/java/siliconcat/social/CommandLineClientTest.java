package siliconcat.social;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class CommandLineClientTest {

    private CommandLineClient commandLineClient;
    private PrintStream oldOut;

    @Before
    public void setUp() throws Exception {
        commandLineClient = new CommandLineClient();
        oldOut = System.out;
    }

    @After
    public void tearDown() throws Exception {
        commandLineClient = null;
        System.setOut(oldOut);
    }

    @Test
    public void executesCommands() throws Exception {
        Optional<String> result = commandLineClient.execute("Alice");
        assertFalse(result.isPresent());
    }

    @Test
    public void startShouldReadFromTheUserInput() throws Exception {
        OutputStream out = new ByteArrayOutputStream();

        System.setIn(new ByteArrayInputStream("Alice -> Hello\nAlice".getBytes()));
        System.setOut(new PrintStream(out));
        commandLineClient.start();

        System.out.flush();

        assertThat(out.toString(), containsString("Hello"));
    }
}