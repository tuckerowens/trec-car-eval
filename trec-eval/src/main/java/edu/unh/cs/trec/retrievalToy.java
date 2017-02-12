package edu.unh.cs.trec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class retrievalToy {
	 static Map<String, Set<String> >truth;
	 static Set<String> truth_rel;
	 static ArrayList<String> ranklist;
//	@SuppressWarnings("static-access")
	public retrievalToy(String filename) throws NumberFormatException, IOException{


		ArrayList<String> documentsRanking = new ArrayList<String>();
		Map<String, Set<String> >groundtruth = new HashMap<String, Set<String>>();
		Set<String> groundtruthRelOnly = new HashSet();
		String line;
		InputStream is = new FileInputStream(new File(filename));
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		while ((line =br.readLine()) != null){

				String[] parsedLine = line.split(" ");
				String sectionId = parsedLine[0];
				String docId = parsedLine[2];
				documentsRanking.add(docId);
				int relScore = Integer.valueOf(parsedLine[3]);

				if(relScore>0){
					groundtruthRelOnly.add(docId);
					if(groundtruth.containsKey(sectionId)){
						groundtruth.get(sectionId).add(docId);
					}else{
						Set<String> trueDocId= new HashSet <String>();
						trueDocId.add(docId);
						groundtruth.put(queryId, trueDocId);}
				}
		}
		br.close();
		truth = groundtruth;
		ranklist=documentsRanking;
		truth_rel = groundtruthRelOnly;
	}

	public  ArrayList<String> getRankList(){
		return ranklist;
	}


	public  Map<String, Set<String>> getTruth(){
		return truth;
	}

	public Set<String> getTruthRel(){
		return truth_rel;
	}
}
