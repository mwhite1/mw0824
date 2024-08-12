# Point Of Sale
App that facilitates rental of tools from a store.  A tool is rented with the following information\
- Code: Unique identifier for tool
- Rental Day Count: Number of days tool is rented for
- Discount Percent: Discount applied to final price
- Checkout Date: Date rental starts
## Prerequisites
- Maven (built with 3.9.8 but older version may be sufficient)
- Java 11 and later
## Build
- navigate to inventory-rental-app directory
- run `mvn clean package assembly:single`
- Output file generated is point-of-sale-jar-with-dependencies.jar located in target directory
## Run
Application is command line application that requires three json files, which are included in the repo
### Input Files
This application requires three json files included in this repo.
#### Tool
Contains the inventory of tools that can be rented.  The file contains an array of objects with the following attributes\
- code: Unique identifier for tool.  Application will use this as key for lookup.
- type: Type of tool.  Unique identifier for json map in types file (which is mentioned below).  This attribute value is **case sensitive**
- brand: Brand of tool.
#### Type
JSON map of types with the tool type as the key.  Each value has the following attributes\
- name: Name of type
- dailyCharge: Charge to rent type per day
- weekdayCharge: `true` if there is charge to rent on weekday and `false` otherwise.
- weekendCharge: `true` if there is charge to rent on weekend and `false` otherwise.
- holidayCharge: `true` if there is charge to rent on holiday and `false` otherwise.
#### Holiday
Contains an array of objects representing holidays.  For each object, all attributes **do not** have to be present
- month: Month holiday falls on. Possible values are the full names of each month (JANUARY,FEBRUARY, etc.)
- dayOfMonth: the day of the month the holiday falls on
- dayOfWeek: the day of the week the holiday falls on.  Possible values are the full names of each day of the week (MONDAY,TUESDAY, etc.)
- dayOfWeekOrdinal: ordinal for the day of the week attribute value.  If value is positive, ordinal is counted from the front of the month and if negative, it is counted from the back of the month. For instance month=MAY, dayOfWeek=MONDAY, and dayOfWeekOrdinal=1 reads as "The first Monday in May".  Whereas if dayOfWeekOrdinal=-1 with all other values being the same, that would read as "The last Monday in May"

Example Holidays:
- July 4th: `{"month":"JULY","dayOfMonth":4}`
- Labor Day: `{"month":"SEPTEMBER","dayOfWeek":"MONDAY","dayOfWeekOrdinal":1}`
- Memorial Day: `{"month":"MAY","dayOfWeek":"MONDAY","dayOfWeekOrdinal":-1}`
### Command Line Options
- holidaysFileName: file containing all eligible holidays (default is holidays.json)
- toolJsonFileName: json file containing tools (default is items.json)
- toolTypeJsonFileName: json file containing tool types (default is types.json)
### Run Instructions
- Run `<run directory>/point-of-sale-jar-with-dependencies.jar <command line options>`
- Follow prompt
