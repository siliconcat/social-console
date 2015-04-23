package siliconcat.social.domain;

import org.joda.time.DateTime;
import siliconcat.social.format.MessageFormatter;

public class Message implements Comparable<Message> {

    private User user;
    private String content;
    private DateTime timestamp;

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
}
