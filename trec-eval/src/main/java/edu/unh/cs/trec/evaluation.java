package edu.unh.cs.trec;

import java.io.IOException;
import java.util.ArrayList;


public class evaluation {
	public static void main(String[] args) throws NumberFormatException, IOException{

		String filename = "data/qrels.robust2004.txt";
		if ( args.length == 1 )
			filename  = args[0];
		retrievalToy newfile = new retrievalToy(filename);

		metrics eval = new metrics(newfile, 3000,3000);
		eval.getResult();

	}
}
