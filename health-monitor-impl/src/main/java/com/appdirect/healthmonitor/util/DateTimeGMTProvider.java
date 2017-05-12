package com.appdirect.healthmonitor.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeGMTProvider {

	private static final ZoneId GMT_ZONE = ZoneId.of("GMT");

	private DateTimeGMTProvider() {
	}

	/**
	 * Returns the current GMT instant
	 *
	 * @return
	 */
	public static Instant getCurrentInstant() {
		return getCurrentDateTime().toInstant();
	}

	/**
	 * Returns the current String formatted GMT Date
	 *
	 * @param pattern
	 * @return
	 */
	public static String getCurrentDateString(String pattern) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern).withZone(GMT_ZONE);
		return dateFormatter.format(getCurrentInstant());
	}

	/**
	 * Returns the current GMT DateTime
	 *
	 * @return
	 */
	public static ZonedDateTime getCurrentDateTime() {
		return ZonedDateTime.now().withZoneSameInstant(GMT_ZONE);
	}
}
