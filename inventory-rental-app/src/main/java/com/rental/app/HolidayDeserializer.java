package com.rental.app;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Month;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class HolidayDeserializer extends StdDeserializer<Holiday>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Object getNodeValue(JsonNode node, HolidayAttrNames attrName) {
		JsonNode attrNode = node.get(attrName.label);
		return attrNode == null || attrNode.isNull() ? null : attrNode;
	}
	
	public HolidayDeserializer() {
		this(null);
	}
	
	public HolidayDeserializer(Class<Holiday> h) {
		super(h);
	}

	@Override
	public Holiday deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		// TODO Auto-generated method stub
		JsonNode node = p.getCodec().readTree(p);
		Object monthNodeValue = getNodeValue(node,HolidayAttrNames.MONTH);
		Month month = monthNodeValue == null ? null : Month.valueOf((String)((TextNode)monthNodeValue).textValue());
		Object dayOfMonthNodeValue = getNodeValue(node,HolidayAttrNames.DAY_OF_MONTH);
		int dayOfMonth = dayOfMonthNodeValue == null ? 0 : (Integer)((IntNode)dayOfMonthNodeValue).numberValue();
		Object dayOfWeekNodeValue = getNodeValue(node,HolidayAttrNames.DAY_OF_WEEK);
		DayOfWeek dayOfWeek = dayOfWeekNodeValue == null ? null : DayOfWeek.valueOf((String)((TextNode)dayOfWeekNodeValue).textValue());
		Object dayOfWeekOrdinalNodeValue = getNodeValue(node,HolidayAttrNames.DAY_OF_WEEK_ORDINAL);
		int dayOfWeekOrdinal = dayOfWeekOrdinalNodeValue == null ? 0 : (Integer)((IntNode)dayOfWeekOrdinalNodeValue).numberValue();
		return new Holiday(month,dayOfMonth,dayOfWeek,dayOfWeekOrdinal);
	}
}
