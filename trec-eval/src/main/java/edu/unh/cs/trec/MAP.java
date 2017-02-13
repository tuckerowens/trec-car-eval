package edu.unh.cs.trec;


public class MAP extends Metric {

  private int correct;
  private double sum;

  public MAP(GroundTruth gt ) {
    super(gt);
    correct = 0;
    this.sum = 0;
  }

  /**
  *
  * This function should increment the counter (assuming you use it)
  * and return a number for the current value of the metric
  */
  public double apply(Ranking ranking) {
    if (!relevent())
      return 0;
    counter++;
    if ( groundTruth.relevantToQuery( ranking.query, ranking.passage ) ||
        groundTruth.relevantToQuery(ranking.query, ranking.entity)) {
        correct++;
        sum += ((double) correct) / ((double) counter);
      }
    return sum / ((double) correct);
  }

  //This function should reset all the values in the metric
  //so we can reuse them.
  public void reset() {
    counter = 0;
    correct = 0;
    sum = 0;
  }

  //This should return if a metric has consumed
  // enough of the ranking to emit a final value
  public boolean relevent() {
    return true;
  }

  public String getName() {
    return "MAP";
  }

  // This should return the final result
  public double getResult() {
    if (correct == 0)
      return 0;
    return sum / ((double) correct);
  }

}
