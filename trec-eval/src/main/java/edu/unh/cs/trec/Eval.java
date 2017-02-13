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
    SystemResults rf = new SystemResults(runfile);


  }


}
