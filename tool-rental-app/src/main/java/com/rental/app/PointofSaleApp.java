package com.rental.app;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
/**
 * Hello world!
 *
 */
public class PointofSaleApp
{
	private static final String TOOL_FILE_NAME = "tools.json";
	private static final String TOOL_TYPES_FILE_NAME = "tooltypes.json";
	private static final String TOOL_TYPE_NAME_KEY = "name";
	private static final String TOOL_TYPE_DAILY_CHARGE_KEY = "dailyCharge";
	private static final String TOOL_TYPE_WEEKEND_CHARGE_KEY = "weekendCharge";
	private static final String TOOL_TYPE_WEEKDAY_CHARGE_KEY = "weekdayCharge";
	private static final String TOOL_TYPE_HOLIDAY_CHARGE_KEY = "holidayCharge";
	private static final String TOOL_NAME_KEY = "name";
	private static final String TOOL_TYPE_KEY = "toolType";
	
	/*private static ToolStore populateStore(String toolTypesFileName, String toolFileName) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		File toolJsonFile = new File(toolTypesFileName);
    	File toolTypesJsonFile = new File(toolFileName);
    	List<HashMap<String,String>> jsonToolTypes = objectMapper.readValue(toolTypesJsonFile, new TypeReference<List<HashMap<String,String>>>(){});
    	List<HashMap<String,String>> jsonTools = objectMapper.readValue(toolTypesJsonFile, new TypeReference<List<HashMap<String,String>>>(){});
    	ToolStore store = new ToolStore();
    	HashMap<String,ToolType> toolTypes = new HashMap<String,ToolType>();
    	for(HashMap<String,String> type : jsonToolTypes) {
    		String typeName = type.get(TOOL_TYPE_NAME_KEY);
    		Double dailyCharge = Double.valueOf(type.get(TOOL_TYPE_DAILY_CHARGE_KEY));
    		Boolean weekendCharge = Boolean.valueOf(type.get(key))
    		toolTypes.put(type., value)
    	}
	}*/
	
    public static void main( String[] args ) throws StreamReadException, DatabindException, IOException
    {
    	System.out.println(Boolean.parseBoolean("F"));
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate dueLocalDate = LocalDate.parse("12/20/24", datePattern);
    }
}
