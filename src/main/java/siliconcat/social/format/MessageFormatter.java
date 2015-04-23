package siliconcat.social.format;

import siliconcat.social.domain.Message;

public interface MessageFormatter {

    String format(Message message);
}
