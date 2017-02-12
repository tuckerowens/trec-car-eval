package edu.unh.cs.trec;

import java.util.ArrayList;
import java.util.Set;

public class metrics {

	retrievalToy newfile;
	ArrayList <String> ranklist;
	ArrayList <Double> result;
	Set<String> truth_rel;
	int map_k;
	int pr_k;


	int top_k;
	public metrics (retrievalToy newfile, int map_k, int pr_k){
		this.newfile=newfile;
		this.ranklist =newfile.getRankList();
		this.truth_rel = newfile.getTruthRel();
		this.result = metric_methods(map_k, pr_k);
		this.map_k = map_k;
		this.pr_k =pr_k;
	}

	public  ArrayList<Double>metric_methods(int map_k,int pr_k){
		ArrayList<Double> eval = new ArrayList<Double>();
		double MAP_a_q=0.0;


		double precisionAtX=0.0;
		double recallAtX=0.0;
		double precisionAtR=0.0;


		int data_patecounter=1;
		int num_of_true = truth_rel.size();

		int ranklist_higherbound = Math.max(num_of_true, Math.max(map_k,pr_k));
		for(String docId: ranklist.subList(0, ranklist_higherbound)){
                    if(truth_rel.contains(docId)){
                        if(counter<map_k){
                            MAP_a_q += 1.0/(1+ranklist.indexOf(docId));
                        }
                        if(counter<pr_k){
                            precisionAtX+=1.0;
                            recallAtX+=1.0;
                        }
                        if(counter<=num_of_true){
                            precisionAtR+=1.0;
                        }
                        counter+=1;
                    }
		}
		eval.add(MAP_a_q/num_of_true);

		return eval;
	}
	public  ArrayList <Double> getResult(){
		System.out.printf("MAP@%d is "+this.result.get(0)+"\n",this.map_k);
                // add Precision  @R, recall@%d
		System.out.printf("Precision@%d is "+this.result.get(1)+"\n",this.pr_k);
		System.out.printf("recall@%d is "+this.result.get(2)+"\n",this.pr_k);
		System.out.printf("Precision@R is "+this.result.get(3)+"\n");
		return this.result;
	}

}
