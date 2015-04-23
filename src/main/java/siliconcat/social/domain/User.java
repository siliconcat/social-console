package siliconcat.social.domain;

import siliconcat.social.storage.Repository;

import java.util.stream.Stream;

public class User {

    private String name;
    private Repository repository;

    public User(Repository repository, String name) {
        this.repository = repository;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Stream<Message> timeline() {
        return repository.findMessages(this).sorted();
    }

    public Stream<Message> wall() {
        return Stream
                .concat(repository.findSubscriptions(this)
                                .flatMap(repository::findMessages),
                        repository.findMessages(this))
                .sorted();
    }

    public Message createMessage(String content) {
        return new Message(this, content);
    }

    public Message publish(String content) {
        Message message = createMessage(content);
        repository.saveMessage(message);
        return message;
    }

    public void subscribeTo(User hansel) {
        repository.saveSubscription(this, hansel);
    }
}
