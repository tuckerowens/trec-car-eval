package edu.unh.cs.trec;

import java.util.ArrayList;
import java.io.*;
import java.util.Collections;


public class SystemResults {

  private ArrayList<ArrayList<Ranking>> rankings;

  public SystemResults( File runfile ) {

    rankings = new ArrayList<>();
    ArrayList<Ranking> qRank = new ArrayList<>();
    try {
      BufferedReader fin = new BufferedReader( new FileReader( runfile ) );
      String line;
      String lastQuery = null;
      while ( (line = fin.readLine()) != null ) {
        Ranking r = rankingFromString(line);
        if (lastQuery == null)
          lastQuery = r.query;
        if (r.query.equals(lastQuery))
          qRank.add( r );
        else {
          lastQuery = r.query;
          Collections.sort( qRank );
          rankings.add( qRank );
          qRank = new ArrayList();
          qRank.add( r );
        }
      }
    } catch (Exception e) {}
    if (!qRank.isEmpty()) {
      Collections.sort( qRank );
      rankings.add(qRank);
    }
  }

  public int queryCount() { return rankings.size(); }


  private Ranking rankingFromString(String ranking) {
    String parts[] = ranking.trim().split(" ");
    return new Ranking (
      parts[0],
      parts[1],
      parts[2],
      Integer.parseInt(parts[3]),
      Double.parseDouble(parts[4]),
      parts[5]
    );
  }

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
      return rank - r.rank;
    }

    public int compareTo(Object o) {
      if ( o instanceof Ranking )
        return rank - ((Ranking)o).rank;
      return 0; //why not...
    }

  }


}
