package de.unidue.langtech.teaching.pp.stacha.filter;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.type.Keyphrase;

public class PostProcessing extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {

		Collection<Keyphrase> keyphrases = JCasUtil.select(jcas, Keyphrase.class);
    	Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
    	Collection<Sentence> sentences = JCasUtil.select(jcas, Sentence.class);
    	Map<Number,String> selected = new TreeMap<Number,String>(Collections.reverseOrder());
		
    	
    	for(Keyphrase k:keyphrases){
    		//if(k.getScore()>0 && !(k.getCoveredText().contains(".")))
    		//if(k.getScore()>1 && (k.getEnd()-k.getStart())>7)
    		
    		String s = k.getKeyphrase();
    		//s = s.toLowerCase();
    		
    		selected.put(Math.floor(k.getScore()*100)/100, s);
  		}
    	
    	
	}

}
