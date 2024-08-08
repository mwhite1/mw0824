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
import org.apache.commons.cli.*;

/**
 * Hello world!
 *
 */
public class PointofSaleApp {
	private static final String TOOL_FILE_NAME = "tools.json";
	private static final String TOOL_TYPES_FILE_NAME = "tooltypes.json";
	private static final String TOOL_TYPE_NAME_KEY = "name";
	private static final String TOOL_TYPE_DAILY_CHARGE_KEY = "dailyCharge";
	private static final String TOOL_TYPE_WEEKEND_CHARGE_KEY = "weekendCharge";
	private static final String TOOL_TYPE_WEEKDAY_CHARGE_KEY = "weekdayCharge";
	private static final String TOOL_TYPE_HOLIDAY_CHARGE_KEY = "holidayCharge";
	private static final String TOOL_NAME_KEY = "name";
	private static final String TOOL_TYPE_KEY = "toolType";
	private static final String TOOL_FILE_ARG_DESC = "json file containing tools";
	private static final String TOOL_JSON_FILE_NAME_OPT = "toolJsonFileName";
	private static final String TOOL_TYPE_JSON_FILE_NAME_OPT = "toolTypeJsonFileName";
	
	private static final String TOOL_TYPE_FILE_ARG_DESC = "json file containing tool types";

	public static boolean populateStoreInventory(String toolFileName, String toolTypeFileName, RentalInventory inventory) {
		ObjectMapper objectMapper = new ObjectMapper();
		File toolJsonFile = new File(toolFileName);
		File toolTypesJsonFile = new File(toolFileName);
		try {
			HashMap<String, HashMap<String,String>> jsonToolTypes = objectMapper.readValue(toolTypesJsonFile,
					new TypeReference<HashMap<String, HashMap<String,String>>>() {
					});
			List<HashMap<String, String>> jsonTools = objectMapper.readValue(toolJsonFile,
					new TypeReference<List<HashMap<String, String>>>() {
					});
			ListMapRentalInventoryPopulator inventoryPopulator = new ListMapRentalInventoryPopulator(jsonTools,jsonToolTypes);
			inventoryPopulator.populateInventory(inventory);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public static boolean printRentalAgreement(Scanner sc, RentalInventory inventory) {
		System.out.println("Enter tool code");
		String toolCode = sc.next();
		System.out.println("Enter number of rental days");
		int numRentalDays = sc.nextInt();
		System.out.println("Enter discount percent");
		int discountPercent = sc.nextInt();
		System.out.println("Enter checkout date");
		String checkoutDate = sc.next();
		try {
			RentalAgreement agreement = inventory.createRentalAgreement(toolCode, numRentalDays, discountPercent, checkoutDate);
			System.out.println(agreement.printRentalAgreement());
		}
		catch(RentalInventoryException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public static CommandLine parseArgs(String[] args) {
		Options options = new Options();
		Option toolsJsonFileName = Option.builder().longOpt(TOOL_JSON_FILE_NAME_OPT).hasArg().required(false).desc(TOOL_FILE_ARG_DESC).build();
		options.addOption(toolsJsonFileName);
		Option toolTypeJsonFileName = Option.builder().longOpt(TOOL_TYPE_JSON_FILE_NAME_OPT).hasArg().required(false).desc(TOOL_FILE_ARG_DESC).build();
		options.addOption(toolTypeJsonFileName);
		CommandLine cmd;
	    CommandLineParser parser = new BasicParser();
	    HelpFormatter helper = new HelpFormatter();
	    try {
	        cmd = parser.parse(options, args);
	        if(cmd.hasOption("a")) {
	        System.out.println("Alpha activated");
	        }

	        if (cmd.hasOption("c")) {
	        String opt_config = cmd.getOptionValue("config");
	        System.out.println("Config set to " + opt_config);
	        }
	    } catch (ParseException e) {
	        System.out.println(e.getMessage());
	        helper.printHelp("Usage:", options);
	        return null;
	    }
	    return cmd;
	}
	
	public static void main(String[] args) {
	    CommandLine cmd = parseArgs(args);
	    String toolJsonFileName = cmd.getOptionValue(TOOL_JSON_FILE_NAME_OPT, TOOL_FILE_NAME);
	    String toolTypeJsonFileName = cmd.getOptionValue(TOOL_TYPE_JSON_FILE_NAME_OPT, TOOL_TYPES_FILE_NAME);
	    RentalInventory inventory = new StoreRentalInventory(new ToolRentalAgreementGenerator());
	    if(!populateStoreInventory(toolJsonFileName, toolTypeJsonFileName, inventory))
	    	System.exit(1);
	    Scanner inputScanner = new Scanner(System.in);
	    boolean keepRunning = true;
	    while(keepRunning){
	    	System.out.println("Please type 1 to generate rental agreement and 2 to exit");
	    	switch(inputScanner.nextInt()) {
	    		case 1:
	    			printRentalAgreement(inputScanner,inventory);
	    			break;
	    		case 2:
	    			System.out.println("Exiting");
	    			keepRunning = false;
	    			break;
	    	}
	    }
	}
}
