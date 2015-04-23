package siliconcat.social;

import siliconcat.social.domain.command.Commands;
import siliconcat.social.storage.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class CommandLineClient {

    private static final String LINE_START = "> ";

    private Repository repository;

    public CommandLineClient() {
        this.repository = new Repository();
    }

    public Optional<String> execute(String command) {
        return Commands.parse(repository, command).execute();
    }

    public void start() throws IOException {
        System.out.print(LINE_START);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String command;

        while ((command = in.readLine()) != null) {
            Optional<String> responseOpt = execute(command);
            responseOpt.ifPresent(System.out::println);

            System.out.print(LINE_START);
        }
    }
}
