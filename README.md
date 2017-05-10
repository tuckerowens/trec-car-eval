# Implementation of evaluation framework:

### dependencies:
The following must be installed on your machine:
  1. Maven build tool `mvn`
  2. Java 7
  
Alternative version of code (for confirmation) is stored at: https://github.com/gummibearehausen/treccar

 
## Usage: 

### Build
The code is written in Java as a Maven project. The user required to compile the project with Maven:
```shell
$ cd trec-eval
$ mvn package 
```
### Run 
```shell
$ java −jar ./ target/∗−SNAPSHOT. jar <runfile > <qrel dir>
```

## Modify
The main class of this project is located in `trec-eval/src/main/java/edu/unh/cs/trec/Eval.java`. 
It builds an array of metrics to be evaluated and passes them into the system. If you would like different metrics please 
feel free to change that line.

Example { Precision@5, Precision@10, MAP }
```java
  rf.evaluateMetrics(new Metric[] {
      new PrecisionAt(5, gt),
      new PrecisionAt(10, gt),
      new MAP(gt)
    });
```


## Extend
In this evaluation system, a `Metric` is an abstract class which is then extended. This means that it's not too complicated to 
implement your own custom metric and use it:
  1. Create a new class which extends Metric
  2. Use the new class as it was shown in the above section
 ## Contributers
*Bryan Zhang*
*Tucker Owens* 
*Bahram Behzadian*

### All members contributed equally in this code submission.
