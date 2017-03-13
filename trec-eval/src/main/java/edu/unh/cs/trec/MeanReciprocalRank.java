package edu.unh.cs.trec;


public class MeanReciprocalRank extends Metric {

  private boolean gotResult;
  private double result;

  public MeanReciprocalRank( GroundTruth gt ) {
    super(gt);
    gotResult = false;
    result = 0;
  }

  public double noResultsCase() {
    return 0;
  }

  /**
  *
  * This function should increment the counter (assuming you use it)
  * and return a number for the current value of the metric
  */
  public double apply(Ranking ranking) {
    if (!relevent())
      return 0 ;
    counter++;
    if ( groundTruth.relevantToQuery( ranking.query, ranking.passage ) ||
        groundTruth.relevantToQuery(ranking.query, ranking.entity)) {
        result = 1.0 / counter;
        gotResult = true;
      }
    return result;
  }

  //This function should reset all the values in the metric
  //so we can reuse them.
  public void reset() {
    gotResult = false;
    result = 0;
    counter = 0;
  }

  //This should return if a metric has consumed
  // enough of the ranking to emit a final value
  public boolean relevent() {
    return !gotResult;
  }

  // This should return the final result
  public  double getResult() {
    return result;
  }

  public String getName() {
    return "Mean Reciprocal Rank";
  }

}
