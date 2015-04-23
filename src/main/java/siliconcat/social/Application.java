package siliconcat.social;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        CommandLineClient client = new CommandLineClient();
        client.start();
    }
}
