package de.unidue.langtech.teaching.pp.stacha.newType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.type.Keyphrase;

public class Printer
    extends JCasAnnotator_ImplBase
{

    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    { 
    	Collection<Keyphrase> keyphrases = JCasUtil.select(jcas, Keyphrase.class);
    	//Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
    	Collection<Sentence> sentences = JCasUtil.select(jcas, Sentence.class);
    	Map<Number,String> selected = new TreeMap<Number,String>(Collections.reverseOrder());
    	
    	String s = null;
    	String low =null;
    	boolean x;
    	
    	//"/src/test/resources/txt/stop.txt"
    	
    	System.out.println("");
    	
    	for(Keyphrase k:keyphrases){
    		 s = k.getKeyphrase();
    		 low = s.toLowerCase();
    		 x = selected.toString().toLowerCase().contains(low);
    		 
    		//s = s.toLowerCase();
    		 if (s.length()>1&&!x) selected.put(Math.floor(k.getScore()*100)/100, s);
    		
    	}
  		 
    	 for(Entry<Number,String> e: selected.entrySet()){
    		 
    		if(selected.containsValue(e.getValue())){
    			//selected.remove(e);
    		}
    		 
    	 }
    	
    	 
    	 
    	 System.out.println("");
    	 
    	 int index=0;
    	
    	 for(Entry<Number,String> e: selected.entrySet()){
    		 
    			 System.out.println(e.getValue() + " --- " + e.getKey());
    			 index++;
    		
    		if (index>=20) break;
    	 }
		
    	    	
    	 System.out.println("");
    	 
       // 	for (Token t:tokens){
      //  		if(t.getPos().getPosValue().contains("NN")||t.getPos().getPosValue().contains("VB")||t.getPos().getPosValue().contains("J")){
        		//	System.out.println(t.getCoveredText()); 
      //  		}
        		
       
    	
      } 
    	
        
    }