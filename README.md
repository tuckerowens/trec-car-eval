# trec-car-eval

there are three \textbf{classes, metrics, evaluation and retrievalToy} respectively. 

evaluation class has the main function,  metrics class requires two essential components : a ranking list and a ground truth. 

In evaluation prototype, we use an input file to create a ranking list and a ground truth for experiment, then four evaluation metrics  can evaluation the ranking list for a single query.



==== retrievalToy class ====
a rank list and ground truth  can be created through accessing the attributes  of a retrievalToy object:
a rank list can be accessed using:   \textbf{[a retrievalToy object].getRankList()}
a truth set can be accessed using:   \textbf{[a retrievalToy object].getTruthRel()}

*in the retrievalToy class, there is also an accessor function   [a retrievalToy object].getTruth()  which can potentially return a hash map ( section id : set <true passage id, ...> for page retrieval task,.


==== metrics class ====
There are 4 evaluation metrics:
1. MAP@K (top k): Mean Average Precision @ k
2. Precision@R
3. Precision@X
4. Recall@X

Create a metrics object using a retrievalToy object (ranking list and truth components).
\textbf{[metrics object ].metric_methods():}  returns an ArrayList of evaluation metrics "[MAP@k, precision at X, recall at X, precision @R ]". 
\textbf{[metrics object ].getResults():} print out the results.


==== run evaluation toy data set====
result is:
"MAP@300 is 2.6328988263573007E-4
Precision@300 is 0.9966666666666667
recall@300 is 0.01875784190715182
Precision@R is 0.19661229611041406"
