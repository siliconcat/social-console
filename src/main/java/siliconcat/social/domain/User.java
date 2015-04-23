package siliconcat.social.domain;

import com.google.common.base.Objects;
import siliconcat.social.storage.Repository;

import java.util.stream.Stream;

public class User {

    private final String name;
    private final Repository repository;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equal(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
