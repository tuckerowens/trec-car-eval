package edu.unh.cs.trec;




public class RPrecision extends Metric {

  private int k;
  private int correct;

  public RPrecision( GroundTruth gt ) {
    super(gt);
    this.k = 0;
    correct = 0;
  }

  /**
  *
  * This function should increment the counter (assuming you use it)
  * and return a number for the current value of the metric
  */
  public double apply(Ranking ranking) {
    if (k == 0)
      k = groundTruth.countDocsForQuery( ranking.query );
    if (!relevent())
        return 0;
    if ( groundTruth.relevantToQuery( ranking.query, ranking.passage ) ||
        groundTruth.relevantToQuery(ranking.query, ranking.entity)) {
        correct++;
      }
    counter++;
    return ((double) correct) / ((double) counter);
  }

  //This function should reset all the values in the metric
  //so we can reuse them.
  public void reset() {
    counter = 0;
    correct = 0;
    k = 0; //This is by ranking.
  }

  //This should return if a metric has consumed
  // enough of the ranking to emit a final value
  public boolean relevent() {
    return counter < k;
  }

  public String getName() {
    return "RPrecision";
  }

  // This should return the final result
  public double getResult() {
    if (counter == 0 || k == 0)
      return 1;
    return ((double) correct) / ((double) k);
  }

}
