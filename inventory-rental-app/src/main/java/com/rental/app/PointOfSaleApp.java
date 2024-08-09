package com.rental.app;

import java.io.File;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import org.apache.commons.cli.*;

/**
 * Hello world!
 *
 */
public class PointOfSaleApp {
	private static final String TOOL_FILE_NAME = "items.json";
	private static final String TOOL_TYPES_FILE_NAME = "types.json";
	private static final String TOOL_FILE_ARG_DESC = "json file containing tools";
	private static final String TOOL_TYPE_FILE_ARG_DESC = "json file containing tools";
	private static final String TOOL_DOES_NOT_EXIST_MESSAGE = "Tool with code %s does not exist";
	private static final String INVALID_STATE_MESSAGE = "Invalid argument.  Please enter %s or %s";
	private static final String CHOOSE_STATE_MESSAGE = "Please type %s to generate rental agreement and %s to exit";
	private static final String TOOL_JSON_FILE_NAME_OPT = "toolJsonFileName";
	private static final String TOOL_TYPE_JSON_FILE_NAME_OPT = "toolTypeJsonFileName";
	private static final int GENERATE_RENTAL_AGREEMENT_STATE = 1;
	private static final int EXIT_STATE = 2;

	public static boolean populateStoreInventory(String toolFileName, String toolTypeFileName,
			RentalInventory inventory) {
		ObjectMapper objectMapper = new ObjectMapper();
		File toolJsonFile = new File(toolFileName);
		File toolTypesJsonFile = new File(toolTypeFileName);
		try {
			HashMap<String, InventoryItemType> jsonToolTypes = objectMapper.readValue(toolTypesJsonFile,
					new TypeReference<HashMap<String, InventoryItemType>>() {
					});
			List<HashMap<String, String>> jsonTools = objectMapper.readValue(toolJsonFile,
					new TypeReference<List<HashMap<String, String>>>() {
					});
			ListMapRentalInventoryPopulator inventoryPopulator = new ListMapRentalInventoryPopulator(jsonTools,
					jsonToolTypes);
			inventoryPopulator.populateInventory(inventory);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public static void printRentalAgreement(Scanner sc, RentalInventory inventory) {
		System.out.println("Enter tool code");
		String toolCode = sc.next();
		System.out.println("Enter number of rental days");
		int numRentalDays = sc.nextInt();
		System.out.println("Enter discount percent");
		int discountPercent = sc.nextInt();
		System.out.println("Enter checkout date");
		String checkoutDate = sc.next();
		System.out.println();
		try {
			RentalAgreement agreement = inventory.createRentalAgreement(toolCode, numRentalDays, discountPercent,
					checkoutDate);
			System.out.println(agreement.printRentalAgreement());
		} catch (RentalInventoryException e) {
			System.out.println(e.getMessage());
		}
	}

	public static CommandLine parseArgs(String[] args) {
		Options options = new Options();
		Option toolsJsonFileName = Option.builder().longOpt(TOOL_JSON_FILE_NAME_OPT).hasArg().required(false)
				.desc(TOOL_FILE_ARG_DESC).build();
		options.addOption(toolsJsonFileName);
		Option toolTypeJsonFileName = Option.builder().longOpt(TOOL_TYPE_JSON_FILE_NAME_OPT).hasArg().required(false)
				.desc(TOOL_TYPE_FILE_ARG_DESC).build();
		options.addOption(toolTypeJsonFileName);
		CommandLine cmd;
		CommandLineParser parser = new DefaultParser();
		HelpFormatter helper = new HelpFormatter();
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			helper.printHelp("Usage:", options);
			return null;
		}
		return cmd;
	}

	public static void main(String[] args) {
		CommandLine cmd = parseArgs(args);
		if (cmd == null) {
			System.exit(1);
		}
		String toolJsonFileName = cmd.getOptionValue(TOOL_JSON_FILE_NAME_OPT, TOOL_FILE_NAME);
		String toolTypeJsonFileName = cmd.getOptionValue(TOOL_TYPE_JSON_FILE_NAME_OPT, TOOL_TYPES_FILE_NAME);
		RentalInventory inventory = new StoreRentalInventory(new ToolRentalAgreementGenerator());
		if (!populateStoreInventory(toolJsonFileName, toolTypeJsonFileName, inventory))
			System.exit(1);
		Scanner inputScanner = new Scanner(System.in);
		boolean keepRunning = true;
		while (keepRunning) {
			System.out.println(String.format(CHOOSE_STATE_MESSAGE, GENERATE_RENTAL_AGREEMENT_STATE, EXIT_STATE));
			switch (inputScanner.nextInt()) {
			case GENERATE_RENTAL_AGREEMENT_STATE:
				printRentalAgreement(inputScanner, inventory);
				break;
			case EXIT_STATE:
				System.out.println("Exiting");
				keepRunning = false;
				break;
			default:
				System.out.println(String.format(INVALID_STATE_MESSAGE, GENERATE_RENTAL_AGREEMENT_STATE, EXIT_STATE));
			}
		}
	}
}
