package de.lubowiecki.client.utils;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public interface ViewHelper {
	
	// automatisch public static final
	DateTimeFormatter DATE_FMT = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
	DateTimeFormatter TIME_FMT = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
	DateTimeFormatter DATE_US_FMT = DateTimeFormatter.ofPattern("YYYY/MM/dd");

}
