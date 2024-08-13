# Point Of Sale
## Prerequisites
- Maven (built with 3.9.8 but older version may be sufficient)
- Java 11 and later
## Test Classes
- CheckoutTest: Tests scenarios outlined in spec.  Function names map to scenarios in spec.  For instance "test1" is test class maps to "Test 1" in spec.  There are some extra tests to test other scenarios as well.
- HolidayTest: Tests holiday class
## Build/Test
- navigate to inventory-rental-app directory
- run `mvn clean test -D=<test class name>` to run specific test class or `mvn clean test` to run all tests
