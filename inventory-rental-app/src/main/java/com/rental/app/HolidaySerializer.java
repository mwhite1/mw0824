package com.rental.app;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Month;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class HolidaySerializer extends StdSerializer<Holiday>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HolidaySerializer() {
		this(null);
	}

	public HolidaySerializer(Class<Holiday> h) {
		super(h);
	}

	@Override
	public void serialize(Holiday value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		// TODO Auto-generated method stub
		gen.writeStartObject();
		Month month = value.getMonth();
		if(month == null)
			gen.writeNullField(HolidayAttrNames.MONTH.label);
		else
			gen.writeStringField(HolidayAttrNames.MONTH.label, value.getMonth().toString());
		gen.writeNumberField(HolidayAttrNames.DAY_OF_MONTH.label, value.getDayOfMonth());
		DayOfWeek dayOfWeek = value.getDayOfWeek();
		if(dayOfWeek == null)
			gen.writeNullField(HolidayAttrNames.DAY_OF_WEEK.label);
		else
			gen.writeStringField(HolidayAttrNames.DAY_OF_WEEK.label, value.getDayOfWeek().toString());
		gen.writeNumberField(HolidayAttrNames.DAY_OF_WEEK_ORDINAL.label, value.getDayOfWeekOrdinal());
		gen.writeEndObject();
	}



}
