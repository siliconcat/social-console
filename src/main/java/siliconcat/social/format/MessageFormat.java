package siliconcat.social.format;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class MessageFormat {

    private MessageFormat() {}

    public static MessageFormatter timeline() {
        return message -> {
            String timeAgo = friendlyDuration(message.getTimestamp());
            return String.format("%s (%s ago)", message.getContent(), timeAgo);
        };
    }

    public static MessageFormatter wall() {
        return message -> {
            return String.format("%s - %s", message.getUser().getName(), timeline().format(message));
        };
    }

    public static String friendlyDuration(DateTime since) {
        PeriodFormatter pf = PeriodFormat.getDefault();
        Period periodUntilNow = new Period(since, DateTime.now());

        if (periodUntilNow.getMinutes() > 0) {
            periodUntilNow = periodUntilNow.normalizedStandard(PeriodType.minutes());
        } else {
            periodUntilNow = periodUntilNow.normalizedStandard(PeriodType.seconds());
        }

        return pf.print(periodUntilNow);
    }

}
