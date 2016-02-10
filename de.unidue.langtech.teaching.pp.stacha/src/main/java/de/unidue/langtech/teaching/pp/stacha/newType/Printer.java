package de.unidue.langtech.teaching.pp.stacha.newType;

import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.type.Keyphrase;

public class Printer
    extends JCasAnnotator_ImplBase
{

    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    { 
    	Collection<Keyphrase> keyphrases = JCasUtil.select(jcas, Keyphrase.class);
    	Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
    	Collection<Sentence> sentences = JCasUtil.select(jcas, Sentence.class);

    	 for(Keyphrase k:keyphrases){
    		//if(k.getScore()>0 && !(k.getCoveredText().contains(".")))
    		if(k.getScore()>1 && (k.getEnd()-k.getStart())>7)
    		System.out.println(k.getKeyphrase()+ " - " + k.getScore());
    		
    	}
    	
    	    	
        for (Sentence s : sentences) { 
        
        	
        	System.out.println(s.getCoveredText());
        }	
        	for (Token t:tokens){
        		if(t.getPos().getPosValue().contains("NN")||t.getPos().getPosValue().contains("VB")||t.getPos().getPosValue().contains("J")){
        		//	System.out.println(t.getCoveredText()); 
        		}
        		
        
        	
    	for(int i=0; i <tokens.size();i++){
            
        	Token t1= (Token) tokens.toArray()[i];
        	
        	
    	}
    	
      } 
    	
        
    }

}