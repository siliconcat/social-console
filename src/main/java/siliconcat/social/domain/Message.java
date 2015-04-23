package siliconcat.social.domain;

import com.google.common.base.Objects;
import org.joda.time.DateTime;
import siliconcat.social.format.MessageFormatter;

public class Message implements Comparable<Message> {

    private final User user;
    private final String content;
    private final DateTime timestamp;

    public Message(User user, String content) {
        this(user, content, DateTime.now());
    }

    public Message(User user, String content, DateTime timestamp) {
        this.user = user;
        this.content = content;
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public String format(MessageFormatter mf) {
        return mf.format(this);
    }

    @Override
    public int compareTo(Message o) {
        return o.getTimestamp().compareTo(timestamp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equal(user, message.user) &&
                Objects.equal(content, message.content) &&
                Objects.equal(timestamp, message.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(user, content, timestamp);
    }
}
