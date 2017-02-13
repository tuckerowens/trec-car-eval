// package evaluation;
package edu.unh.cs.trec;

import java.io.IOException;
import java.util.ArrayList;


public class evaluation {
	public static void main(String[] args) throws NumberFormatException, IOException{
		// String dataName = "qrels.robust2004.txt";
		// String dataPath = "data/";
                String filename = "data/qrels.robust2004.txt";
                if ( args.length ==1 )
                    filename = args[0];
		// retrievalToy newfile = new retrievalToy(dataPath, dataName);
		retrievalToy newfile = new retrievalToy(filename);
		metrics eval = new metrics(newfile, 5,5,10);
		eval.getResult();
		System.out.println(eval.getRankOutput());
	}
}
