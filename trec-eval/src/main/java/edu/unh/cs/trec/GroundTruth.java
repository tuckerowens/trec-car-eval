package edu.unh.cs.trec;

import java.util.HashMap;
import java.util.HashSet;

import java.io.*;

public class GroundTruth {

  private HashMap<String, HashSet<String>> truthSets;


  public GroundTruth( File qrelFile ) {
    
    truthSets = new HashMap<>();
    try {
        consumeTruth( new BufferedReader( new FileReader( qrelFile ) ));
      } catch (Exception e) {
        throw new IllegalArgumentException("The qrel directory, really needs to be a directory");
      }
  }

  public int queryCount() { return truthSets.size(); }

  public int countDocsForQuery(String query) {
    if (truthSets.containsKey(query))
      return truthSets.get(query).size();
    return 0; // We could smooth here.
  }



  private void consumeTruth( BufferedReader qrelReader ) {
    String input;
    try {
      while( (input = qrelReader.readLine()) != null ) {
        String parts[] = input.trim().split(" ");
        String query = parts[0];
        String reference = parts[2];
        int relevance = Integer.parseInt( parts[3] );

        //Who cares?
        if ( relevance == 0 )
          continue;

        if ( !truthSets.containsKey(query) )
            truthSets.put( query, new HashSet<String>() );
        assert( !truthSets.get(query).contains(reference) );
        truthSets.get(query).add(reference);
      }
    qrelReader.close();
    } catch (Exception e) {}
  }


  public boolean relevantToQuery( String query, String thing ) {
    if ( ! truthSets.containsKey(query) )
      return false;
    return truthSets.get(query).contains(thing);
  }


}
