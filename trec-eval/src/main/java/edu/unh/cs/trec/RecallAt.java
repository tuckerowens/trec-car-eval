package edu.unh.cs.trec;


public class RecallAt extends Metric {

  private final int k;
  private int correct, relDocs;


  public RecallAt( int k,  GroundTruth gt ) {
    super(gt);
    counter = 0;
    relDocs = 0;
    this.k = k;
    correct = 0;
  }

  /**
  *
  * This function should increment the counter (assuming you use it)
  * and return a number for the current value of the metric
  */
  public double apply(Ranking ranking) {
    if (!relevent())
      return 0;
    if ( groundTruth.relevantToQuery( ranking.query, ranking.passage ) ||
        groundTruth.relevantToQuery(ranking.query, ranking.entity))
        correct++;
    counter++;
    if (relDocs == 0)
      relDocs = groundTruth.countDocsForQuery(ranking.query);
    if (relDocs == 0)
      throw new IllegalStateException( "Are you evaluating your system with queries we know nothing about..." );
    return ((double) correct) / ((double) relDocs);
  }

  //This function should reset all the values in the metric
  //so we can reuse them.
  public void reset() {
    counter = 0;
    correct = 0;
  }

  //This should return if a metric has consumed
  // enough of the ranking to emit a final value
  public boolean relevent() {
    return counter < k;
  }

  public String getName() {
    return "Recall@" + k;
  }

  // This should return the final result
  public double getResult() {
    if (counter == 0)
      return 1;
    return ((double) correct) / ((double) relDocs);
  }

}
