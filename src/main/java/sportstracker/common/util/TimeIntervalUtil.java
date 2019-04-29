package sportstracker.common.util;

import sportstracker.model.TimeInterval;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static sportstracker.common.util.TimeUtil.getClientCurrentLocalTime;

public class TimeIntervalUtil {

    /**
     * Get date by Timeinterval
     *
     * @param timeInterval time interval
     * @return date
     */
    public static Date getClientCurrentLocalDateFromDate(TimeInterval timeInterval, int timeZoneOffset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getClientCurrentLocalTime(timeZoneOffset));
        switch (timeInterval) {
            case Last7Days:
                calendar.add(Calendar.DATE, -7);
                break;
            case Last30Days:
                calendar.add(Calendar.DATE, -30);
                break;
            case CurrentYear:
                calendar.set(Calendar.DAY_OF_YEAR, 1);
                calendar.set(Calendar.MONTH, 0);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                break;
            case ThisWeek:
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                break;
            default:
                calendar.set(Calendar.YEAR, 2000);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                break;
        }

        return calendar.getTime();
    }

    public static List<Date> getDateListWithTimeInterval(TimeInterval timeInterval, int timeZoneOffset, Date registerDate) {
        List<Date> dateList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getClientCurrentLocalDateFromDate(timeInterval, timeZoneOffset));
        int numberOfDate = 0;
        switch (timeInterval) {
            case Last7Days:
                numberOfDate = 7;
                break;
            case Last30Days:
                numberOfDate = 30;

                break;
            case CurrentYear:
                numberOfDate = 365;
                break;
            case ThisWeek:
                numberOfDate = 7;
                calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
                break;
            default:
//                Calendar registerCalendar = Calendar.getInstance();
//                registerCalendar.setTime(registerDate);
//                long diffInMillies = Math.abs(calendar.getTime().getTime() - registerCalendar.getTime().getTime());
//                numberOfDate = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                calendar.setTime(registerDate);
                LocalDateTime localTimeNow = LocalDateTime.now();
                LocalDateTime register = LocalDateTime.ofInstant(registerDate.toInstant(), ZoneId.systemDefault());
                Duration duration = Duration.between(register, localTimeNow);
                numberOfDate = (int) duration.getSeconds() / 24 / 60 / 60;
                break;
        }

        for (int i = numberOfDate; i >= 0; i--) {
            calendar.add(Calendar.DATE, 1);
            if (i == 0) {
                calendar.setTime(Calendar.getInstance().getTime());
            }
            dateList.add(calendar.getTime());
        }

        return dateList;
    }

}
