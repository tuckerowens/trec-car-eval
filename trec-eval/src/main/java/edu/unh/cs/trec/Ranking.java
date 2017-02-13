package edu.unh.cs.trec;

public class Ranking implements Comparable {
  public String entity;
  public String passage;
  public int rank;
  public double sim;
  public String runID;
  public String query;

  Ranking( String query, String entity, String passage, int rank, double sim, String tag ) {
    this.entity = entity;
    this.passage = passage;
    this.rank = rank;
    this.sim = sim;
    this.runID = tag;
    this.query = query;
  }

  public int compareTo(Ranking r) {
    return r.rank - rank;
  }

  public int compareTo(Object o) {
    if ( o instanceof Ranking )
      return rank - ((Ranking)o).rank;
    return 0; //why not...
  }

}
