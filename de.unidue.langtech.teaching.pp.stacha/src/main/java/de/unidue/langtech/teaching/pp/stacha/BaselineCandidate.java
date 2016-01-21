package de.unidue.langtech.teaching.pp.stacha;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unidue.langtech.teaching.pp.type.DetectedLanguage;

@SuppressWarnings("unused")
public class BaselineCandidate extends JCasAnnotator_ImplBase{

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {

        Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
        DetectedLanguage languageAnno = new DetectedLanguage(jcas);
        
        String lang = "EN";
                	
        	System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        
        for(Token t:tokens){
        	
        	String x = t.getCoveredText().toLowerCase();
        	
        	if(x.equals(",")){
        		tokens.remove(t.getCoveredText());
        	}
        	
        	System.out.println(t.getCoveredText());
        }
		
	}

}
