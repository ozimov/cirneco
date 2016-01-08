package it.ozimov.cirneco.hamcrest.java7.date;

import it.ozimov.cirneco.hamcrest.java7.BaseMatcherTest;

import org.joda.time.DateTimeZone;

import org.junit.BeforeClass;


public abstract class BaseDateMatcherTest extends BaseMatcherTest {

    public static final DateTimeZone TIME_ZONE = DateTimeZone.forID(
            "Etc/Greenwich");

    @BeforeClass public static void setBeforeClass() {
        System.setProperty("user.timezone", "UTC");
    }

}