// package evaluation;
package edu.unh.cs.trec;

import java.util.ArrayList;
import java.util.Set;

public class metrics {
	
	retrievalToy newfile;
	ArrayList <String> ranklist;
	ArrayList <String> result;
	Set<String> truth_rel;
	int map_k;
	int pr_k;
	int rankLength;
	ArrayList<String> outputRank;
	ArrayList<ArrayList<String>> metricOutput;
	int top_k;
	public metrics (retrievalToy newfile, int map_k, int pr_k, int rankLength){
		this.newfile=newfile;
		this.ranklist =newfile.getRankList();
		this.truth_rel = newfile.getTruthRel();
		this.metricOutput = metric_methods(map_k, pr_k,rankLength);
		this.result = metricOutput.get(0);
		this.outputRank = metricOutput.get(1);
		this.map_k = map_k;
		this.pr_k =pr_k;  
		this.rankLength = rankLength;
		
		
		
	}
	
	public  ArrayList<ArrayList<String>>metric_methods(int map_k,int pr_k, int rankLength){
		ArrayList<String> eval = new ArrayList<String>();
		
		ArrayList<String> returnRankedList = new ArrayList<String>();
		String resultPerDoc = null;
		double MAP_a_q=0.0;
		double precisionAtX=0.0;
		double recallAtX=0.0;
		double precisionAtR=0.0;	
		int counter=1;
		int num_of_true = truth_rel.size();
		System.out.println("number of true passages: " + num_of_true);		
		int ranklist_higherbound = Math.max(num_of_true, Math.max(map_k,pr_k));
		for(String Id_s: ranklist.subList(0, ranklist_higherbound)){
			String docId = Id_s.split(" ")[1];
			String sectionId = Id_s.split(" ")[0];
			String rankPerDoc = sectionId + " "+ docId+" ";
			if(truth_rel.contains(docId)){
				if(counter<=map_k){
					MAP_a_q += 1.0/(1+ranklist.indexOf(Id_s));
					rankPerDoc+="1";
					//System.out.println("n"+ranklist.indexOf(docId));
					}
				if(counter<=pr_k){
					precisionAtX+=1.0;
//					System.out.println(precisionAt100);
					recallAtX+=1.0;}
				if(counter<=num_of_true){
					precisionAtR+=1.0;}	
				
//				System.out.println(MAP_a_q);
			}else{
				rankPerDoc+="0";
			}
			counter+=1;
			if(counter<rankLength){
			returnRankedList.add(rankPerDoc);
			}		
			}	
		
		eval.add(Double.toString(MAP_a_q/num_of_true));
		eval.add(Double.toString(precisionAtX/pr_k));
		eval.add(Double.toString(recallAtX/num_of_true));
		eval.add(Double.toString(precisionAtR/num_of_true));
		
	
		ArrayList<ArrayList<String>> returnedOutput = new ArrayList<ArrayList<String>>();
		returnedOutput.add(eval);
		returnedOutput.add(returnRankedList);
		return returnedOutput;
	}
	public  ArrayList <String> getResult(){
		System.out.printf("MAP@%d is "+this.result.get(0)+"\n",this.map_k);
		System.out.printf("Precision@%d is "+this.result.get(1)+"\n",this.pr_k);
		System.out.printf("recall@%d is "+this.result.get(2)+"\n",this.pr_k);
		System.out.printf("Precision@R is "+this.result.get(3)+"\n");

		return this.result;
	}
	public ArrayList<String> getRankOutput(){
		return this.outputRank;
	}
}
