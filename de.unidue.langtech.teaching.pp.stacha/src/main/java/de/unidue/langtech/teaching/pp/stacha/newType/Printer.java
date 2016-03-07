package de.unidue.langtech.teaching.pp.stacha.newType;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
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
    	Map<Number,String> map = new TreeMap<Number,String>(Collections.reverseOrder());
    	
    	
    	System.out.println("");
    	
    	 for(Keyphrase k:keyphrases){
    		//if(k.getScore()>0 && !(k.getCoveredText().contains(".")))
    		//if(k.getScore()>1 && (k.getEnd()-k.getStart())>7)
    		
    		 map.put(Math.floor(k.getScore()*100)/100,k.getKeyphrase());
  		 }
    	 
    	 for (Sentence s : sentences) System.out.println(s.getCoveredText());
    	 System.out.println("");
    	 
    	 int index=0;
    	
    	 for(Entry<Number,String> e: map.entrySet()){
    		 System.out.println(e.getValue() + " /// " + e.getKey());
    		 index++;
    		if (index>=15) break;
    	 }
    	    	
    	 System.out.println("");
    	 
       // 	for (Token t:tokens){
      //  		if(t.getPos().getPosValue().contains("NN")||t.getPos().getPosValue().contains("VB")||t.getPos().getPosValue().contains("J")){
        		//	System.out.println(t.getCoveredText()); 
      //  		}
        		
       
    	
      } 
    	
        
    }