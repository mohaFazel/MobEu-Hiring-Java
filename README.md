# Mobiquity Assignment

This program is a Java application ​that implements a solution for Knapsack problem using Dynamic Programming.
  
The Knapsack problem is one of the most famous problems in Algorithms world which is taught at universities. The general definition of the Knapsack problem can easily find on [Wikipedia](https://en.wikipedia.org/wiki/Knapsack_problem) and the good tutorial to understand the solution for that presented in [Youtube](https://www.youtube.com/watch?v=8LusJS5-AGo).

There is also a brief description of Knapsack problem below:

### Knapsack Problem
You want to send your friend a package with different things.

Each thing you put inside the package has such parameters as index number, weight and cost. The package has a weight limit. Your goal is to determine which things to put into the package so that the total weight is less than or equal to the package limit and the total cost is as large as possible.

You would prefer to send a package which weighs less in case there is more than one package with the same price.

#### Input Sample
Your program should accept as its first argument a path to a filename. 

The input file contains several lines. Each line is one test case.
Each line contains the weight that the package can take (before the colon) and the list of things you need to choose. 

Each thing is enclosed in parentheses where the 1st number is a thing's index number, the 2nd is its weight and the 3rd is its cost. E.g.
```
81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)
8 : (1,15.3,€34)
75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)
56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)
```

#### Output Sample
Output sample
For each set of things that you put into a package provide a new row in the output string (items’ index numbers are separated by comma). E.g.
```$xslt
4 
- 
2,7 
8,9
```

## Build
You can simply build the project using `mvn clean install`

## Run
As it does not have any main class, it cannot run as an standalone application. So you will probably need to add it as a library (i.e. Maven dependency) to another application to use it. 