# Currency Manager
Application provides managing of personal expenses using basic command-line operations.

> Tests for app are located in `com.poodel.commands.commands_implementation.unit_tests_commands` folder of project

## How to build and run 
__Note__: To run this app you need to have Maven, installed and added to SysPath on your computer. [Maven Guide](http://www.apache-maven.ru/install.html).

Open project using IDE (Intellij Idea, etc.) or run console in project folder and enter:  

```
mvn clean install
```
```
mvn exec:java -Dexec.mainClass="com.poodel.ExpensesManager"
```

## Command usage listning

##### Add new expenses:
```
    > add 2017-04-25 12 USD Jogurt
    > add 2017-04-25 3 EUR “French fries”
```
##### Get list of all expenses sorted by date:
```
    > list
```    
##### Remove all expenses with specified date:
```    
   > clear 2017-04-27
```
##### Get total amount of expenses, fetched to specific currency (according to [Fixer](http://fixer.io)):
```
   > total PLN
```
##### Finish execution of application
```
   > exit
```

###### To find all available currencies [go to the Enum of currencies](https://github.com/Werald/currencymanager/blob/master/src/main/java/com/poodel/commands/CurrencyType.java)
