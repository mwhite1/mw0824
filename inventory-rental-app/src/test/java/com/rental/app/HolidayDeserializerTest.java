package com.rental.app;

import static org.junit.Assert.assertEquals;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class HolidayDeserializerTest {
	private ObjectMapper mapper;

	private static Holiday generateHoliday(List<Pair<HolidayAttrNames,Object>> attrs) {
		Holiday holiday = new Holiday();
		for (Pair<HolidayAttrNames,Object> attr : attrs) {
			Object val = attr.getSecond();
			if(val != null) {
				switch(attr.getFirst()) {
				case MONTH:
					holiday.setMonth((Month)val);
					break;
				case DAY_OF_MONTH:
					holiday.setDayOfMonth((Integer)val);
					break;
				case DAY_OF_WEEK:
					holiday.setDayOfWeek((DayOfWeek)val);
					break;
				case DAY_OF_WEEK_ORDINAL:
					holiday.setDayOfWeekOrdinal((Integer)val);
				default:
					break;

				}
			}
		}
		return holiday;
	}

	private static String generateHolidayJsonString(List<Pair<HolidayAttrNames,Object>> attrs) {
		List<String> jsonAttrs = new ArrayList<String>();
		for (Pair<HolidayAttrNames,Object> attr : attrs) {
			HolidayAttrNames attrName = attr.getFirst();
			String strAttrName = String.format("\"%s\"",attrName.label);
			Object val = attr.getSecond();
			String valString = "null";
			if(val != null) {
				switch(attrName) {
				case MONTH:
					valString = String.format("\"%s\"", val.toString().toLowerCase());
					break;
				case DAY_OF_MONTH:
					valString = String.format("%s", val);
					break;
				case DAY_OF_WEEK:
					valString = String.format("\"%s\"", val.toString().toLowerCase());
					break;
				case DAY_OF_WEEK_ORDINAL:
					valString = String.format("%s", val);
				default:
					break;

				}
			}
			jsonAttrs.add(String.format("%s:%s", strAttrName,valString));
		}
		return String.format("{%s}",String.join(",", jsonAttrs));
	}

	@Before
	public void setup() {
		mapper = JsonMapper.builder().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS).build();
	}

	@Test
	public void shouldDeserializeWithNullValues() throws Exception {
		ArrayList<Pair<HolidayAttrNames,Object>> attrList = new ArrayList<Pair<HolidayAttrNames,Object>>();
		attrList.add(new Pair<HolidayAttrNames,Object>(HolidayAttrNames.MONTH,null));
		Holiday expectedHoliday = generateHoliday(attrList);
		String stringToDeserialize = generateHolidayJsonString(attrList);
		Holiday actualHoliday = mapper.readValue(stringToDeserialize, Holiday.class);
		assertEquals(expectedHoliday,actualHoliday);
	}

	@Test
	public void shouldDeserializeWithAllValues() throws Exception {
		ArrayList<Pair<HolidayAttrNames,Object>> attrList = new ArrayList<Pair<HolidayAttrNames,Object>>();
		attrList.add(new Pair<HolidayAttrNames,Object>(HolidayAttrNames.MONTH,Month.APRIL));
		attrList.add(new Pair<HolidayAttrNames,Object>(HolidayAttrNames.DAY_OF_MONTH,5));
		attrList.add(new Pair<HolidayAttrNames,Object>(HolidayAttrNames.DAY_OF_WEEK,DayOfWeek.MONDAY));
		attrList.add(new Pair<HolidayAttrNames,Object>(HolidayAttrNames.DAY_OF_WEEK_ORDINAL,-1));
		Holiday expectedHoliday = generateHoliday(attrList);
		String stringToDeserialize = generateHolidayJsonString(attrList);
		Holiday actualHoliday = mapper.readValue(stringToDeserialize, Holiday.class);
		assertEquals(expectedHoliday,actualHoliday);
	}

}
