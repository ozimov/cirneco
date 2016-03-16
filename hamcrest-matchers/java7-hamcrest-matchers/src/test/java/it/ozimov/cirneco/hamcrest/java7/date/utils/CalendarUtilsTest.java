package it.ozimov.cirneco.hamcrest.java7.date.utils;

import it.ozimov.cirneco.hamcrest.BaseMatcherTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static it.ozimov.cirneco.hamcrest.java7.clazz.IsValidNoArgumentConstructor.hasNoArgumentConstructor;
import static it.ozimov.cirneco.hamcrest.java7.date.DateTestUtils.date;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CalendarUtilsTest extends BaseMatcherTest {

    @Test
    public void testConstructor() throws Exception {
        // Arrange
        MatcherAssert.assertThat(CalendarUtils.class, hasNoArgumentConstructor());
    }

    @Test
    public void testFromDateToCalendar() throws Exception {

        // Arrange
        final Date date = date(2015, 12, 21, 10, 0, 59, 999);

        // Act
        final Calendar calendar = CalendarUtils.fromDateToCalendar(date);

        // Assert
        assertThat("Expected that the Calendar has the same timestamp than the date", calendar.getTimeInMillis(),
                is(date.getTime()));
    }

    @Test
    public void testWeekDay() throws Exception {

        // Arrange
        final Date mondayDate = date(2015, 12, 21);
        final Date fridayDate = date(2015, 12, 25);
        final Date nextMondayDate = date(2015, 12, 28);

        // Act
        final int mondayWeekDay = CalendarUtils.weekDay(mondayDate);
        final int fridayWeekDay = CalendarUtils.weekDay(fridayDate);
        final int nextMondayWeekDay = CalendarUtils.weekDay(nextMondayDate);

        // Assert
        assertThat(mondayWeekDay, is(2));
        assertThat(fridayWeekDay, is(6));
        assertThat(nextMondayWeekDay, is(2));
    }

    @Test
    public void testMonth() throws Exception {

        // Arrange
        final Date februaryDate = date(2015, 2, 21);
        final Date decemberDate = date(2015, 12, 21);

        // Act
        final int februaryMonth = CalendarUtils.month(februaryDate);
        final int decemberMonth = CalendarUtils.month(decemberDate);

        // Assert
        assertThat(februaryMonth, is(2));
        assertThat(decemberMonth, is(12));
    }

    @Test
    public void testWeekOfYear() throws Exception {

        // Arrange
        final Date week1Date = date(2014, 12, 29);
        final Date week2Date = date(2015, 1, 5);
        final Date week52Date = date(2015, 12, 27);
        final Date week53Date = date(2016, 1, 3);

        // Act
        final int week1 = CalendarUtils.weekOfYear(week1Date);
        final int week2 = CalendarUtils.weekOfYear(week2Date);
        final int week52 = CalendarUtils.weekOfYear(week52Date);
        final int week53 = CalendarUtils.weekOfYear(week53Date);

        // Assert
        assertThat(week1, is(1));
        assertThat(week2, is(2));
        assertThat(week52, is(52));
        assertThat(week53, is(53));
    }

    @Test
    public void testDay() throws Exception {

        // Arrange
        final Date day1Date = date(2015, 1, 31);
        final Date day2Date = date(2000, 2, 29);

        // Act
        final int day1 = CalendarUtils.day(day1Date);
        final int day2 = CalendarUtils.day(day2Date);

        // Assert
        assertThat(day1, is(31));
        assertThat(day2, is(29));
    }

    @Test
    public void testDayOfYear() throws Exception {

        // Arrange
        final Date day1Date = date(2015, 1, 31);
        final Date day2Date = date(2000, 2, 29);
        final Date day3Date = date(2000, 3, 1);

        // Act
        final int day1 = CalendarUtils.dayOfYear(day1Date);
        final int day2 = CalendarUtils.dayOfYear(day2Date);
        final int day3 = CalendarUtils.dayOfYear(day3Date);

        // Assert
        assertThat(day1, is(31));
        assertThat(day2, is(60));
        assertThat(day3, is(61));
    }

    @Test
    public void testYear() throws Exception {

        // Arrange
        final Date year1Date = date(2015, 1, 1, 1);
        final Date year2Date = date(2000, 1, 1, 1);

        // Act
        final int year1 = CalendarUtils.year(year1Date);
        final int year2 = CalendarUtils.year(year2Date);

        // Assert
        assertThat(year1, is(2015));
        assertThat(year2, is(2000));
    }

    @Test
    public void testIsLeapYear() throws Exception {

        // Arrange
        final Date leapYearDate = date(2000, 1, 1, 1);
        final Date nonLeapYearDate = date(2001, 1, 1, 1);

        // Act
        final boolean is2000LeapYear = CalendarUtils.isLeapYear(leapYearDate);
        final boolean is2001LeapYear = CalendarUtils.isLeapYear(nonLeapYearDate);

        // Assert
        assertTrue(is2000LeapYear);
        assertFalse(is2001LeapYear);
    }

    @Test
    public void testIsAM() throws Exception {

        // Arrange
        final Date amDate = date(2000, 1, 1, 11);
        final Date pmDate = date(2000, 1, 1, 23);

        // Act
        final boolean is11AM = CalendarUtils.isAM(amDate);
        final boolean is23AM = CalendarUtils.isAM(pmDate);

        // Assert
        assertTrue(is11AM);
        assertFalse(is23AM);
    }

    @Test
    public void testIsPM() throws Exception {

        // Arrange
        final Date amDate = date(2000, 1, 1, 11);
        final Date pmDate = date(2000, 1, 1, 23);

        // Act
        final boolean is11PM = CalendarUtils.isPM(amDate);
        final boolean is23PM = CalendarUtils.isPM(pmDate);

        // Assert
        assertFalse(is11PM);
        assertTrue(is23PM);
    }

    @Test
    public void testHour12() throws Exception {

        // Arrange
        final Date amDate = date(2000, 1, 1, 11);
        final Date pmDate = date(2000, 1, 1, 23);

        // Act
        final int _11In12HourFormat = CalendarUtils.hour12(amDate);
        final int _23In12HourFormat = CalendarUtils.hour12(pmDate);

        // Assert
        assertThat(_11In12HourFormat, is(11));
        assertThat(_23In12HourFormat, is(11));
    }

    @Test
    public void testHour24() throws Exception {

        // Arrange
        final Date amDate = date(2000, 1, 1, 11);
        final Date pmDate = date(2000, 1, 1, 23);

        // Act
        final int _11In24HourFormat = CalendarUtils.hour24(amDate);
        final int _23In24HourFormat = CalendarUtils.hour24(pmDate);

        // Assert
        assertThat(_11In24HourFormat, is(11));
        assertThat(_23In24HourFormat, is(23));
    }

    @Test
    public void testMinute() throws Exception {

        // Arrange
        final Date date = date(2000, 1, 1, 0, 30);

        // Act
        final int minute = CalendarUtils.minute(date);

        // Assert
        assertThat(minute, is(30));
    }

    @Test
    public void testSecond() throws Exception {

        // Arrange
        final Date date = date(2000, 1, 1, 0, 0, 40);

        // Act
        final int second = CalendarUtils.second(date);

        // Assert
        assertThat(second, is(40));
    }

    @Test
    public void testMillisecond() throws Exception {

        // Arrange
        final Date date = date(2000, 1, 1, 0, 0, 0, 999);

        // Act
        final int millisecond = CalendarUtils.millisecond(date);

        // Assert
        assertThat(millisecond, is(999));
    }

}
