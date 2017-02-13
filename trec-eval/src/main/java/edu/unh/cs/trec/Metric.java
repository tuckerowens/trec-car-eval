package edu.unh.cs.trec;



public abstract class Metric {

  GroundTruth groundTruth;
  int counter;
  public Metric( GroundTruth gt ) {
    groundTruth = gt;
    counter = 0;
  }

  /**
  *
  * This function should increment the counter (assuming you use it)
  * and return a number for the current value of the metric
  */
  public double apply(Ranking ranking);

  //This function should reset all the values in the metric
  //so we can reuse them.
  public void reset();

}
