package de.lubowiecki.client.utils;

import java.util.Comparator;

public class ValueUtils {

    public static final Comparator<String> DOUBLE_COMP = (a, b) -> Double.compare(strToDouble(a), strToDouble(b));

    private ValueUtils() {
    }

    public static double strToDouble(String str) {
        try {
            return Double.parseDouble(str.replace(",", "."));
        }
        catch(NumberFormatException e) {
            return 0;
        }
    }

    public static String doubleToStr(double d) {
        return String.format("%.2f", d);
    }
}
