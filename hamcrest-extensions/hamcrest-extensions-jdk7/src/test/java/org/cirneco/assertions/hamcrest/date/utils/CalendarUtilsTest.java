package org.cirneco.assertions.hamcrest.date.utils;

import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.cirneco.assertions.hamcrest.date.BaseDateMatcherTest.TIME_ZONE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalendarUtilsTest {

    @BeforeClass
    public static void setBeforeClass() {
        System.setProperty("user.timezone", "UTC");
    }


    @Test
    public void testFromDateToCalendar() throws Exception {
        //Arrange
        final Date date = new DateTime(2015, 12, 21, 10, 0, 59, 999, TIME_ZONE).toDate();

        //Act
        final Calendar calendar = CalendarUtils.fromDateToCalendar(date);

        //Assert
        assertThat("Expected that the Calendar has the same timestamp than the date",
                calendar.getTimeInMillis(), is(date.getTime()));
    }

    @Test
    public void testWeekDay() throws Exception {
        //Arrange
        final Date mondayDate = new DateTime(2015, 12, 21, 0, 0, 0, TIME_ZONE).toDate();
        final Date fridayDate = new DateTime(2015, 12, 25, 0, 0, 0, TIME_ZONE).toDate();
        final Date nextMondayDate = new DateTime(2015, 12, 28, 0, 0, 0, TIME_ZONE).toDate();

        //Act
        final int mondayWeekDay = CalendarUtils.weekDay(mondayDate);
        final int fridayWeekDay = CalendarUtils.weekDay(fridayDate);
        final int nextMondayWeekDay = CalendarUtils.weekDay(nextMondayDate);

        //Assert
        assertThat(mondayWeekDay, is(2));
        assertThat(fridayWeekDay, is(6));
        assertThat(nextMondayWeekDay, is(2));
    }

    @Test
    public void testMonth() throws Exception {
        //Arrange
        final Date februaryDate = new DateTime(2015, 2, 21, 0, 0, 0, TIME_ZONE).toDate();
        final Date decemberDate = new DateTime(2015, 12, 21, 0, 0, 0, TIME_ZONE).toDate();

        //Act
        final int februaryMonth = CalendarUtils.month(februaryDate);
        final int decemberMonth = CalendarUtils.month(decemberDate);

        //Assert
        assertThat(februaryMonth, is(2));
        assertThat(decemberMonth, is(12));
    }

    @Test
    public void testWeekOfYear() throws Exception {
        //Arrange
        final Date week1Date = new DateTime(2014, 12, 29, 0, 0, 0, TIME_ZONE).toDate();
        final Date week2Date = new DateTime(2015, 1, 5, 0, 0, 0, TIME_ZONE).toDate();
        final Date week52Date = new DateTime(2015, 12, 27, 0, 0, 0, TIME_ZONE).toDate();
        final Date week53Date = new DateTime(2016, 1, 3, 0, 0, 0, TIME_ZONE).toDate();

        //Act
        final int week1 = CalendarUtils.weekOfYear(week1Date);
        final int week2 = CalendarUtils.weekOfYear(week2Date);
        final int week52 = CalendarUtils.weekOfYear(week52Date);
        final int week53 = CalendarUtils.weekOfYear(week53Date);

        //Assert
        assertThat(week1, is(1));
        assertThat(week2, is(2));
        assertThat(week52, is(52));
        assertThat(week53, is(53));
    }

    @Test
    public void testDay() throws Exception {
        //Arrange
        final Date day1Date = new DateTime(2015, 1, 31, 0, 0, 0, TIME_ZONE).toDate();
        final Date day2Date = new DateTime(2000, 2, 29, 0, 0, 0, TIME_ZONE).toDate();

        //Act
        final int day1 = CalendarUtils.day(day1Date);
        final int day2 = CalendarUtils.day(day2Date);

        //Assert
        assertThat(day1, is(31));
        assertThat(day2, is(29));
    }

    @Test
    public void testDayOfYear() throws Exception {
        //Arrange
        final Date day1Date = new DateTime(2015, 1, 31, 0, 0, 0, TIME_ZONE).toDate();
        final Date day2Date = new DateTime(2000, 2, 29, 0, 0, 0, TIME_ZONE).toDate();
        final Date day3Date = new DateTime(2000, 3, 1, 0, 0, 0, TIME_ZONE).toDate();

        //Act
        final int day1 = CalendarUtils.dayOfYear(day1Date);
        final int day2 = CalendarUtils.dayOfYear(day2Date);
        final int day3 = CalendarUtils.dayOfYear(day3Date);

        //Assert
        assertThat(day1, is(31));
        assertThat(day2, is(60));
        assertThat(day3, is(61));
    }

    @Test
    public void testYear() throws Exception {
        //Arrange
        final Date year1Date = new DateTime(2015, 1, 1, 1, 0, 0, 0, TIME_ZONE).toDate();
        final Date year2Date = new DateTime(2000, 1, 1, 1, 0, 0, 0, TIME_ZONE).toDate();

        //Act
        final int year1 = CalendarUtils.year(year1Date);
        final int year2 = CalendarUtils.year(year2Date);

        //Assert
        assertThat(year1, is(2015));
        assertThat(year2, is(2000));
    }

    @Test
    public void testIsLeapYear() throws Exception {
        //Arrange
        final Date leapYearDate = new DateTime(2000, 1, 1, 1, 0, 0, 0, TIME_ZONE).toDate();
        final Date nonLeapYearDate = new DateTime(2001, 1, 1, 1, 0, 0, 0, TIME_ZONE).toDate();

        //Act
        final boolean is2000LeapYear = CalendarUtils.isLeapYear(leapYearDate);
        final boolean is2001LeapYear = CalendarUtils.isLeapYear(nonLeapYearDate);

        //Assert
        assertThat(is2000LeapYear, is(true));
        assertThat(is2001LeapYear, is(false));
    }

    @Test
    public void testIsAM() throws Exception {
        //Arrange
        final Date amDate = new DateTime(2000, 1, 1, 11, 0, 0, 0, TIME_ZONE).toDate();
        final Date pmDate = new DateTime(2000, 1, 1, 23, 0, 0, 0, TIME_ZONE).toDate();

        //Act
        final boolean is11AM = CalendarUtils.isAM(amDate);
        final boolean is23AM = CalendarUtils.isAM(pmDate);

        //Assert
        assertThat(is11AM, is(true));
        assertThat(is23AM, is(false));
    }

    @Test
    public void testIsPM() throws Exception {
        //Arrange
        final Date amDate = new DateTime(2000, 1, 1, 11, 0, 0, 0, TIME_ZONE).toDate();
        final Date pmDate = new DateTime(2000, 1, 1, 23, 0, 0, 0, TIME_ZONE).toDate();

        //Act
        final boolean is11PM = CalendarUtils.isPM(amDate);
        final boolean is23PM = CalendarUtils.isPM(pmDate);

        //Assert
        assertThat(is11PM, is(false));
        assertThat(is23PM, is(true));
    }

    @Test
    public void testHour12() throws Exception {
        //Arrange
        final Date amDate = new DateTime(2000, 1, 1, 11, 0, 0, 0, TIME_ZONE).toDate();
        final Date pmDate = new DateTime(2000, 1, 1, 23, 0, 0, 0, TIME_ZONE).toDate();

        //Act
        final int _11In12HourFormat = CalendarUtils.hour12(amDate);
        final int _23In12HourFormat = CalendarUtils.hour12(pmDate);

        //Assert
        assertThat(_11In12HourFormat, is(11));
        assertThat(_23In12HourFormat, is(11));
    }

    @Test
    public void testHour24() throws Exception {
        //Arrange
        final Date amDate = new DateTime(2000, 1, 1, 11, 0, 0, 0, TIME_ZONE).toDate();
        final Date pmDate = new DateTime(2000, 1, 1, 23, 0, 0, 0, TIME_ZONE).toDate();

        //Act
        final int _11In24HourFormat = CalendarUtils.hour24(amDate);
        final int _23In24HourFormat = CalendarUtils.hour24(pmDate);

        //Assert
        assertThat(_11In24HourFormat, is(11));
        assertThat(_23In24HourFormat, is(23));
    }

    @Test
    public void testMinute() throws Exception {
        //Arrange
        final Date date = new DateTime(2000, 1, 1, 0, 30, 0, 0, TIME_ZONE).toDate();

        //Act
        final int minute = CalendarUtils.minute(date);

        //Assert
        assertThat(minute, is(30));
    }

    @Test
    public void testSecond() throws Exception {
        //Arrange
        final Date date = new DateTime(2000, 1, 1, 0, 0, 40, 0, TIME_ZONE).toDate();

        //Act
        final int second = CalendarUtils.second(date);

        //Assert
        assertThat(second, is(40));
    }

    @Test
    public void testMillisecond() throws Exception {
        //Arrange
        final Date date = new DateTime(2000, 1, 1, 0, 0, 0, 999, TIME_ZONE).toDate();

        //Act
        final int millisecond = CalendarUtils.millisecond(date);

        //Assert
        assertThat(millisecond, is(999));
    }

}