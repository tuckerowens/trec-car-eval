package edu.unh.cs.trec;

public class Ranking implements Comparable {
  public final String entity;
  public final String passage;
  public final int rank;
  public final double sim;
  public final String runID;
  public final String query;

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

  public String toString() {

    return query + " " + passage + " " + rank;

  }

}
