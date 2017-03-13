package edu.unh.cs.trec;


import java.io.File;

public class Eval {



  public static void main(String[] args) {
    if ( args.length != 2 ) {
      System.err.println("Required params: <runfile> <qrel dir>");
      return;
    }

    File runfile = new File( args[0] );
    File qrels = new File( args[1] );

    GroundTruth gt = new GroundTruth(qrels);
    SystemResults rf = new SystemResults(runfile, gt.queryCount());


    rf.evaluateMetrics(new Metric[] {
      new PrecisionAt(5, gt),
      new PrecisionAt(10, gt),
      new PrecisionAt(15, gt),
      new PrecisionAt(20, gt),
      new PrecisionAt(100, gt),
      new RPrecision(gt),
      new MAP(gt),
      new MeanReciprocalRank(gt)
    });

  }


}
