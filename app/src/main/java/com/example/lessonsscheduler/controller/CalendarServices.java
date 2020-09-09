package com.example.lessonsscheduler.controller;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;

import androidx.annotation.RequiresApi;

import com.example.lessonsscheduler.model.Lesson;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

/**
 * A class to handle services and functions relating to time and the calendar
 *
 * @author Joshua Drumm
 * @version 1.0
 * @since   2020-4-10
 */
public class CalendarServices
{
    /*
     * Gets the user's next available course to check-in to
     * @param courses The list of courses the user is registered in
     * @param user The user whose courses to check
     * @return The user's next course
     */
    /*
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Course getNextCourse(List<Course> courses, LoggedInUser user)
    {
        long currentTime = getCurrentTime(ZoneId.of(user.getSemester().getTimeZoneID()));
        Course soonestCourse = courses.get(0);
        long soonestCourseTime = getNextTime(courses.get(0), user.getSemester().getTimeZoneID());
        for(int i = 1; i < courses.size(); i++)
        {
            Course course = courses.get(i);
            long courseTime = getNextTime(courses.get(i), user.getSemester().getTimeZoneID());
            if(courseTime < soonestCourseTime)
            {
                soonestCourseTime = courseTime;
                soonestCourse = course;
            }
        }
        return soonestCourse;
    }
    */

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean addCalendarEvent(Lesson lesson, int alertMinutes, ContentResolver cr) {
        Calendar calendarEvent = Calendar.getInstance();
        Uri EVENTS_URI = Uri.parse(CalendarServices.getCalendarUriBase(true) + "events");

        TimeZone timeZone = TimeZone.getDefault();

        long startTime = getNextTime(lesson);
        ContentValues values = new ContentValues();

        Uri event = null;
        values.put(CalendarContract.Events.CALENDAR_ID, 1);
        values.put(CalendarContract.Events._ID, lesson.getHash());
        values.put(CalendarContract.Events.TITLE, "Lesson with " + lesson.getStudent().getName());
        values.put(CalendarContract.EXTRA_EVENT_ALL_DAY, false);
        values.put(CalendarContract.Events.DTSTART, startTime);
        values.put(CalendarContract.Events.DTEND, startTime + 60 * 60 * 1000);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone.toString());
        //values.put(CalendarContract.EXTRA_EVENT_BEGIN_TIME,calendarEvent.getTimeInMillis()); // Only date part is considered when ALL_DAY is true; Same as DTSTART
        //.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,calendarEvent.getTimeInMillis() + 60 * 60 * 1000) // Only date part is considered when ALL_DAY is true
        //.putExtra(CalendarContract.Events.EVENT_LOCATION, "Location")
        //.putExtra(CalendarContract.Events.DESCRIPTION, "DESCRIPTION") // Description
        //.putExtra(Intent.EXTRA_EMAIL, currentUser.get)
        //values.put(CalendarContract.Events.EXDATE, currentUser.getSemester().getOffDaysFormatted());
        values.put(CalendarContract.Events.RRULE, "FREQ=WEEKLY;BYDAY=" + formatDays(lesson.getDays())); // Recurrence rule
        values.put(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
        values.put(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_FREE);
        //Log.i("VALUES", values.toString());
        event = cr.insert(EVENTS_URI, values);

        if(event != null) {
            Uri REMINDERS_URI = Uri.parse(CalendarServices.getCalendarUriBase(true) + "reminders");
            values = new ContentValues();
            values.put(CalendarContract.Reminders.EVENT_ID, Long.parseLong(event.getLastPathSegment()));
            values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
            values.put(CalendarContract.Reminders.MINUTES, alertMinutes);
            cr.insert(REMINDERS_URI, values);
            return true;
        }

        return false;
    }

    /**
     * Gets the next day and time happening for a course in a specific timezone
     * @param lesson The lesson to get the next day for
     * @return The time in milliseconds since the Unix epoch that the day begins on
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static long getNextTime(Lesson lesson) {
        ArrayList<DayOfWeek> days = lesson.getDays();
        long soonestDay = getTimeOfNextDayCode(days.get(0), lesson.getHour(), lesson.getMinute());
        for (int i = 1; i < days.size(); i++) {
            long nextDay = getTimeOfNextDayCode(days.get(i), lesson.getHour(), lesson.getMinute());
            if (nextDay < soonestDay)
                soonestDay = nextDay;
        }
        return soonestDay + (lesson.getHour() * 60 + lesson.getMinute()) * 60000;
    }

    /**
     * Gets the next time of a course happening on a specific day in the semester with a given time
     * @param day The day that the course happens next on
     * @param hour The hour the class starts
     * @param minute The minute the class starts
     * @return The Unix Epoch time in milliseconds for the time of the start of the day
     *         for the course's next occurrence on the specified day in the semester
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static long getTimeOfNextDayCode(DayOfWeek day, int hour, int minute)
    {
        ZoneId zone = ZoneId.systemDefault();
        long time = ZonedDateTime.now(zone).toLocalDate().with(TemporalAdjusters.nextOrSame(day)).atStartOfDay().toInstant(zone.getRules().getOffset(LocalDateTime.now())).toEpochMilli();
        int hourNow = ZonedDateTime.now(zone).getHour();
        int minuteNow = ZonedDateTime.now(zone).getHour();
        DayOfWeek currentDay = ZonedDateTime.now(zone).getDayOfWeek();
        if(day == currentDay && (hourNow > hour || (hourNow == hour && minuteNow > minute)))
            time = ZonedDateTime.now(zone).toLocalDate().with(TemporalAdjusters.next(day)).atStartOfDay().toInstant(zone.getRules().getOffset(LocalDateTime.now())).toEpochMilli();
        return time;
    }

    /**
     * Gets the current time
     * @param zone The timezone to get the time from. This is usually the user's timezone
     * @return The Unix Epoch time in milliseconds for the specified timezone
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static long getCurrentTime(ZoneId zone)
    {
        return ZonedDateTime.now(zone).toInstant().getEpochSecond() * 1000;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public String formatDays(ArrayList<DayOfWeek> days)
    {
        if(days.size() == 0)
            return "";
        String formattedDays = getDayOfWeek(days.get(0));
        for(int i = 0; i < days.size(); i++)
            formattedDays += ',' + getDayOfWeek(days.get(i));
        return formattedDays;
    }

    /**
     * Gets the day of the week based on the formatted day code
     * DayOfWeek Enum -> MO,TU,WE,TH,FR,SA,SU
     * @param day The day of week enum to convert to a string
     * @return The day formatted for CalendarServices
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getDayOfWeek(DayOfWeek day)
    {
        switch(day)
        {
            case MONDAY:
                return "MO";
            case TUESDAY:
                return "TU";
            case WEDNESDAY:
                return "WE";
            case THURSDAY:
                return "TH";
            case FRIDAY:
                return "FR";
            case SATURDAY:
                return "SA";
            case SUNDAY:
                return "SU";
            default:
                return "";
        }
    }

    /**
     * Gets the Calendar URI base
     * @param eventUri If true, the events URI will be used instead of the calendar URI
     * @return The base URI needed for accessing calendar events
     */
    public static String getCalendarUriBase(boolean eventUri) {
        Uri calendarURI = null;
        try {
            if (android.os.Build.VERSION.SDK_INT <= 7) {
                calendarURI = (eventUri) ? Uri.parse("content://calendar/") :
                        Uri.parse("content://calendar/calendars");
            } else {
                calendarURI = (eventUri) ? Uri.parse("content://com.android.calendar/") : Uri
                        .parse("content://com.android.calendar/calendars");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendarURI.toString();
    }
}
