package edu.unh.cs.trec;



public abstract class Metric {

  GroundTruth groundTruth;
  int counter;



  Metric( GroundTruth gt ) {
    groundTruth = gt;
    counter = 0;
  }

  public abstract double noResultsCase();

  /**
  *
  * This function should increment the counter (assuming you use it)
  * and return a number for the current value of the metric
  */
  public abstract double apply(Ranking ranking);

  //This function should reset all the values in the metric
  //so we can reuse them.
  public abstract void reset();

  //This should return if a metric has consumed
  // enough of the ranking to emit a final value
  public abstract boolean relevent();

  // This should return the final result
  public abstract  double getResult();

  public abstract String getName();


}
