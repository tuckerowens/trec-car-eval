package evaluation;

import java.io.IOException;
import java.util.ArrayList;


public class evaluation {
	public static void main(String[] args) throws NumberFormatException, IOException{
		String dataName = "qrels.robust2004.txt";
		String dataPath = "data/";
		retrievalToy newfile = new retrievalToy(dataPath, dataName);
		
		metrics eval = new metrics(newfile, 3000,3000);
		eval.getResult();
		
	}
}
