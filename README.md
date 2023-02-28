# Sudoku Validator

## The goal of the program
The goal of a program is to validate a sudoku game passed to it via the REST interface.
Information about sudoku: https://en.wikipedia.org/wiki/Sudoku

## Game Rules
 - 9x9 grid
 - no repeating numbers in a row
 - no repeating numbers in a column
 - no repeating digit in a 3x3 sub-grid
 - empty fields do not invalidate the puzzle. A completely empty board should validate positively.

## Challenge
 - Your goal is to extend and modify the project in such a way, that it fulfills 
   the goals described above.
 - You're free to extend the interfaces, but please don't remove anything from them
 - Please pay attention to comments left in the code.
 - Your solution should be tested. Provided is one minimal "integration" 
   test, just to ensure you reached the wanted goal.
   This test should fail initially, and it is your primary goal to make it pass.
   Please add other tests you find valuable.

## Build instructions

```bash
# install gradle wrapper
> gradle wrapper

# build
> ./gradlew build

# build without tests
> ./gradlew build -x test

# run application from the command line
> ./gradlew bootRun

# clean the build
> ./gradlew clean
```
