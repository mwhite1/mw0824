package com.rental.app;

import static org.junit.Assert.assertEquals;

import java.time.DayOfWeek;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HolidaySerializerTest {
	private ObjectMapper mapper;
	
	private static String createJsonAttrName(String attrName) {
		return String.format("\"%s\":", attrName);
	}
	
	private static String generateJsonString(Holiday holiday) {
		Month month = holiday.getMonth();
		String monthString = month == null ? "null" : String.format("\"%s\"", month.toString());
		String dayOfMonthString = String.format("%s",holiday.getDayOfMonth());
		DayOfWeek dayOfWeek = holiday.getDayOfWeek();
		String dayOfWeekString = dayOfWeek == null ? "null" : String.format("\"%s\"", dayOfWeek.toString());
		String dayOfWeekOrdinalString = String.format("%s", holiday.getDayOfWeekOrdinal());
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append(createJsonAttrName(HolidayAttrNames.MONTH.label));
		builder.append(monthString);
		builder.append(",");
		builder.append(createJsonAttrName(HolidayAttrNames.DAY_OF_MONTH.label));
		builder.append(dayOfMonthString);
		builder.append(",");
		builder.append(createJsonAttrName(HolidayAttrNames.DAY_OF_WEEK.label));
		builder.append(dayOfWeekString);
		builder.append(",");
		builder.append(createJsonAttrName(HolidayAttrNames.DAY_OF_WEEK_ORDINAL.label));
		builder.append(dayOfWeekOrdinalString);
		builder.append("}");
		return builder.toString();
	}
	
	@Before
	public void setUp() {
		mapper = new ObjectMapper();
	}
	
	@Test
	public void shouldGenerateSerializedHolidayStringWithNullValues() throws Exception {
		Holiday testHoliday = new Holiday(null,0,null,0);
		String expectedJsonString = generateJsonString(testHoliday);
		String actualJsonString = mapper.writeValueAsString(testHoliday).replaceAll("\\s", "");
		assertEquals(expectedJsonString,actualJsonString);
	}
	
	@Test
	public void shouldGenerateSerializedHolidayString() throws Exception {
		Holiday testHoliday = new Holiday(Month.DECEMBER,3,DayOfWeek.MONDAY,5);
		String expectedJsonString = generateJsonString(testHoliday);
		String actualJsonString = mapper.writeValueAsString(testHoliday).replaceAll("\\s", "");
		assertEquals(expectedJsonString,actualJsonString);
	}
	
}
