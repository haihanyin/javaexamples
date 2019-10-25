package com.github.hh.je.time;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeTest {

    // https://lw900925.github.io/java/java8-newtime-api.html

    public void info() {
        Class[] oldClasses = new Class[] {
                Date.class,
                Calendar.class,
                TimeZone.class
        };
    }

    @Test
    public void testLocalDate() {
        System.out.println(LocalDate.now());
        System.out.println(LocalDate.now(ZoneId.of("Asia/Shanghai")));
        System.out.println(LocalDate.MAX);
        System.out.println(LocalDate.MIN);
        System.out.println(LocalDate.of(1, 2, 3));
        System.out.println(LocalDate.parse("1999-09-09"));
    }

    @Test
    public void testLocalDateTime() {
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
        System.out.println(LocalDateTime.MAX);
        System.out.println(LocalDateTime.MIN);
        System.out.println(LocalDateTime.of(1, 2, 3, 4, 5));
        System.out.println(LocalDateTime.parse("1999-08-07T12:21:15"));
    }

    @Test
    public void testYearAndMonthAndDay() {
        System.out.println(Month.of(1));
        System.out.println(Month.DECEMBER);
        System.out.println("===");

        System.out.println(Year.now());
        System.out.println(Year.of(2019));
        System.out.println(Year.parse("2019"));
        System.out.println("===");

        System.out.println(YearMonth.now());
        System.out.println(YearMonth.parse("2019-09"));
        System.out.println("===");

        System.out.println(MonthDay.now());
        System.out.println(MonthDay.of(1, 2));
    }

    @Test
    public void testInstant() {
        System.out.println(LocalDateTime.now());
        System.out.println(Instant.now());
    }

    @Test
    public void testDuration() throws InterruptedException {
        LocalDateTime time1 = LocalDateTime.now();
        Thread.sleep(1000);
        LocalDateTime time2 = LocalDateTime.now();
        Duration between = Duration.between(time1, time2);
        System.out.println(between.toHours());
        System.out.println(between.toMillis());

    }

    @Test
    public void testDuration2() {
        Duration duration = Duration.ofDays(3);
        LocalDateTime plus = LocalDateTime.now().plus(duration);
        System.out.println(plus);
    }

    @Test
    public void testPeriod() {
        Period period = Period.of(1, 1, 1);
        LocalDateTime plus = LocalDateTime.now().plus(period);
        System.out.println(plus);
    }

    @Test
    public void testTemporalAdjusters() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println(now.with(TemporalAdjusters.firstDayOfNextMonth()));
    }

    @Test
    public void testDateTimeFormatter() {
        Class<DateTimeFormatter> dateTimeFormatterClass = DateTimeFormatter.class;
    }

}
