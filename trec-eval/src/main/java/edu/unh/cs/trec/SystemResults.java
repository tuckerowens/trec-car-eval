package edu.unh.cs.trec;

import java.util.ArrayList;
import java.io.*;
import java.util.Collections;


public class SystemResults {

  private ArrayList<ArrayList<Ranking>> rankings;
  private int testedQueries;

  public SystemResults( File runfile, int testedQueries) {
    this.testedQueries = testedQueries;
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



  public void evaluateMetrics( Metric[] metrics ) {
    ArrayList<ArrayList<Double>> results = new ArrayList<>();
    double sums[] = new double[metrics.length];
    for (int i = 0; i < metrics.length; i++) {
      results.add( new ArrayList() );
      sums[i] = 0.0;
    }

    boolean metricsReady = false;
    //Starting to think the word Ranking is overused
    for (ArrayList<Ranking> ranking : rankings) {
      for (Ranking r : ranking) {
        metricsReady = true;
        for (int i = 0; i < metrics.length; i++) {
          metrics[i].apply( r );
          if(metricsReady && metrics[i].relevent())
            metricsReady = false;
        }
        if (metricsReady)
          break;
      }
      for (int i = 0; i < metrics.length; i++) {
        results.get(i).add(metrics[i].getResult());
        sums[i] += metrics[i].getResult();

        metrics[i].reset();
      }
    }
    final int missingQCount = testedQueries - queryCount();
    for (int i = 0; i < missingQCount; i++ )
      for (int m = 0; m < metrics.length; m++)
        results.get(m).add(metrics[m].noResultsCase());


    double error[] = new double[ sums.length ];
    for ( int i = 0; i < error.length; i++ ) {
      sums[i] /= testedQueries;
      error[i] = 0;
      for (Double d : results.get(i))
        error[i] += Math.pow( sums[i] - d, 2 );
      error[i] = Math.sqrt( error[i] / results.get(i).size());
      System.out.println("Metric " + metrics[i].getName() + " = " + sums[i] + "\t err: " + error[i]);
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




}
