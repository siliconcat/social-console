package siliconcat.social.storage;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import siliconcat.social.domain.Message;
import siliconcat.social.domain.User;

import java.util.stream.Stream;

public class Repository {

    private Multimap<String, Message> messagesByUser;
    private Multimap<String, User> subscriptionsByUser;

    public Repository() {
        this.messagesByUser = HashMultimap.create();
        this.subscriptionsByUser = HashMultimap.create();
    }

    public void saveMessage(Message message) {
        messagesByUser.put(message.getUser().getName(), message);
    }

    public Stream<Message> findMessages(User user) {
        return messagesByUser.get(user.getName()).stream();
    }

    public void saveSubscription(User user, User subscription) {
        subscriptionsByUser.put(user.getName(), subscription);
    }

    public Stream<User> findSubscriptions(User user) {
        return subscriptionsByUser.get(user.getName()).stream();
    }
}
