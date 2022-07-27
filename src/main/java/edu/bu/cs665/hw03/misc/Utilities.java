package edu.bu.cs665.hw03.misc;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Some helper functions
 *
 * @author dlegaspi@bu.edu
 */
public final class Utilities {
    public static LocalDate toDate(@NonNull String sd) {
        return LocalDate.parse(sd);
    }

    public static BigDecimal toBankCurrency(double amount) {
        return BigDecimal.valueOf(amount);
    }

    public static boolean isLessThan(BigDecimal c1, BigDecimal c2) {
        return c1.compareTo(c2) < 0;
    }

    public static boolean isGreaterThan(BigDecimal c1, BigDecimal c2) {
        return c1.compareTo(c2) > 0;
    }

    public static boolean isEqual(BigDecimal c1, BigDecimal c2) {
        return c1.compareTo(c2) == 0;
    }

    private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d, yyyy");

    public static String formatDate(LocalDate date) {
        return DATE_FORMAT.format(date);
    }

    public static String formatCurrency(BigDecimal c) {
        return "$" + c.longValue();
    }

    public static boolean inRange(LocalDate date, LocalDate fromDate, LocalDate toDate, boolean inclusive) {
        if (inclusive) {
            return (date.equals(fromDate) || date.isAfter(fromDate)) &&
                            (date.equals(toDate) || date.isBefore(toDate));
        } else {
            return date.isAfter(fromDate) && date.isBefore(toDate);
        }
    }

    public static String removeSpaces(String s) {
        return s.replaceAll("\\s", "");
    }
}
